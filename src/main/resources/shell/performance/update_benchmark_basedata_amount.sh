benchmark_resource_dir="/export/benchmark/shardingsphere-benchmark/src/main/resources"
chmod -R 777 $benchmark_resource_dir
cd $benchmark_resource_dir
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./testplan/common/datapreparation/shardingsphere/encrypt/shardingsphere-encrypt-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./testplan/common/datapreparation/shardingsphere/encrypt/shardingsphere-encrypt-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./testplan/common/datapreparation/shardingsphere/masterslave/shardingsphere-masterslave-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./testplan/common/datapreparation/shardingsphere/masterslave/shardingsphere-masterslave-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./testplan/common/datapreparation/shardingsphere/sharding/shardingsphere-sharding-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./testplan/common/datapreparation/shardingsphere/sharding/shardingsphere-sharding-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./testplan/common/datapreparation/shardingsphere/sharding-masterslave-encrypt/shardingsphere-sharding-masterslave-encrypt-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./testplan/common/datapreparation/shardingsphere/sharding-masterslave-encrypt/shardingsphere-sharding-masterslave-encrypt-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./testplan/common/datapreparation/jdbc/encrypt/jdbc-encrypt-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./testplan/common/datapreparation/jdbc/encrypt/jdbc-encrypt-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./testplan/common/datapreparation/jdbc/masterslave/jdbc-masterslave-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./testplan/common/datapreparation/jdbc/masterslave/jdbc-masterslave-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./testplan/common/datapreparation/jdbc/sharding/jdbc-sharding-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./testplan/common/datapreparation/jdbc/sharding/jdbc-sharding-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./testplan/common/datapreparation/jdbc/shardingmasterslaveencrypt/jdbc-shardingmasterslaveencrypt-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./testplan/common/datapreparation/jdbc/shardingmasterslaveencrypt/jdbc-shardingmasterslaveencrypt-createdata-testplan.jmx`