chmod -R 777 /export/benchmark/shardingsphere-benchmark/src/main/resources
sed -i "s/\"LoopController.loops\">[0-9]*/\"LoopController.loops\">$1/g" `grep '"LoopController.loops">[0-9]*' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/testplan/common/ss/encrypt/ss-encrypt-createdata-testplan.jmx`
