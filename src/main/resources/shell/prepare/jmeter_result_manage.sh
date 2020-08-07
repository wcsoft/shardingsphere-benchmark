# create base dir for benchmark result
if [ ! -d "/export/shardingsphere-benchmark/result"  ];then
  mkdir -p /export/shardingsphere-benchmark/result
fi
chmod -R 777 /export/shardingsphere-benchmark/result

# create sub dir of benchmark result
if [ ! -d "/export/shardingsphere-benchmark/result/fullrouting/encrypt" ];then
  mkdir -p /export/shardingsphere-benchmark/result/fullrouting/encrypt
else
  echo /export/shardingsphere-benchmark/result/fullrouting/encrypt/  exist
  rm -rf /export/shardingsphere-benchmark/result/fullrouting/encrypt/*
fi

if [ ! -d "/export/shardingsphere-benchmark/result/fullrouting/masterslave" ];then
  mkdir -p /export/shardingsphere-benchmark/result/fullrouting/masterslave
else
  echo /export/shardingsphere-benchmark/result/fullrouting/masterslave  exist
  rm -rf /export/shardingsphere-benchmark/result/fullrouting/masterslave/*
fi

if [ ! -d "/export/shardingsphere-benchmark/result/fullrouting/sharding" ];then
  mkdir -p /export/shardingsphere-benchmark/result/fullrouting/sharding
else
  echo /export/shardingsphere-benchmark/result/fullrouting/sharding  exist
  rm -rf /export/shardingsphere-benchmark/result/fullrouting/sharding/*
fi

if [ ! -d "/export/shardingsphere-benchmark/result/fullrouting/shardingmasterslaveencrypt" ];then
  mkdir -p /export/shardingsphere-benchmark/result/fullrouting/shardingmasterslaveencrypt
else
  echo /export/shardingsphere-benchmark/result/fullrouting/shardingmasterslaveencrypt  exist
  rm -rf /export/shardingsphere-benchmark/result/fullrouting/shardingmasterslaveencrypt/*
fi

if [ ! -d "/export/shardingsphere-benchmark/result/rangerouting/encrypt" ];then
  mkdir -p /export/shardingsphere-benchmark/result/rangerouting/encrypt
else
  echo /export/shardingsphere-benchmark/result/rangerouting/encrypt  exist
  rm -rf /export/shardingsphere-benchmark/result/rangerouting/encrypt/*
fi

if [ ! -d "/export/shardingsphere-benchmark/result/rangerouting/masterslave" ];then
  mkdir -p /export/shardingsphere-benchmark/result/rangerouting/masterslave
else
  echo /export/shardingsphere-benchmark/result/rangerouting/masterslave  exist
  rm -rf /export/shardingsphere-benchmark/result/rangerouting/masterslave/*
fi

if [ ! -d "/export/shardingsphere-benchmark/result/rangerouting/sharding" ];then
  mkdir -p /export/shardingsphere-benchmark/result/rangerouting/sharding
else
  echo /export/shardingsphere-benchmark/result/rangerouting/sharding  exist
  rm -rf /export/shardingsphere-benchmark/result/rangerouting/sharding/*
fi

if [ ! -d "/export/shardingsphere-benchmark/result/rangerouting/shardingmasterslaveencrypt" ];then
  mkdir -p /export/shardingsphere-benchmark/result/rangerouting/shardingmasterslaveencrypt
else
  echo /export/shardingsphere-benchmark/result/rangerouting/shardingmasterslaveencrypt  exist
  rm -rf /export/shardingsphere-benchmark/result/rangerouting/shardingmasterslaveencrypt/*
fi

if [ ! -d "/export/shardingsphere-benchmark/result/singlerouting/encrypt" ];then
  mkdir -p /export/shardingsphere-benchmark/result/singlerouting/encrypt
else
  echo /export/shardingsphere-benchmark/result/singlerouting/encrypt  exist
  rm -rf /export/shardingsphere-benchmark/result/singlerouting/encrypt/*
fi

if [ ! -d "/export/shardingsphere-benchmark/result/singlerouting/masterslave" ];then
  mkdir -p /export/shardingsphere-benchmark/result/singlerouting/masterslave
else
  echo /export/shardingsphere-benchmark/result/singlerouting/masterslave  exist
  rm -rf /export/shardingsphere-benchmark/result/singlerouting/masterslave/*
fi

if [ ! -d "/export/shardingsphere-benchmark/result/singlerouting/sharding" ];then
  mkdir -p /export/shardingsphere-benchmark/result/singlerouting/sharding
else
  echo /export/shardingsphere-benchmark/result/singlerouting/sharding  exist
  rm -rf /export/shardingsphere-benchmark/result/singlerouting/sharding/*
fi

if [ ! -d "/export/shardingsphere-benchmark/result/singlerouting/shardingmasterslaveencrypt" ];then
  mkdir -p /export/shardingsphere-benchmark/result/singlerouting/shardingmasterslaveencrypt
else
  echo /export/shardingsphere-benchmark/result/singlerouting/shardingmasterslaveencrypt  exist
  rm -rf /export/shardingsphere-benchmark/result/singlerouting/shardingmasterslaveencrypt/*
fi

chmod -R 777 /export/shardingsphere-benchmark/result