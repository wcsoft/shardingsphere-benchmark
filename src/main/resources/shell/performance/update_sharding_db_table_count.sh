chmod -R 777 /export/benchmark/shardingsphere-benchmark/src/main/resources
let db_count=$1-1
let table_count=$2-1
let db_count_smallshards=$3-1
let table_count_smallshards=$4-1

cd /export/benchmark/shardingsphere-benchmark/src/main/resources

sed -i "s/{0..[1-9]*}.sbtest\${\0..[1-9]*}/{0..$db_count}.sbtest\${0..$table_count}/g" `grep '{0..[1-9]*}.sbtest\${0..[1-9]*}' -rl ./yaml/fullrouting`
sed -i "s/{id % [1-9]*}/{id % $1}/g" `grep '{id % [1-9]*}' -rl ./yaml/fullrouting`
sed -i "s/{k % [0-9]*}/{k % $2}/g" `grep '{k % [0-9]*}' -rl ./yaml/fullrouting`

sed -i "s/{0..[1-9]*}.sbtest\${\0..[1-9]*}/{0..$db_count}.sbtest\${0..$table_count}/g" `grep '{0..[1-9]*}.sbtest\${0..[1-9]*}' -rl ./yaml/rangerouting`
sed -i "s/{id % [1-9]*}/{id % $1}/g" `grep '{id % [1-9]*}' -rl ./yaml/rangerouting`
sed -i "s/{k % [0-9]*}/{k % $2}/g" `grep '{k % [0-9]*}' -rl ./yaml/rangerouting`

sed -i "s/{0..[1-9]*}.sbtest\${\0..[1-9]*}/{0..$db_count}.sbtest\${0..$table_count}/g" `grep '{0..[1-9]*}.sbtest\${0..[1-9]*}' -rl ./yaml/singlerouting`
sed -i "s/{id % [1-9]*}/{id % $1}/g" `grep '{id % [1-9]*}' -rl ./yaml/singlerouting`
sed -i "s/{k % [0-9]*}/{k % $2}/g" `grep '{k % [0-9]*}' -rl ./yaml/singlerouting`

sed -i "s/shardingsphere.sharding.table.count=[0-9]*/shardingsphere.sharding.table.count=$2/g" `grep 'shardingsphere.sharding.table.count=[0-9]*' -rl ./config/user-config.properties`
sed -i "s/shardingsphere.sharding.db.count=[0-9]*/shardingsphere.sharding.db.count=$1/g" `grep 'shardingsphere.sharding.db.count=[0-9]*' -rl ./config/user-config.properties`

#sed -i "s/{0..[1-9]*}.sbtest\${\0..[1-9]*}/{0..$db_count_smallshards}.sbtest\${0..$table_count_smallshards}/g" `grep '{0..[1-9]*}.sbtest\${0..[1-9]*}' -rl ./yaml/fullrouting-smallshards`
#sed -i "s/{id % [1-9]*}/{id % $3}/g" `grep '{id % [1-9]*}' -rl ./yaml/fullrouting-smallshards`
#sed -i "s/{k % [0-9]*}/{k % $4}/g" `grep '{k % [0-9]*}' -rl ./yaml/fullrouting-smallshards`

#sed -i "s/benchmark.smallsharding.table.count=[0-9]*/benchmark.smallsharding.table.count=$4/g" `grep 'benchmark.smallsharding.table.count=[0-9]*' -rl ./config/dbconfig.properties`
