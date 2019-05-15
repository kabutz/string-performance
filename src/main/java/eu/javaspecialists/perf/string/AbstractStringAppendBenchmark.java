package eu.javaspecialists.perf.string;

import eu.javaspecialists.perf.util.*;
import org.openjdk.jmh.annotations.*;

import java.lang.management.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

/**
 * Testing the various algorithms currently employed in the StringConcatFactory.
 *
 * @see java.lang.invoke.StringConcatFactory
 */
@Fork(3)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public abstract class AbstractStringAppendBenchmark {
    private static final int NUMBER_OF_DIFFERENT_VALUES = 1024;
    private static final int MASK = NUMBER_OF_DIFFERENT_VALUES - 1;
    private final static ThreadMXBean tmb = ManagementFactory.getThreadMXBean();
    private String[] stringValues;
    private long[] longValues;
    private int nextNumber;
    private ByteWatcherSingleThread byteWatcher;

    public long calculateAllocations() {
        return byteWatcher.calculateAllocations();
    }

    /**
     * Deliberately not thread safe to avoid a bottleneck on synchronization
     */
    protected String nextString() {
        return stringValues[(nextNumber++) & MASK];
    }

    protected long nextLong() {
        return longValues[(nextNumber++) & MASK];
    }

    @Setup
    public void setup() {
        longValues = ThreadLocalRandom.current().longs(NUMBER_OF_DIFFERENT_VALUES, Long.MAX_VALUE / 2, Long.MAX_VALUE)
                .parallel()
                .toArray();
        stringValues = LongStream.of(longValues)
                .mapToObj(Long::toString)
                .toArray(String[]::new);
        byteWatcher = new ByteWatcherSingleThread();
        byteWatcher.reset();
    }

    @TearDown
    public void tearDown() {
        long bytesAllocated = byteWatcher.calculateAllocations();
        System.out.printf(Locale.US, "%n### bytes allocated %,d%n", bytesAllocated);
        byteWatcher = null;
    }


    @Benchmark
    public String stringAdditionWithPlus() {
        String title = nextString();
        long id1 = nextLong();
        String optiontxt1 = nextString();
        long id2 = nextLong();
        String optiontxt2 = nextString();
        return "<h1>" + title + "</h1>" +
                "<ul>" +
                "<li><b>" + id1 + "</b> " + optiontxt1 + "</li>" +
                "<li><b>" + id2 + "</b> " + optiontxt2 + "</li>" +
                "</ul>";
    }
}
