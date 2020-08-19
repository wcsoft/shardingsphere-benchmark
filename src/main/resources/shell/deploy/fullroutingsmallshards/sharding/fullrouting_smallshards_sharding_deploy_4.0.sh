#!/bin/sh
cd /home/jenkins
./apache-shardingsphere-*-sharding-proxy-bin/bin/stop.sh
sleep 5
rm -f  /home/jenkins/apache-shardingsphere-*-sharding-proxy-bin/conf/config-*.yaml
cp -f /export/shardingsphere-benchmark/yaml_conf/fullrouting/sharding/proxy/config-proxy-fullrouting-smallshards-sharding.yaml /export/shardingsphere-benchmark/yaml_conf/server.yaml /home/jenkins/apache-shardingsphere-*-sharding-proxy-bin/conf
./apache-shardingsphere-*-sharding-proxy-bin/bin/start.sh
sleep 10
