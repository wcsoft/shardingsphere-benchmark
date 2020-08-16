#!bin/bash
cd /export/benchmark/shardingsphere-benchmark/src/main/resources/testplan/
chmod -R 777 /export/benchmark/shardingsphere-benchmark/src/main/resources/testplan
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/testplan/fullrouting`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/testplan/fullrouting`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/testplan/rangerouting`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/testplan/rangerouting`
sed -i "s/\"ThreadGroup.num_threads\">[0-9]*/\"ThreadGroup.num_threads\">$2/g" `grep '"ThreadGroup.num_threads">[0-9]*' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/testplan/singlerouting`
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/testplan/singlerouting`