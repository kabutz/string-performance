# string-performance
Set of benchmarks for the "Enough java.lang.String to Hang Ourselves ..." talk

## Build
JDK 8 or later is required to build the code. Make sure that your `JAVA_HOME` points to proper JDK release and the run the following command:
```
./gradlew shadowJar
```
This will produce a single jar file containing benchmarks:
`build/libs/string-performance-benchmarks-jdk-$javaVersion.jar`, where `$javaVersion` will be replaced with the Java version used to build
the jar (e.g. `string-performance-benchmarks-jdk-11.jar` when built using Java 11).

## Run
First build the benchmark jars as described above and then execute them using the following command:
```
java -jar <jar file>
```
For example this is how to test JDK 11 code using extra JVM flags to be able to compare JDK 8 with JDK 11:
```
java -jar build/libs/string-performance-benchmarks-11.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC'
```


