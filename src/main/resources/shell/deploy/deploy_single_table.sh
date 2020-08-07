ss_version=`head -n +1 /home/jenkins/BT_jenkins/ss_build_version.log`
echo $ss_version

if [ $ss_version == 5.0 ];then
  echo "5.0"
  sh /home/jenkins/BT_jenkins/deploy_single_table_5.0.sh
elif [ $ss_version == 4.1.1 ];then
  echo "4.1.1"
  sh /home/jenkins/BT_jenkins/deploy_single_table_4.0.sh
else
  echo "default version"
  sh /home/jenkins/BT_jenkins/deploy_single_table_5.0.sh
fi
