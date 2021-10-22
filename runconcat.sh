DATE=`date "+%Y-%m-%d-%M-%S"`

for algo in MH_INLINE_SIZED_EXACT BC_SB_SIZED_EXACT MH_SB_SIZED BC_SB_SIZED MH_SB_SIZED_EXACT BC_SB; do
for i in 11 17; do 
	echo $i; 
	export JAVA_HOME=/opt/jdk-$i
	JVM_ARGS="-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -Djava.lang.invoke.stringConcat=$algo"
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs "${JVM_ARGS}" StringAppenderBenchmark.plus > java-jdk$i-$algo-$DATE.out 
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs "${JVM_ARGS}" -prof gc StringAppenderBenchmark.plus > java-jdk$i-gc-$algo-$DATE.out 
done
done

