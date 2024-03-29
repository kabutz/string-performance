DATE=`date "+%Y-%m-%d-%M-%S"`

for i in 6 7 8; do 
	echo $i; 
	export JAVA_HOME=/opt/jdk-$i
	/opt/jdk-$i/bin/java -showversion -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:+TieredCompilation -XX:+UseParallelGC' StringAppenderBenchmark.mixed > java-jdk$i-+TieredCompilation-$DATE.out 
	#/opt/jdk-$i/bin/java -showversion -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC' > java-jdk$i-$DATE.out 
done
 
for i in 11 17 18 9 10 12 13 14 15; do 
	echo $i; 
	export JAVA_HOME=/opt/jdk-$i
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:+TieredCompilation -XX:+UseParallelGC' StringAppenderBenchmark.mixed > java-jdk$i-+TieredCompilation-$DATE.out 
	#/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC' > java-jdk$i-$DATE.out 
done
