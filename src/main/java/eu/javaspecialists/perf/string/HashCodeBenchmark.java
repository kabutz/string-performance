package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.*;

import java.util.*;
import java.util.concurrent.*;

@Fork(3)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class HashCodeBenchmark {
    private static final char[] lettersInLatinAlphabet = new char[26 * 2];
    static {
        for (int i = 0; i < lettersInLatinAlphabet.length / 2; i++) {
            lettersInLatinAlphabet[i] = (char) ('a' + i);
            lettersInLatinAlphabet[i + 26] = (char) ('A' + i);
        }
    }
    @Param({"100", "1000", "10000"})
    private int length;

    private char[] value;

    @Setup
    public void setup() {
        Random random = new Random();
        value = new char[length];
        for (int i = 0; i < value.length; i++) {
            value[i] = lettersInLatinAlphabet[random.nextInt(lettersInLatinAlphabet.length)];
        }
        System.out.println(new String(value));
    }

    @Benchmark
    public void testHashCodeJava1(Blackhole bh) {
        bh.consume(hashCodeJava1(value, 0, length));
    }

    public int hashCodeJava1(char[] value, int offset, int count) {
        int h = 0;
        int off = offset;
        char val[] = value;
        int len = count;

        if (len < 16) {
            for (int i = len; i > 0; i--) {
                h = (h * 37) + val[off++];
            }
        } else {
            // only sample some characters
            int skip = len / 8;
            for (int i = len; i > 0; i -= skip, off += skip) {
                h = (h * 39) + val[off];
            }
        }
        return h;
    }

    @Benchmark
    public void testHashCodeJava2(Blackhole bh) {
        bh.consume(hashCodeJava2(value, 0, length));
    }

    public int hashCodeJava2(char[] value, int offset, int count) {
        int h = 0;
        int off = offset;
        char val[] = value;
        int len = count;

        for (int i = 0; i < len; i++)
            h = 31 * h + val[off++];

        return h;
    }
}
