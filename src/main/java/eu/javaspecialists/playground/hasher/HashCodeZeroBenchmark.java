package eu.javaspecialists.playground.hasher;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;
import org.openjdk.jmh.runner.options.*;

import java.lang.invoke.*;
import java.util.concurrent.*;

@Fork(3)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 10, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class HashCodeZeroBenchmark {
    @Param({"0", "2", "4", "6", "8", "10", "12", "14", "16", "18"})
    private int quadru;
    private String s;

    @Setup
    public void setup() throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        s = "ARcZguv";
        for (int i = 0; i < quadru; i++) {
            s += s;
        }
        System.out.printf("%d %d%n", quadru, s.length());
    }


    @Benchmark
    public int hashCodeString() {
        return s.hashCode();
    }

    public static void main(String... args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MethodHandles.lookup().lookupClass().getName())
                .forks(1)
                .warmupIterations(3)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(5)
                .measurementTime(TimeValue.seconds(1))
                .addProfiler("gc")
                .build();
        new Runner(opt).run();
    }
}
