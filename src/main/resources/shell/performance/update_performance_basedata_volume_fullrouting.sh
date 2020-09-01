chmod -R 777 /home/jenkins/apache-jmeter-4.0/shardingsphere_test_plan
cd /home/jenkins/apache-jmeter-4.0/shardingsphere_test_plan
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./common/ss/encrypt/ss-encrypt-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./common/ss/encrypt/ss-encrypt-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./common/ss/masterslave/ss-masterslave-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./common/ss/masterslave/ss-masterslave-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./common/ss/sharding/ss-sharding-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./common/ss/sharding/ss-sharding-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./common/ss/sharding-masterslave-encrypt/ss-sharding-masterslave-encrypt-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./common/ss/sharding-masterslave-encrypt/ss-sharding-masterslave-encrypt-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./common/jdbc/encrypt/jdbc-encrypt-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./common/jdbc/encrypt/jdbc-encrypt-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./common/jdbc/masterslave/jdbc-masterslave-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./common/jdbc/masterslave/jdbc-masterslave-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./common/jdbc/sharding/jdbc-sharding-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./common/jdbc/sharding/jdbc-sharding-createdata-testplan.jmx`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./common/jdbc/shardingmasterslaveencrypt/jdbc-shardingmasterslaveencrypt-createdata-testplan.jmx`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./common/jdbc/shardingmasterslaveencrypt/jdbc-shardingmasterslaveencrypt-createdata-testplan.jmx`