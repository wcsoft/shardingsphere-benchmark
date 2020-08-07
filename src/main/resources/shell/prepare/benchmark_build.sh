export JAVA_HOME=/export/jdk1.8.0_192/
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin

cd /export/benchmark/shardingsphere-benchmark
/export/apache-maven-3.6.0/bin/mvn clean install

scp -r target/shardingsphere-benchmark-1.1-SNAPSHOT.jar jenkins@ss.benchmark.performance.machine:/home/jenkins/apache-jmeter-4.0/lib/ext

scp -r src/main/resources/testplan/* jenkins@ss.benchmark.performance.machine:/home/jenkins/apache-jmeter-4.0/shardingsphere_test_plan

#copy yaml of proxy to proxy machine:/home/jenkins/BT_jenkins/proxy_yaml_conf
scp -r src/main/resources/yaml/* jenkins@ss.benchmark.proxy.machine:/export/shardingsphere-benchmark/yaml_conf

scp -r src/main/resources/shell/deploy/* jenkins@ss.benchmark.proxy.machine:/export/shardingsphere-benchmark/shell

#copy benchmark jar to proxy machine
scp -r target/shardingsphere-benchmark-1.1-SNAPSHOT.jar jenkins@ss.benchmark.proxy.machine:/home/jenkins

scp -r ss_build_version.log jenkins@ss.benchmark.performance.machine:/home/jenkins/apache-jmeter-4.0

scp -r ss_build_version.log jenkins@ss.benchmark.proxy.machine:/home/jenkins/BT_jenkins

scp -r src/main/resources/shell/prepare/jmeter_result_manage.sh jenkins@ss.benchmark.performance.machine:/export/shardingsphere-benchmark/shell