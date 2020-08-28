#!/bin/sh
cd /home/jenkins
#./apache-shardingsphere-*-sharding-proxy-bin/bin/stop.sh
#sleep 5
rm -fr apache-shardingsphere-5.0.0-RC1-SNAPSHOT-sharding-proxy-bin
mv sharding-distribution/sharding-proxy-distribution/target/*sharding-proxy-bin.tar.gz .
tar zxvf apache-shardingsphere-5.0.0-RC1-SNAPSHOT-sharding-proxy-bin.tar.gz
#rm -f /home/jenkins/apache-shardingsphere-5.0.0-RC1-SNAPSHOT-sharding-proxy-bin/conf/config-*
#cp -f /export/shardingsphere-benchmark/yaml_conf/singlerouting/sharding-masterslave-encrypt/proxy/config-proxy-singlerouting-sharding-masterslave-enc.yaml /export/shardingsphere-benchmark/yaml_conf/server.yaml /home/jenkins/apache-shardingsphere-*-sharding-proxy-bin/conf
cp -f /home/jenkins/mysql-connector-java-5.1.47.jar /home/jenkins/shardingsphere-benchmark-1.1-SNAPSHOT.jar /home/jenkins/apache-shardingsphere-5.0.0-RC1-SNAPSHOT-sharding-proxy-bin/lib
#./apache-shardingsphere-*-sharding-proxy-bin/bin/start.sh
sleep 10