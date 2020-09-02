chmod -R 777 /export/benchmark/shardingsphere-benchmark/src/main/resources
cd /export/benchmark/shardingsphere-benchmark/src/main/resources
sed -i "s/shardingsphere.result.sample.amount=[0-9]*,[0-9]*/shardingsphere.result.sample.amount=$1,$2/g" `grep 'shardingsphere.result.sample.amount=[0-9]*,[0-9]*' -rl ./config/user-config.properties`