package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.*;

import java.util.*;
import java.util.concurrent.*;

@Fork(3)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class InternBenchmark {
    @Param({"100", "100000", "10000000"})
    private int limit;

    private int value;

    @Setup
    public void setup() {
        value = 100;
    }

    @Benchmark
    public String creatingStrings(Blackhole bh) {
        return nextValue();
    }

    @Benchmark
    public String interningString() {
        return nextValue().intern();
    }

    private final Map<String, String> cache = new ConcurrentHashMap<>();

    @Benchmark
    public String chmString() {
        String next = nextValue();
        String putResult = cache.putIfAbsent(next, next);
        return putResult == null ? next : putResult;
    }

    private String nextValue() {
        value++;
        if (value > limit) value = 0;
        return Integer.toHexString(value);
    }
}
