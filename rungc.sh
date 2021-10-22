DATE=`date "+%Y-%m-%d-%M-%S"`

for i in 6 7 8; do 
	echo $i; 
	export JAVA_HOME=/opt/jdk-$i
	/opt/jdk-$i/bin/java -showversion -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC' -prof gc StringAppender -f 1 -r 1 -w 1 > java-jdk$i-gc-$DATE.out 
done
 
for i in 11 17; do 
	echo $i; 
	export JAVA_HOME=/opt/jdk-$i
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC' -prof gc StringAppender -f 1 -r 1 -w 1 > java-jdk$i-gc-$DATE.out 
done
