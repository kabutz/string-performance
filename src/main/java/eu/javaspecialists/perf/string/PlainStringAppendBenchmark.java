package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class PlainStringAppendBenchmark extends AbstractStringAppendBenchmark {
  @Benchmark
  public void withoutAnyStringAppending(Blackhole bh) {
    bh.consume(nextString());
    bh.consume(nextString());
    bh.consume(nextLong());
  }

  @Benchmark
  public String stringBuilder() {
    String title = nextString();
    long id1 = nextLong();
    String optiontxt1 = nextString();
    long id2 = nextLong();
    String optiontxt2 = nextString();
    return new StringBuilder().append("<h1>").append(title).append("</h1>")
        .append("<ul>").append("<li><b>").append(id1).append("</b> ")
        .append(optiontxt1).append("</li>")
        .append("<li><b>").append(id2).append("</b> ")
        .append(optiontxt2).append("</li>").append("</ul>").toString();
  }

  @Benchmark
  public String stringBuilderSized() {
    String title = nextString();
    long id1 = nextLong();
    String optiontxt1 = nextString();
    long id2 = nextLong();
    String optiontxt2 = nextString();
    return new StringBuilder(52 + title.length() + 19 + optiontxt1.length() + 19 + optiontxt2.length())
        .append("<h1>").append(title).append("</h1>")
        .append("<ul>").append("<li><b>").append(id1).append("</b> ")
        .append(optiontxt1).append("</li>")
        .append("<li><b>").append(id2).append("</b> ")
        .append(optiontxt2).append("</li>").append("</ul>").toString();
  }

  @Benchmark
  public String stringBuffer() {
    String title = nextString();
    long id1 = nextLong();
    String optiontxt1 = nextString();
    long id2 = nextLong();
    String optiontxt2 = nextString();
    return new StringBuffer().append("<h1>").append(title).append("</h1>")
        .append("<ul>").append("<li><b>").append(id1).append("</b> ")
        .append(optiontxt1).append("</li>")
        .append("<li><b>").append(id2).append("</b> ")
        .append(optiontxt2).append("</li>").append("</ul>").toString();
  }

  @Benchmark
  public String stringBufferSized() {
    String title = nextString();
    long id1 = nextLong();
    String optiontxt1 = nextString();
    long id2 = nextLong();
    String optiontxt2 = nextString();
    return new StringBuffer(52 + title.length() + 19 + optiontxt1.length() + 19 + optiontxt2.length())
        .append("<h1>").append(title).append("</h1>")
        .append("<ul>").append("<li><b>").append(id1).append("</b> ")
        .append(optiontxt1).append("</li>")
        .append("<li><b>").append(id2).append("</b> ")
        .append(optiontxt2).append("</li>").append("</ul>").toString();
  }

  @Benchmark
  public String stringFormat() {
    String title = nextString();
    long id1 = nextLong();
    String optiontxt1 = nextString();
    long id2 = nextLong();
    String optiontxt2 = nextString();
    return String.format("<h1>%s</h1><ul><li><b>%d</b> %s</li><li><b>%d</b> %s</li></ul>", title, id1, optiontxt1, id2, optiontxt2);
  }
}