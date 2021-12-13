package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringAppenderAppendBenchmark {
    private static final char[] chars =
            "thequickbrownfoxjumpsoverlazydog".toCharArray();
    private static final String[] strings =
            new String(chars).chars()
                    .mapToObj(c -> "" + (char) c)
                    .toArray(String[]::new);
    private static final int MASK = chars.length - 1;

    @Benchmark
    @OperationsPerInvocation(1024)
    public StringBuilder testAppendChar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1024; i++) {
            sb.append(chars[i & MASK]);
        }
        return sb;
    }

    @Benchmark
    @OperationsPerInvocation(1024)
    public StringBuilder testAppendString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1024; i++) {
            sb.append(strings[i & MASK]);
        }
        return sb;
    }
}
