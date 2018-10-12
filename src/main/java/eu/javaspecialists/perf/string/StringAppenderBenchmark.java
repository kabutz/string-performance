package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StringAppenderBenchmark {
  @Param("What is the fastest way to append Strings?")
  private String title;
  @Param("1539203113091")
  private long id1;
  @Param("Plain old +")
  private String optiontxt1;
  @Param("3472387872424")
  private long id2;
  @Param("StringBuilder.append()")
  private String optiontxt2;

  @Benchmark
  public String concat() {
    return "<h1>" + title + "</h1>" +
        "<ul>" +
        "<li><b>" + id1 + "</b> " + optiontxt1 + "</li>" +
        "<li><b>" + id2 + "</b> " + optiontxt2 + "</li>" +
        "</ul>";
  }

  @Benchmark
  public String sb() {
    return new StringBuilder().append("<h1>").append(title)
        .append("</h1>").append("<ul>").append("<li><b>")
        .append(id1).append("</b> ").append(optiontxt1)
        .append("</li>").append("<li><b>").append(id2)
        .append("</b> ").append(optiontxt2).append("</li>")
        .append("</ul>").toString();
  }

  @Benchmark
  public String sb_sized() {
    return new StringBuilder(52 + title.length() + 19 + optiontxt1.length() + 19 + optiontxt2.length())
        .append("<h1>").append(title).append("</h1>")
        .append("<ul>").append("<li><b>").append(id1).append("</b> ")
        .append(optiontxt1).append("</li>")
        .append("<li><b>").append(id2).append("</b> ")
        .append(optiontxt2).append("</li>").append("</ul>").toString();
  }
}