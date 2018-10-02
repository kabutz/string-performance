package eu.javaspecialists.perf.string;

import eu.javaspecialists.perf.util.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.*;

import java.lang.management.*;
import java.util.*;
import java.util.concurrent.*;

@Fork(3)
public class PlainStringAppendBenchmark extends AbstractStringAppendBenchmark{
  @Benchmark
  public void withoutAnyStringAppending(Blackhole bh) {
    bh.consume(nextString());
    bh.consume(nextString());
    bh.consume(nextString());
  }

  @Benchmark
  public void stringBuilder(Blackhole bh) {
    String s1 = nextString();
    String s2 = nextString();
    String s3 = nextString();
    bh.consume(new StringBuilder().append("SELECT ").append(s1).append(" FROM ").append(s2).append(" WHERE ").append(s3).toString());
  }

  @Benchmark
  public void stringBuilderSized(Blackhole bh) {
    String s1 = nextString();
    String s2 = nextString();
    String s3 = nextString();
    bh.consume(new StringBuilder(20 + s1.length() + s2.length() + s3.length())
        .append("SELECT ").append(s1).append(" FROM ").append(s2).append(" WHERE ").append(s3).toString());
  }

  @Benchmark
  public void stringBuffer(Blackhole bh) {
    String s1 = nextString();
    String s2 = nextString();
    String s3 = nextString();
    bh.consume(new StringBuffer().append("SELECT ").append(s1).append(" FROM ").append(s2).append(" WHERE ").append(s3).toString());
  }

  @Benchmark
  public void stringBufferSized(Blackhole bh) {
    String s1 = nextString();
    String s2 = nextString();
    String s3 = nextString();
    bh.consume(new StringBuffer(20 + s1.length() + s2.length() + s3.length())
        .append("SELECT ").append(s1).append(" FROM ").append(s2).append(" WHERE ").append(s3).toString());
  }
}