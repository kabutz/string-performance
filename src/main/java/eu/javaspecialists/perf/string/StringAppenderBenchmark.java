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
  @Param("id, insert_time")
  private String columns;
  @Param("history")
  private String table;
  @Param("1539203113091")
  private long time;

  @Benchmark
  public String concat() {
    return "SELECT " + columns + " FROM " + table + " WHERE last_update_time > " + time;
  }

  @Benchmark
  public String sb() {
    return new StringBuilder()
        .append("SELECT ").append(columns).append(" FROM ").append(table).append(" WHERE last_update_time > ").append(time).toString();
  }

  @Benchmark
  public String sb_sized() {
    return new StringBuilder(39 + columns.length() + table.length() + 19)
        .append("SELECT ").append(columns).append(" FROM ").append(table).append(" WHERE last_update_time > ").append(time).toString();
  }
}