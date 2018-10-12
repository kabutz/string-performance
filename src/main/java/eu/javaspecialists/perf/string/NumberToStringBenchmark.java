package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(3)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class NumberToStringBenchmark {
  @Param("1640531527")
  private int intVal;
  @Param("-8454749669228202880")
  private long longVal;

  @Benchmark
  public String int_concat() {
    return "" + intVal;
  }

  @Benchmark
  public String int_toString() {
    return Integer.toString(intVal);
  }

  @Benchmark
  public String long_concat() {
    return "" + longVal;
  }

  @Benchmark
  public String long_toString() {
    return Long.toString(longVal);
  }
}