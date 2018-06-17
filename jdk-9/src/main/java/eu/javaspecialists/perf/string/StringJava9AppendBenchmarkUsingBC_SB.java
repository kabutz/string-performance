package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.*;

import java.util.concurrent.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 3, jvmArgsAppend = {"-Djava.lang.invoke.stringConcat=BC_SB"})//, "-Djava.lang.invoke.stringConcat.debug=true"})
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public class StringJava9AppendBenchmarkUsingBC_SB extends AbstractStringAppendBenchmark {
}