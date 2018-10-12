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
    String s1 = nextString();
    String s2 = nextString();
    long l3 = nextLong();
    return new StringBuilder().append("SELECT ").append(s1).append(" FROM ").append(s2).append(" WHERE last_update_time > ").append(l3).toString();
  }

  @Benchmark
  public String stringBuilderSized() {
    String s1 = nextString();
    String s2 = nextString();
    long l3 = nextLong();
    return new StringBuilder(20 + s1.length() + s2.length() + 19)
        .append("SELECT ").append(s1).append(" FROM ").append(s2).append(" WHERE last_update_time > ").append(l3).toString();
  }

  @Benchmark
  public String stringBuffer() {
    String s1 = nextString();
    String s2 = nextString();
    long l3 = nextLong();
    return new StringBuffer().append("SELECT ").append(s1).append(" FROM ").append(s2).append(" WHERE last_update_time > ").append(l3).toString();
  }

  @Benchmark
  public String stringBufferSized() {
    String s1 = nextString();
    String s2 = nextString();
    long l3 = nextLong();
    return new StringBuffer(20 + s1.length() + s2.length() + 19)
        .append("SELECT ").append(s1).append(" FROM ").append(s2).append(" WHERE last_update_time > ").append(l3).toString();
  }
}