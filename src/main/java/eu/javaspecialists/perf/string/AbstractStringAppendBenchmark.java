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
    String s1 = nextString();
    String s2 = nextString();
    long s3 = nextLong();
    return "SELECT " + s1 + " FROM " + s2 + " WHERE last_update_time > " + s3;
  }
}