package eu.javaspecialists.perf.string;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.*;

import java.util.concurrent.*;

@Fork(value = 3, jvmArgsAppend = {"-Djava.lang.invoke.stringConcat=BC_SB"})//, "-Djava.lang.invoke.stringConcat.debug=true"})
public class StringJava9AppendBenchmarkUsingBC_SB extends AbstractStringAppendBenchmark {
}