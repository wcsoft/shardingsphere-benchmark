chmod -R 777 /export/benchmark/shardingsphere-benchmark/src/main/resources
cd /export/benchmark/shardingsphere-benchmark/src/main/resources
sed -i "s/ss.benchmark.result.skip.begin=[0-9]*/ss.benchmark.result.skip.begin=$1/g" `grep 'ss.benchmark.result.skip.begin=[0-9]*' -rl ./config/benchmark-result.properties`
sed -i "s/ss.benchmark.result.skip.end=[0-9]*/ss.benchmark.result.skip.end=$2/g" `grep 'ss.benchmark.result.skip.end=[0-9]*' -rl ./config/benchmark-result.properties`