package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class IntToStringBenchmark {
  @Param("1640531527")
  private int value;

  @Benchmark
  public String concat() {
    return "" + value;
  }

  @Benchmark
  public String integer_toString() {
    return Integer.toString(value);
  }
}