JAVA6=/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/bin
JAVA7=/Library/Java/JavaVirtualMachines/jdk1.7.0.jdk/Contents/Home/bin
JAVA8=/Library/Java/JavaVirtualMachines/jdk1.8.0.jdk/Contents/Home/bin
JAVA11=/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home/bin

mkdir -p test/j6 test/j7 test/j8 test/j11

cd src/main/java
echo compiling with Java 6
$JAVA6/javac -d ../../../test/j6 eu/javaspecialists/playground/IntToStringLazy.java eu/javaspecialists/playground/IntToStringStudious.java 
echo compiling with Java 7
$JAVA7/javac -d ../../../test/j7 eu/javaspecialists/playground/IntToStringLazy.java eu/javaspecialists/playground/IntToStringStudious.java 
echo compiling with Java 8
$JAVA8/javac -d ../../../test/j8 eu/javaspecialists/playground/IntToStringLazy.java eu/javaspecialists/playground/IntToStringStudious.java 
echo compiling with Java 11
$JAVA11/javac -d ../../../test/j11 eu/javaspecialists/playground/IntToStringLazy.java eu/javaspecialists/playground/IntToStringStudious.java 

cd ../../..

echo out of box comparison 
$JAVA6/java -classpath test/j6 eu.javaspecialists.playground/IntToStringLazy
$JAVA6/java -classpath test/j6 eu.javaspecialists.playground/IntToStringStudious
$JAVA7/java -classpath test/j7 eu.javaspecialists.playground/IntToStringLazy
$JAVA7/java -classpath test/j7 eu.javaspecialists.playground/IntToStringStudious
$JAVA8/java -classpath test/j8 eu.javaspecialists.playground/IntToStringLazy
$JAVA8/java -classpath test/j8 eu.javaspecialists.playground/IntToStringStudious
$JAVA11/java -classpath test/j11 eu.javaspecialists.playground/IntToStringLazy
$JAVA11/java -classpath test/j11 eu.javaspecialists.playground/IntToStringStudious
$JAVA11/java -classpath test/j6 eu.javaspecialists.playground/IntToStringLazy
$JAVA11/java -classpath test/j6 eu.javaspecialists.playground/IntToStringStudious

echo fair comparisons

$JAVA6/java -Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -classpath test/j6 eu.javaspecialists.playground/IntToStringLazy
$JAVA6/java -Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -classpath test/j6 eu.javaspecialists.playground/IntToStringStudious
$JAVA7/java -Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -classpath test/j7 eu.javaspecialists.playground/IntToStringLazy
$JAVA7/java -Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -classpath test/j7 eu.javaspecialists.playground/IntToStringStudious
$JAVA8/java -Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -classpath test/j8 eu.javaspecialists.playground/IntToStringLazy
$JAVA8/java -Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -classpath test/j8 eu.javaspecialists.playground/IntToStringStudious
$JAVA11/java -Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -classpath test/j11 eu.javaspecialists.playground/IntToStringLazy
$JAVA11/java -Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -classpath test/j11 eu.javaspecialists.playground/IntToStringStudious
$JAVA11/java -Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -XX:-CompactStrings -classpath test/j11 eu.javaspecialists.playground/IntToStringLazy
$JAVA11/java -Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -XX:-CompactStrings -classpath test/j11 eu.javaspecialists.playground/IntToStringStudious

