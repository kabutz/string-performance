package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(3)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StringIntrinsicsBenchmark {
  @Param("The quick brown fox jumps over the lazy dog")
  private String s1;
  @Param("The quick brown fox jumps over the lazy dog")
  private String s2;
  private byte[] s1Bytes;
  private byte[] s2Bytes;

  @Setup
  public void setup() {
    s1Bytes = asLatin1ByteArray(s1);
    s2Bytes = asLatin1ByteArray(s2);
  }

  private byte[] asLatin1ByteArray(String value) {
    byte[] result = new byte[value.length()];
    for (int i = 0; i < result.length; i++) {
      result[i] = (byte) value.charAt(i);
    }
    return result;
  }

  @Benchmark
  public boolean string_equals() {
    return s1.equals(s2);
  }

  @Benchmark
  public boolean hand_rolled() {
    return equals(s1Bytes, s2Bytes);
  }

  // Copied from java.lang.StringLatin1.equals(byte[], byte[])
  public static boolean equals(byte[] value, byte[] other) {
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

}
