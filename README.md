# What is ShardingSphere Benchmark
ShardingSphere Benchmark is a performance tool to check its benchmark of core scenarios which are in figure below.


|                         | *ShardingSphere-JDBC* | *ShardingSphere-Proxy* | *MYSQL* |
| ----------------------- | --------------------- | ---------------------- | ------------------------ |
| Full Route              | Select                | Select                 | Select                   |
|                         | Insert+Update+Delete  | Insert+Update+Delete   | Insert+Update+Delete     |      
| Range Route             | Select                | Select                 | Select                   |
|                         | Insert+Update+Delete  | Insert+Update+Delete   | Insert+Update+Delete     |
| Single Route            | Select                | Select                 | Select                   |
|                         | Insert+Update+Delete  | Insert+Update+Delete   | Insert+Update+Delete     |

## How to work
ShardingSphere Benchmark is mainly achieved by JMeter in summary, running as command *jmeter -n -t testplan*. The workflow refers to the figure below.

*TODO:image of workflow here*

### Environment Preparation

#### Install MYSQL 
* Get [MYSQL installation](https://dev.mysql.com/downloads/mysql/) from official website depending on the system you use. It's appreciated to choose MYSQL version above 5.
* Install and set up MYSQL according to its [MYSQL Doc](https://dev.mysql.com/doc), please choose right version of MYSQL doc in compliance with your installation.

#### Install JDK
* Get [JDK installation](https://www.oracle.com/java/technologies/javase-downloads.html) from official website depending on the system you use. It's appreciated to choose JDK version above 7.
* Install and set up JDK according to *Installation Instructions* of MYSQL related version section of [JDK installation](https://www.oracle.com/java/technologies/javase-downloads.html).

#### Install Maven
* Get [Maven installation](http://maven.apache.org/download.cgi) from official website depending on the system you use. It's appreciated to choose MYSQL version 3.6.
* Install ans set up Maven according to [Maven doc](http://maven.apache.org/install.html) 

#### Get JDBC Jar
* Get JDBC Jar from official website [JDBC Jar url](https://dev.mysql.com/downloads/connector/j/). Its version depends on MYSQL you use.

#### Build ShardingSphere Benchmark
* Get source code of ShardingSphere from [ShardingSphere Benchmark Code URL](https://github.com/wcsoft/shardingsphere-benchmark).
* Build ShardingSphere Benchmark project by running command below.
```bash
mvn clean install
```

#### Install ShardingSphere-Proxy
* Get source code of ShardingSphere from [ShardingSphere Source Code URL](https://github.com/apache/shardingsphere), the features for different branches refer to [ShardingSphere Doc](https://shardingsphere.apache.org/).
* Get ShardingSphere-Proxy installation by running command below. For ShardingSphere 4.x, it's located at *{project_base_dir}/sharding-distribution/sharding-proxy-distribution/target*; for ShardingSphere 5.x, it's located at  *{project_base_dir}/shardingsphere-distribution/shardingsphere-proxy-distribution/target*.
```bash
mvn clean install -Prelease
```
* Decompress ShardingSphere-proxy above, leave the JDBC jar and ShardingSphere Benchmark jar into *{ShardingSphere-Proxy_base_dir}/lib/*.


#### Install JMeter
* Get [JMeter installation](https://jmeter.apache.org/download_jmeter.cgi) from official website depending on the JDK you use. It's appreciated to choose JMeter version 5.3.
* Install and set up JMeter according to [JMeter doc](https://jmeter.apache.org/usermanual/get-started.html).
* Leave ShardingSphere Benchmark jar into *{JMeter_base_dir}/lib/ext*.


#### Configuration
We have extracted necessary user configuration which is in *{ShardingSphere Benchmark base dir}/src/main/resources/config/user-config.properties*. You have to change configuration list below as yours. By default we configure any ip with host in the code or yaml to prevent ip leakage and cause security problems, ip or host depends on your choice. 

```bash
shardingsphere.project.base.path=/export/jenkins/workspace/ShardingSphere-Benchmark-Deploy
shardingsphere.benchmark.project.base.path=/export/benchmark/shardingsphere-benchmark
shardingsphere.benchmark.result.base.path=/export/shardingsphere-benchmark/result
shardingsphere.benchmark.result.port=3306
shardingsphere.benchmark.result.username=root
shardingsphere.benchmark.result.password=
shardingsphere.benchmark.database.machine.list=ss.benchmark.fullrouting.encrypt.ds0;ss.benchmark.fullrouting.encrypt.ds1;ss.benchmark.fullrouting.encrypt.ds2
shardingsphere.benchmark.result.host=ss.benchmark.result
shardingsphere.benchmark.result.datasource=benchmark_result
```

#### Init Database/Table
We have to prepare database and table for benchmark. They are configured at *{ShardingSphere Benchmark base dir}/src/main/resources/config/user-config.properties*, run the command below to create physical databases and tables.
```bash
jmeter -n -t {ShardingSphere Benchmark base dir}/src/main/resources/testplan/common/initdb/initdb-testplan.jmx
```

#### Init Base Data
All of initing base data test plans for different scenarios are at *{ShardingSphere Benchmark base dir}/src/main/resources/testplan/common/datapreparation*. Here is a command for instance, which scenario to test in final depends on your choice.  
```bash
jmeter -n -t {ShardingSphere Benchmark base dir}/src/main/resources/testplan/common/datapreparation/shardingsphere/sharding/shardingsphere-sharding-createdata-testplan.jmx
```

#### Run a Test Plan
All of test plans for different scenarios are at *{ShardingSphere Benchmark base dir}/src/main/resources/testplan/*. Here is a command for instance, which scenario to test in final depends on your choice.  
```bash
jmeter -n -t {ShardingSphere Benchmark base dir}/src/main/resources/testplan/singlerouting/encrypt/shardingjdbc-singlerouting-encrypt-select-testplan.jmx
```

#### Statistics
Statistics result is stored into excel by running command below. Excel file is at the path you configured in *{ShardingSphere Benchmark base dir}/src/main/resources/config/user-config.properties*.
```bash
jmeter -n -t {ShardingSphere Benchmark base dir}/src/main/resources/testplan/statistic/ss-benchmark-statistic-testplan.jmx
```