package org.apache.shardingsphere.benchmark.common.statistic;

import org.apache.shardingsphere.benchmark.bean.BenchmarkResultBean;
import org.apache.shardingsphere.benchmark.common.resultParser.BenchmarkResultParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BenchmarkFullroutingStatistic {
    
    public static List<BenchmarkResultBean> fullRoutingResult = new ArrayList<BenchmarkResultBean>();
    
    public static List<BenchmarkResultBean> calculateFullroutingScenarioResult(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion) {
        calculateFullroutingEncrypt(benchmarkResultPath, sqlConfig, benchmarkVersion);
        calculateFullroutingMasterslave(benchmarkResultPath, sqlConfig, benchmarkVersion);
        calculateFullroutingSharding(benchmarkResultPath, sqlConfig, benchmarkVersion);
        calculateFullroutingShardingMasterslaveEncrypt(benchmarkResultPath, sqlConfig, benchmarkVersion);
        return fullRoutingResult;
    }
    
    public static void calculateFullroutingEncrypt(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion) {
        String proxyFullRoutingEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.encrypt.insertupdatedelete.result");
        Map proxyFullRoutingEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingEncryptInsertupdatedeleteResultPath);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.fullrouting.encrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.fullrouting.encrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.fullrouting.encrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean proxyFullRoutingEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxyFullRoutingEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Encrypt", "ShardingProxy", "fullrouting", "Insert+Update+delete");
        System.out.println(proxyFullRoutingEncryptInsertupdatedeleteResult.toString());
        
        String shardingjdbcFullRoutingEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.encrypt.insertupdatedelete.result");
        Map shardingjdbcFullRoutingEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingEncryptInsertupdatedeleteResultPath);
        BenchmarkResultBean shardingjdbcFullRoutingEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Encrypt", "ShardingJDBC", "fullrouting", "Insert+Update+delete");
        System.out.println(shardingjdbcFullRoutingEncryptInsertupdatedeleteResult.toString());
        
        String jdbcFullRoutingEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.encrypt.insertupdatedelete.result");
        Map jdbcFullRoutingEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingEncryptInsertupdatedeleteResultPath);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.fullrouting.encrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.fullrouting.encrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.fullrouting.encrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcFullRoutingEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingEncryptInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "Encrypt", "MYSQL", "fullrouting", "Insert+Update+delete");
        System.out.println(jdbcFullRoutingEncryptInsertupdatedeleteResult.toString());
        
        String proxyFullRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.encrypt.select.result");
        Map proxyFullRoutingEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingEncryptSelectResultPath);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.encrypt.select.sql");
        BenchmarkResultBean proxyFullRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyFullRoutingEncryptSelectResult, ssSelectSql, "Encrypt", "ShardingProxy", "fullrouting", "Select");
        System.out.println(proxyFullRoutingEncryptSelectResult.toString());
        
        String shardingjdbcFullRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.encrypt.select.result");
        Map shardingjdbcFullRoutingEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingEncryptSelectResultPath);
        BenchmarkResultBean shardingjdbcFullRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingEncryptSelectResult, ssSelectSql, "Encrypt", "ShardingJDBC", "fullrouting", "Select");
        System.out.println(shardingjdbcFullRoutingEncryptSelectResult.toString());
        
        String jdbcFullRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.encrypt.select.result");
        Map jdbcFullRoutingEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingEncryptSelectResultPath);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.fullrouting.encrypt.select.sql");
        BenchmarkResultBean jdbcFullRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingEncryptSelectResult, jdbcSelectSql, "Encrypt", "MYSQL", "fullrouting", "Select");
        System.out.println(jdbcFullRoutingEncryptSelectResult.toString());
        fullRoutingResult.add(proxyFullRoutingEncryptInsertupdatedeleteResultBean);
        fullRoutingResult.add(shardingjdbcFullRoutingEncryptInsertupdatedeleteResultBean);
        fullRoutingResult.add(jdbcFullRoutingEncryptInsertupdatedeleteResultBean);
        fullRoutingResult.add(proxyFullRoutingEncryptSelectResultBean);
        fullRoutingResult.add(shardingjdbcFullRoutingEncryptSelectResultBean);
        fullRoutingResult.add(jdbcFullRoutingEncryptSelectResultBean);
    }
    
    /**
     *
     */
    public static void calculateFullroutingMasterslave(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion) {
        String proxyFullRoutingMasterSlaveInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.masterslave.insertupdatedelete.result");
        Map proxyFullRoutingMasterSlaveInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingMasterSlaveInsertupdatedeleteResultPath);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.fullrouting.masterslave.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.fullrouting.masterslave.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.fullrouting.masterslave.update.sql") +  ";\r\n";
        BenchmarkResultBean proxyFullRoutingMasterSlaveInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxyFullRoutingMasterSlaveInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "MasterSlave", "ShardingProxy", "fullrouting", "Insert+Update+delete");
        System.out.println(proxyFullRoutingMasterSlaveInsertupdatedeleteResult.toString());
        
        String shardingjdbcFullRoutingMasterSlaveInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.masterslave.insertupdatedelete.result");
        Map shardingjdbcFullRoutingMasterSlaveInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingMasterSlaveInsertupdatedeleteResultPath);
        BenchmarkResultBean shardingjdbcFullRoutingMasterSlaveInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingMasterSlaveInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "MasterSlave", "ShardingJDBC", "fullrouting", "Insert+Update+delete");
        System.out.println(shardingjdbcFullRoutingMasterSlaveInsertupdatedeleteResult.toString());
        
        String jdbcFullRoutingMasterSlaveInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.masterslave.insertupdatedelete.result");
        Map jdbcFullRoutingMasterSlaveInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingMasterSlaveInsertupdatedeleteResultPath);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.fullrouting.masterslave.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.fullrouting.masterslave.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.fullrouting.masterslave.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcFullRoutingMasterSlaveInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingMasterSlaveInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "MasterSlave", "MYSQL", "fullrouting", "Insert+Update+delete");
        System.out.println(jdbcFullRoutingMasterSlaveInsertupdatedeleteResult.toString());
        
        String proxyFullRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.masterslave.select.result");
        Map proxyFullRoutingMasterSlaveSelectResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingMasterSlaveSelectResultPath);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.masterslave.select.sql");
        BenchmarkResultBean proxyFullRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyFullRoutingMasterSlaveSelectResult, ssSelectSql, "MasterSlave", "ShardingProxy", "fullrouting", "Select");
        System.out.println(proxyFullRoutingMasterSlaveSelectResult.toString());
        
        String shardingjdbcFullRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.masterslave.select.result");
        Map shardingjdbcFullRoutingMasterSlaveSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingMasterSlaveSelectResultPath);
        BenchmarkResultBean shardingjdbcFullRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingMasterSlaveSelectResult, ssSelectSql, "MasterSlave", "ShardingJDBC", "fullrouting", "Select");
        System.out.println(shardingjdbcFullRoutingMasterSlaveSelectResult.toString());
        
        String jdbcFullRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.masterslave.select.result");
        Map jdbcFullRoutingMasterSlaveSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingMasterSlaveSelectResultPath);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.fullrouting.masterslave.select.sql");
        BenchmarkResultBean jdbcFullRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingMasterSlaveSelectResult, jdbcSelectSql, "MasterSlave", "MYSQL", "fullrouting", "Select");
        System.out.println(jdbcFullRoutingMasterSlaveSelectResult.toString());
        fullRoutingResult.add(proxyFullRoutingMasterSlaveInsertupdatedeleteResultBean);
        fullRoutingResult.add(shardingjdbcFullRoutingMasterSlaveInsertupdatedeleteResultBean);
        fullRoutingResult.add(jdbcFullRoutingMasterSlaveInsertupdatedeleteResultBean);
        fullRoutingResult.add(proxyFullRoutingMasterSlaveSelectResultBean);
        fullRoutingResult.add(shardingjdbcFullRoutingMasterSlaveSelectResultBean);
        fullRoutingResult.add(jdbcFullRoutingMasterSlaveSelectResultBean);
    }
    
    /**
     *
     */
    public static void calculateFullroutingSharding(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion) {
        String proxyFullRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.sharding.insertupdatedelete.result");
        Map proxyFullRoutingShardingInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingShardingInsertupdatedeleteResultPath);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.fullrouting.sharding.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.fullrouting.sharding.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.fullrouting.sharding.update.sql") +  ";\r\n";
        BenchmarkResultBean proxyFullRoutingShardingInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxyFullRoutingShardingInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding", "ShardingProxy", "fullrouting", "Insert+Update+delete");
        System.out.println(proxyFullRoutingShardingInsertupdatedeleteResult.toString());
        
        String shardingjdbcFullRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.sharding.insertupdatedelete.result");
        Map shardingjdbcFullRoutingShardingInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingShardingInsertupdatedeleteResultPath);
        BenchmarkResultBean shardingjdbcFullRoutingShardingInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingShardingInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding", "ShardingJDBC", "fullrouting", "Insert+Update+delete");
        System.out.println(shardingjdbcFullRoutingShardingInsertupdatedeleteResult.toString());
        
        String jdbcFullRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.sharding.insertupdatedelete.result");
        Map jdbcFullRoutingShardingInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingShardingInsertupdatedeleteResultPath);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.fullrouting.sharding.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.fullrouting.sharding.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.fullrouting.sharding.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcFullRoutingShardingInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingShardingInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "Sharding", "MYSQL", "fullrouting", "Insert+Update+delete");
        System.out.println(jdbcFullRoutingShardingInsertupdatedeleteResult.toString());
        
        String proxyFullRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.sharding.select.result");
        Map proxyFullRoutingShardingSelectResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingShardingSelectResultPath);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.sharding.select.sql");
        BenchmarkResultBean proxyFullRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyFullRoutingShardingSelectResult, ssSelectSql, "Sharding", "ShardingProxy", "fullrouting", "Select");
        System.out.println(proxyFullRoutingShardingSelectResult.toString());
        
        String shardingjdbcFullRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.sharding.select.result");
        Map shardingjdbcFullRoutingShardingSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingShardingSelectResultPath);
        BenchmarkResultBean shardingjdbcFullRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingShardingSelectResult, ssSelectSql, "Sharding", "ShardingJDBC", "fullrouting", "Select");
        System.out.println(shardingjdbcFullRoutingShardingSelectResult.toString());
        
        String jdbcFullRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.sharding.select.result");
        Map jdbcFullRoutingShardingSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingShardingSelectResultPath);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.fullrouting.sharding.select.sql");
        BenchmarkResultBean jdbcFullRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingShardingSelectResult, jdbcSelectSql, "Sharding", "MYSQL", "fullrouting", "Select");
        System.out.println(jdbcFullRoutingShardingSelectResult.toString());
        fullRoutingResult.add(proxyFullRoutingShardingInsertupdatedeleteResultBean);
        fullRoutingResult.add(shardingjdbcFullRoutingShardingInsertupdatedeleteResultBean);
        fullRoutingResult.add(jdbcFullRoutingShardingInsertupdatedeleteResultBean);
        fullRoutingResult.add(proxyFullRoutingShardingSelectResultBean);
        fullRoutingResult.add(shardingjdbcFullRoutingShardingSelectResultBean);
        fullRoutingResult.add(jdbcFullRoutingShardingSelectResultBean);
    }
    
    /**
     *
     */
    public static void calculateFullroutingShardingMasterslaveEncrypt(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion) {
        String proxyFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        Map proxyFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.fullrouting.shardingmasterslaveencrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.fullrouting.shardingmasterslaveencrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.fullrouting.shardingmasterslaveencrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean proxyFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxyFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding+Master+Slave+Encrypt", "ShardingProxy", "fullrouting", "Insert+Update+delete");
        System.out.println(proxyFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult.toString());
        
        String shardingjdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        Map shardingjdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath);
        BenchmarkResultBean shardingjdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding+Master+Slave+Encrypt", "ShardingJDBC", "fullrouting", "Insert+Update+delete");
        System.out.println(shardingjdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult.toString());
        
        String jdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        Map jdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.fullrouting.shardingmasterslaveencrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.fullrouting.shardingmasterslaveencrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.fullrouting.shardingmasterslaveencrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "Sharding+Master+Slave+Encrypt", "MYSQL", "fullrouting", "Insert+Update+delete");
        System.out.println(jdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult.toString());
        
        String proxyFullRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.shardingmasterslaveencrypt.select.result");
        Map proxyFullRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingShardingMasterSlaveEncryptSelectResultPath);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.shardingmasterslaveencrypt.select.sql");
        BenchmarkResultBean proxyFullRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyFullRoutingShardingMasterSlaveEncryptSelectResult, ssSelectSql, "Sharding+Master+Slave+Encrypt", "ShardingProxy", "fullrouting", "Select");
        System.out.println(proxyFullRoutingShardingMasterSlaveEncryptSelectResult.toString());
        
        String shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.shardingmasterslaveencrypt.select.result");
        Map shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResultPath);
        BenchmarkResultBean shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResult, ssSelectSql, "Sharding+Master+Slave+Encrypt", "ShardingJDBC", "fullrouting", "Select");
        System.out.println(shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResult.toString());
        
        String jdbcFullRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.shardingmasterslaveencrypt.select.result");
        Map jdbcFullRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingShardingMasterSlaveEncryptSelectResultPath);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.fullrouting.shardingmasterslaveencrypt.select.sql");
        BenchmarkResultBean jdbcFullRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingShardingMasterSlaveEncryptSelectResult, jdbcSelectSql, "Sharding+Master+Slave+Encrypt", "MYSQL", "fullrouting", "Select");
        System.out.println(jdbcFullRoutingShardingMasterSlaveEncryptSelectResult.toString());
        fullRoutingResult.add(proxyFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean);
        fullRoutingResult.add(shardingjdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean);
        fullRoutingResult.add(jdbcFullRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean);
        fullRoutingResult.add(proxyFullRoutingShardingMasterSlaveEncryptSelectResultBean);
        fullRoutingResult.add(shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResultBean);
        fullRoutingResult.add(jdbcFullRoutingShardingMasterSlaveEncryptSelectResultBean);
    }
}
