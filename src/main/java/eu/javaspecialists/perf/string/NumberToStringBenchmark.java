package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.*;

@Fork(3)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class NumberToStringBenchmark {
    private int intVal = 1640531527;
    private long longVal = -8454749669228202880L;

    @Benchmark
    public String int_plus() {
        return "" + intVal;
    }

    @Benchmark
    public String int_toString() {
        return Integer.toString(intVal);
    }

    @Benchmark
    public String long_plus() {
        return "" + longVal;
    }

    @Benchmark
    public String long_toString() {
        return Long.toString(longVal);
    }
}
