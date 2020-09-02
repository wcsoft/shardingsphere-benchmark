#!/bin/sh
proxy_work_dir="/home/jenkins"
if [ ! -d $proxy_work_dir  ];then
  mkdir -p $proxy_work_dir
fi

cd $proxy_work_dir
./apache-shardingsphere-*-shardingsphere-proxy-bin/bin/stop.sh
sleep 5
rm -f  /home/jenkins/apache-shardingsphere-*-shardingsphere-proxy-bin/conf/config-*.yaml
cp -f /export/shardingsphere-benchmark/yaml_conf/fullrouting/sharding-masterslave-encrypt/proxy/config-proxy-fullrouting-smallshards-sharding-masterslave-enc.yaml /export/shardingsphere-benchmark/yaml_conf/server.yaml /home/jenkins/apache-shardingsphere-*-shardingsphere-proxy-bin/conf
./apache-shardingsphere-*-shardingsphere-proxy-bin/bin/start.sh
sleep 10
