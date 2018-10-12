package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.*;

import java.util.concurrent.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class InternBenchmark {
  @Param({"100", "100000", "10000000"})
  private int limit;

  private int value;

  @Setup
  public void setup() {
    value = 100;
  }

  @Benchmark
  public void creatingStrings(Blackhole bh) {
    bh.consume(nextValue());
  }

  @Benchmark
  public void interningString(Blackhole bh) {
    bh.consume(nextValue().intern());
  }

  private String nextValue() {
    value++;
    if (value > limit) value = 0;
    return Integer.toHexString(value);
  }
}