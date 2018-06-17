package eu.javaspecialists.perf.string;

import eu.javaspecialists.perf.util.ByteWatcherSingleThread;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.*;

import java.lang.management.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Testing the various algorithms currently employed in the StringConcatFactory.
 *
 * @see java.lang.invoke.StringConcatFactory
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public abstract class AbstractStringAppendBenchmark {
  private static final int NUMBER_OF_DIFFERENT_STRINGS = 1024;
  private static final int MASK = NUMBER_OF_DIFFERENT_STRINGS - 1;
  private final static ThreadMXBean tmb = ManagementFactory.getThreadMXBean();
  private String[] values;
  private int nextNumber;
  private ByteWatcherSingleThread byteWatcher;

  public long calculateAllocations() {
    return byteWatcher.calculateAllocations();
  }

  /**
   * Deliberately not thread safe to avoid a bottleneck on synchronization
   */
  protected String nextString() {
    return values[(nextNumber++) & MASK];
  }
  @Setup
  public void setup() {
    values = ThreadLocalRandom.current().ints(NUMBER_OF_DIFFERENT_STRINGS, 0, 100_000_000)
        .parallel()
        .mapToObj(Integer::toString)
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
  public void stringAdditionWithPlus(Blackhole bh) {
    String s1 = nextString();
    String s2 = nextString();
    String s3 = nextString();
    bh.consume("SELECT " + s1 + " FROM " + s2 + " WHERE " + s3);
  }
}