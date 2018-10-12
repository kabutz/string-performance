package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(3)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StringAppenderBenchmark {
  @Param("String benchmarks")
  private String title;
  @Param("2734923874")
  private long id1;
  @Param("100100")
  private long id2;
  @Param("plus vs concat")
  private String optiontxt1;
  @Param("StringBuilder")
  private String optiontxt2;

  @Benchmark
  public String plus() {
    //        4                      16                   5                          12                5                       10
    return "<h1>" + title + "</h1><ul><li><b>" + id1 + "</b> " + optiontxt1 + "</li><li><b>" + id2 + "</b> " + optiontxt2 + "</li></ul>";
  }

  @Benchmark
  public String concat() {
    return "<h1>".concat(title).concat("</h1><ul><li><b>").concat(Long.toString(id1)).concat("</b> ").concat(optiontxt1)
        .concat("</li><li><b>").concat(Long.toString(id2)).concat("</b> ").concat(optiontxt2).concat("</li></ul>");
  }

  @Benchmark
  public String sb() {
    return new StringBuilder()
        .append("<h1>").append(title).append("</h1><ul><li><b>").append(id1).append("</b> ").append(optiontxt1)
        .append("</li><li><b>").append(id2).append("</b> ").append(optiontxt2).append("</li></ul>").toString();
  }

  @Benchmark
  public String sb_sized() {
    return new StringBuilder(52 + 2 * 20 + optiontxt1.length() + optiontxt2.length())
        .append("<h1>").append(title).append("</h1><ul><li><b>").append(id1).append("</b> ").append(optiontxt1)
        .append("</li><li><b>").append(id2).append("</b> ").append(optiontxt2).append("</li></ul>").toString();
  }
}