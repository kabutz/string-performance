package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.*;

import java.util.concurrent.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(3)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public class IntToStringBenchmark {
  private int value;

  @Setup
  public void setup() {
    value = ThreadLocalRandom.current().nextInt(10, 100);
  }

  @Benchmark
  public void lazyIntToString(Blackhole bh) {
    bh.consume("" + value);
  }

  @Benchmark
  public void studiousIntToString1(Blackhole bh) {
    bh.consume(Integer.toString(value));
  }
}