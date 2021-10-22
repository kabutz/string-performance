DATE=`date "+%Y-%m-%d-%M-%S"`

for i in 18 16 15 14 13 12 10 9 ; do 
	echo $i; 
	export JAVA_HOME=/opt/jdk-$i
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC' StringAppender > java-jdk$i-$DATE.out 
done
