#!/bin/sh
cd /home/jenkins
./apache-shardingsphere-*-shardingsphere-proxy-bin/bin/stop.sh
sleep 5
rm -f  ./apache-shardingsphere-*-sharding-proxy-bin/conf/config-*.yaml
cp -f /export/shardingsphere-benchmark/yaml_conf/fullrouting/encrypt/proxy/config-proxy-fullrouting-encrypt.yaml /export/shardingsphere-benchmark/yaml_conf/server.yaml ./apache-shardingsphere-*-sharding-proxy-bin/conf
./apache-shardingsphere-*-shardingsphere-proxy-bin/bin/start.sh
sleep 10
