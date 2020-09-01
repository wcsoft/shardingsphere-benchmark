#!bin/bash
chmod -R 777 /export/benchmark/shardingsphere-benchmark/src/main/resources/testplan
cd /export/benchmark/shardingsphere-benchmark/src/main/resources/testplan

sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./fullrouting`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./fullrouting`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./rangerouting`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./rangerouting`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl ./singlerouting`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl ./singlerouting`
sed -i "s/ss.benchmark.result.concurrency=[0-9]*/ss.benchmark.result.concurrency=$2/g" `grep 'ss.benchmark.result.concurrency=[0-9]*' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/config/benchmark-result.properties`