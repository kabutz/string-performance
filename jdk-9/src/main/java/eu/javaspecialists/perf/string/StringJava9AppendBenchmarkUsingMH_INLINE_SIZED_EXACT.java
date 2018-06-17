package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;

@Fork(value = 3, jvmArgsAppend = {"-Djava.lang.invoke.stringConcat=MH_INLINE_SIZED_EXACT"})//, "-Djava.lang.invoke.stringConcat.debug=true"})
public class StringJava9AppendBenchmarkUsingMH_INLINE_SIZED_EXACT extends AbstractStringAppendBenchmark {
}