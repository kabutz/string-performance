package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;

@Fork(value = 3, jvmArgsAppend = {"-Djava.lang.invoke.stringConcat=MH_SB_SIZED"})//, "-Djava.lang.invoke.stringConcat.debug=true"})
public class StringJava9AppendBenchmarkUsingMH_SB_SIZED extends AbstractStringAppendBenchmark {
}