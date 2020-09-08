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

*image of workflow here*

### Environment Preparation

#### Install MYSQL 
* Get [MYSQL installation](https://dev.mysql.com/downloads/mysql/) from official website depending on the system you use. It's appreciated that choose MYSQL version above 5.
* Install MYSQL 
* Install JDK
* Install JMeter
* Checkout Sharding Sphere project to build to get Sharding-Proxy.

### Configuration in necessary
Extract user configuration

## Quick Start
