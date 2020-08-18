chmod -R 777 /export/benchmark/shardingsphere-benchmark
cd /export/benchmark/shardingsphere-benchmark

if [ -f ./ss_build_version.log ]; then
  rm ./ss_build_version.log
fi
echo $1 > ./ss_build_version.log
sleep 2s
sed -i "s/ss.benchmark.version=[^0-9.]*\([0-9.]*\)\([0-9.]*\)./ss.benchmark.version=$1/g" `grep 'ss.benchmark.version=[^0-9.]*\([0-9.]*\)\([0-9.]*\)' -rl /export/benchmark/shardingsphere-benchmark/src/main/resources/config/benchmark-version.properties`

ss_version=`head -n +1 ./ss_build_version.log`

if [ $ss_version == 5.0 ];then
  echo "5.0"
  git reset --hard origin/master
  git pull origin master
elif [ $ss_version == 4.1.1 ];then
  echo "4.1.1"
  git reset --hard origin/4.1.1
  git pull origin 4.1.1
else
  echo "default version"
  git reset --hard origin/master
  git pull origin master
fi
