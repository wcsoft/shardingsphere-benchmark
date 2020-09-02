#!/bin/sh
proxy_work_dir="/home/jenkins"
if [ ! -d $proxy_work_dir  ];then
  mkdir -p $proxy_work_dir
fi
chmod -R 777 $proxy_work_dir
cd $proxy_work_dir
./apache-shardingsphere-*-sharding-proxy-bin/bin/stop.sh
sleep 5
rm -f  ./apache-shardingsphere-*-sharding-proxy-bin/conf/config-*.yaml
cp -f /export/shardingsphere-benchmark/yaml_conf/rangerouting/sharding/proxy/config-proxy-rangerouting-sharding.yaml /export/shardingsphere-benchmark/yaml_conf/server.yaml ./apache-shardingsphere-*-sharding-proxy-bin/conf
./apache-shardingsphere-*-sharding-proxy-bin/bin/start.sh
sleep 10
