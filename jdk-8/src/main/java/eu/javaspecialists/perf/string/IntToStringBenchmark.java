package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.*;

import java.util.concurrent.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(3)
@Warmup(iterations = 10)
@Measurement(iterations = 20)
@State(Scope.Benchmark)
public class IntToStringBenchmark {
  @Param({"1", "0", "1640531527"})
  private int INCREMENT;

  private int value;

  @Setup
  public void setup() {
    value = 100;
  }

  @Benchmark
  public void lazyIntToString(Blackhole bh) {
    bh.consume("" + nextValue());
  }

  @Benchmark
  public void studiousIntToString1(Blackhole bh) {
    bh.consume(Integer.toString(nextValue()));
  }

  private int nextValue() {
    return (value += INCREMENT);
  }
}