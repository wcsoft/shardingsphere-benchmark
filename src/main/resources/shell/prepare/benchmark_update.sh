cd /export/benchmark/shardingsphere-benchmark

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
