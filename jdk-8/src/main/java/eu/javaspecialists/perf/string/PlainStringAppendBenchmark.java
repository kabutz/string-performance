package eu.javaspecialists.perf.string;

import eu.javaspecialists.perf.util.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.*;

import java.lang.management.*;
import java.util.*;
import java.util.concurrent.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 3)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public class PlainStringAppendBenchmark extends AbstractStringAppendBenchmark{
  private StringAppenderHelper helper;

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