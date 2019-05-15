package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;

@Fork(3)
@Warmup(iterations = 30, time = 1)
@Measurement(iterations = 30, time = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StringIntrinsicsBenchmark {
    private final static boolean TEST_DEDUPLICATION = false;

    @Param({"4", "16", "64", "256", "1024", "65536"})
    private int length;

    private String s1;
    private String s2;
    private String s3;
    private Latin1String l1;
    private Latin1String l2;
    private Latin1String l3;
    private Latin1StringMismatch m1;
    private Latin1StringMismatch m2;
    private Latin1StringMismatch m3;
    private byte[] b1;
    private byte[] b2;
    private byte[] b3;

    @Setup
    public void setup() throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        s1 = createString(length);
        s2 = createString(length);
        s3 = "Wrong length!";
        l1 = new Latin1String(s1);
        l2 = new Latin1String(s2);
        l3 = new Latin1String(s3);
        m1 = new Latin1StringMismatch(s1);
        m2 = new Latin1StringMismatch(s2);
        m3 = new Latin1StringMismatch(s3);
        b1 = l1.value;
        b2 = l2.value;
        b3 = l3.value;

        if (TEST_DEDUPLICATION) {
            Field valueField = String.class.getDeclaredField("value");
            valueField.setAccessible(true);
            boolean deduplicated = false;
            System.out.print("Check for deduplication ..");
            for (int i = 0; i < 10 && !deduplicated; i++) {
                System.out.print('.');
                System.gc();
                Thread.sleep(100);
                deduplicated = (valueField.get(s1) == valueField.get(s2));
            }
            if (deduplicated) {
                System.out.println(" deduplicated!");
            } else {
                System.out.println(" not deduplicated.");
            }
        }
    }

    private String createString(int length) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append("The quick brown fox jumps over the lazy dog.  ");
        }
        sb.setLength(length);
        String s = sb.toString();
        if (s.length() != length) throw new AssertionError(
                "Incorrect length.  Expected " + length + ", actual length=" + s.length() + " s=\"" + s + "\"");
        return s;
    }

    @Benchmark
    public boolean string_equal() {
        return s1.equals(s2);
    }

    @Benchmark
    public boolean string_non_equal() {
        return s1.equals(s3);
    }

    @Benchmark
    public boolean hand_rolled_equal() {
        return l1.equals(l2);
    }

    @Benchmark
    public boolean hand_rolled_non_equal() {
        return l1.equals(l3);
    }

    @Benchmark
    public boolean mismatch_equal() {
        return m1.equals(m2);
    }

    @Benchmark
    public boolean mismatch_non_equal() {
        return m1.equals(m3);
    }

    public static final class Latin1String {
        private final byte[] value;

        public Latin1String(String string) {
            byte[] bytes = new byte[string.length()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) string.charAt(i);
            }
            value = bytes;
        }

        @Override
        public boolean equals(Object anObject) {
            if (this == anObject) {
                return true;
            }
            if (anObject instanceof Latin1String) {
                Latin1String aString = (Latin1String) anObject;
                return equals(value, aString.value);
            }
            return false;
        }

        // Copied from java.lang.StringLatin1.equals(byte[], byte[])
        private static boolean equals(byte[] value, byte[] other) {
            if (value.length == other.length) {
                for (int i = 0; i < value.length; i++) {
                    if (value[i] != other[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(value);
        }
    }

    public static final class Latin1StringMismatch {
        private final byte[] value;

        public Latin1StringMismatch(String string) {
            byte[] bytes = new byte[string.length()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) string.charAt(i);
            }
            value = bytes;
        }

        @Override
        public boolean equals(Object anObject) {
            if (this == anObject) {
                return true;
            }
            if (anObject instanceof Latin1StringMismatch) {
                Latin1StringMismatch aString = (Latin1StringMismatch) anObject;
                return equals(value, aString.value);
            }
            return false;
        }

        // Probably how it is implemented in the intrinsic
        private static boolean equals(byte[] value, byte[] other) {
            if (value.length == other.length) {
                return Arrays.mismatch(value, other) == -1;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(value);
        }
    }

}
