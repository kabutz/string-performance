package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(3)
@Warmup(iterations = 10)
@Measurement(iterations = 20)
@State(Scope.Benchmark)
public class StringAppenderBenchmark {
  @Param("*")
  private String columns;
  @Param("persons")
  private String table;
  @Param("21")
  private int age;

  @Benchmark
  public String concat() {
    return "SELECT " + columns + " FROM " + table + " WHERE age > " + age;
  }

  @Benchmark
  public String sb() {
    return new StringBuilder()
        .append("SELECT ").append(columns).append(" FROM ").append(table).append(" WHERE age > ").append(age).toString();
  }

  @Benchmark
  public String sb_sized() {
    return new StringBuilder(25 + columns.length() + table.length() + 10)
        .append("SELECT ").append(columns).append(" FROM ").append(table).append(" WHERE age > ").append(age).toString();
  }
}