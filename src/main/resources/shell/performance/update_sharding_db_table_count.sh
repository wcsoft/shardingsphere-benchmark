chmod -R 777 /export/benchmark/shardingsphere-benchmark/src/main/resources
let db_count=$1-1
let table_count=$2-1
sed -i "s/{0..[1-9]*}.sbtest\${\0..[1-9]*}/{0..$db_count}.sbtest\${0..$table_count}/g" `grep '{0..[1-9]*}.sbtest\${0..[1-9]*}' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/yaml`
sed -i "s/{id % [1-9]*}/{id % $1}/g" `grep '{id % [1-9]*}' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/yaml`
sed -i "s/{k % [0-9]*}/{k % $2}/g" `grep '{k % [0-9]*}' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/yaml`
sed -i "s/benchmark.table.count=[0-9]*/benchmark.table.count=$2/g" `grep 'benchmark.table.count=[0-9]*' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/config/dbconfig.properties`
