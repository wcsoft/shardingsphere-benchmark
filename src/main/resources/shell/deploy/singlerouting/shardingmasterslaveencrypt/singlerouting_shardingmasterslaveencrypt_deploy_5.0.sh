#!/bin/sh
proxy_work_dir="/home/jenkins"
if [ ! -d $proxy_work_dir  ];then
  mkdir -p $proxy_work_dir
fi

cd $proxy_work_dir
#./apache-shardingsphere-*-shardingsphere-proxy-bin/bin/stop.sh
#sleep 5
rm -fr apache-shardingsphere-5.0.0-RC1-SNAPSHOT-shardingsphere-proxy-bin
mv shardingsphere-distribution/shardingsphere-proxy-distribution/target/*shardingsphere-proxy-bin.tar.gz .
tar zxvf apache-shardingsphere-5.0.0-RC1-SNAPSHOT-shardingsphere-proxy-bin.tar.gz
#rm -f /home/jenkins/apache-shardingsphere-5.0.0-RC1-SNAPSHOT-shardingsphere-proxy-bin/conf/config-*
#cp -f /export/shardingsphere-benchmark/yaml_conf/singlerouting/sharding-masterslave-encrypt/proxy/config-proxy-singlerouting-sharding-masterslave-enc.yaml /export/shardingsphere-benchmark/yaml_conf/server.yaml /home/jenkins/apache-shardingsphere-*-shardingsphere-proxy-bin/conf
cp -f ./mysql-connector-java-5.1.47.jar ./shardingsphere-benchmark-1.1-SNAPSHOT.jar ./apache-shardingsphere-5.0.0-RC1-SNAPSHOT-shardingsphere-proxy-bin/lib
#./apache-shardingsphere-*-shardingsphere-proxy-bin/bin/start.sh
#sleep 10
