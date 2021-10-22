DATE=`date "+%Y-%m-%d-%M-%S"`

for i in 11 17; do 
	echo $i; 
	export JAVA_HOME=/opt/jdk-$i
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -Djava.lang.invoke.stringConcat=MH_INLINE_SIZED_EXACT' -prof gc StringAppender > java-jdk$i-MH_INLINE_SIZED_EXACT-$DATE.out 
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -Djava.lang.invoke.stringConcat=BC_SB_SIZED_EXACT' -prof gc StringAppender > java-jdk$i-BC_SB_SIZED_EXACT-$DATE.out 
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -Djava.lang.invoke.stringConcat=MH_SB_SIZED' -prof gc StringAppender > java-jdk$i-MH_SB_SIZED-$DATE.out 
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -Djava.lang.invoke.stringConcat=BC_SB_SIZED' -prof gc StringAppender > java-jdk$i-BC_SB_SIZED-$DATE.out 
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -Djava.lang.invoke.stringConcat=MH_SB_SIZED_EXACT' -prof gc StringAppender > java-jdk$i-MH_SB_SIZED_EXACT-$DATE.out 
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC -Djava.lang.invoke.stringConcat=BC_SB' -prof gc StringAppender > java-jdk$i-BC_SB-$DATE.out 
done

for i in 18 16 15 14 13 12 10 9 ; do 
	echo $i; 
	export JAVA_HOME=/opt/jdk-$i
	/opt/jdk-$i/bin/java -showversion --add-opens java.base/java.io=ALL-UNNAMED -jar build/libs/string-performance-benchmarks-jdk-$i.jar -jvmArgs '-Xmx8g -XX:-TieredCompilation -XX:+UseParallelGC' StringAppender > java-jdk$i-$DATE.out 
done
