# string-performance
Set of benchmarks for the "Enough java.lang.String to Hang Ourselves ..." talk

## Build
JDK 11 or later is required to build the code. Make sure that your `JAVA_HOME` points to proper JDK release and the run the following command:
```
gradlew shadowJar
```
This will produce two jar files:
1. `jdk-8/build/libs/jdk-8-benchmarks.jar` - contains code and benchmarks relevant for JDK 8
2. `jdk-11/build/libs/jdk-11-benchmarks.jar` - contains JDK 8 code and benchmarks plus new benchmarks for JDK 11

## Run
First build the benchmark jars as described above and then execute them using the following command:
```
java -jar <jar file>
```
For example this is how to test JDK 11 code:
```
java -jar jdk-11/build/libs/jdk-11-benchmarks.jar -jvmArgs '-XX:+UseParallelGC'
```


