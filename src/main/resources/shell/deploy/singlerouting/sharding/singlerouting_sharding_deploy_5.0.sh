#!/bin/sh
cd /home/jenkins
./apache-shardingsphere-*-shardingsphere-proxy-bin/bin/stop.sh
sleep 5
rm -f  ./apache-shardingsphere-*-shardingsphere-proxy-bin/conf/config-*.yaml
cp -f /export/shardingsphere-benchmark/yaml_conf/singlerouting/sharding/proxy/config-proxy-singlerouting-sharding.yaml /export/shardingsphere-benchmark/yaml_conf/server.yaml ./apache-shardingsphere-*-shardingsphere-proxy-bin/conf
./apache-shardingsphere-*-shardingsphere-proxy-bin/bin/start.sh
sleep 10
