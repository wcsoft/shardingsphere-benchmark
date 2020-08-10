package org.apache.shardingsphere.benchmark.common.statistic;

import org.apache.shardingsphere.benchmark.bean.BenchmarkResultBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BenchmarkRangeroutingStatistic {
    
    public static List<BenchmarkResultBean> rangeRoutingResult = new ArrayList<BenchmarkResultBean>();
    
    public static List<BenchmarkResultBean> calculateRangeRoutingScenarioResult(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion) {
        calculateRangeroutingEncrypt(benchmarkResultPath, sqlConfig, benchmarkVersion);
        calculateRangeRoutingMasterslave(benchmarkResultPath, sqlConfig, benchmarkVersion);
        calculateRangeRoutingSharding(benchmarkResultPath, sqlConfig, benchmarkVersion);
        calculateRangeRoutingShardingMasterslaveEncrypt(benchmarkResultPath, sqlConfig, benchmarkVersion);
        return rangeRoutingResult;
    }
    
    public static void calculateRangeroutingEncrypt(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion) {
        String proxyRangeRoutingEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.encrypt.insertupdatedelete.result");
        Map proxyRangeRoutingEncryptInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(proxyRangeRoutingEncryptInsertupdatedeleteResultPath);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.rangerouting.encrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.rangerouting.encrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.rangerouting.encrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean proxyRangeRoutingEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxyRangeRoutingEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Encrypt", "ShardingProxy", "RangeRouting", "Insert+Update+delete");
        System.out.println(proxyRangeRoutingEncryptInsertupdatedeleteResult.toString());
        
        String shardingjdbcRangeRoutingEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.encrypt.insertupdatedelete.result");
        Map shardingjdbcRangeRoutingEncryptInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcRangeRoutingEncryptInsertupdatedeleteResultPath);
        BenchmarkResultBean shardingjdbcRangeRoutingEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcRangeRoutingEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Encrypt", "ShardingJDBC", "RangeRouting", "Insert+Update+delete");
        System.out.println(shardingjdbcRangeRoutingEncryptInsertupdatedeleteResult.toString());
        
        String jdbcRangeRoutingEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.encrypt.insertupdatedelete.result");
        Map jdbcRangeRoutingEncryptInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcRangeRoutingEncryptInsertupdatedeleteResultPath);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.rangerouting.encrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.rangerouting.encrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.rangerouting.encrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcRangeRoutingEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcRangeRoutingEncryptInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "Encrypt", "MYSQL", "RangeRouting", "Insert+Update+delete");
        System.out.println(jdbcRangeRoutingEncryptInsertupdatedeleteResult.toString());
        
        String proxyRangeRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.encrypt.select.result");
        Map proxyRangeRoutingEncryptSelectResult = BenchmarkResultStatistic.benchmarkStatistic(proxyRangeRoutingEncryptSelectResultPath);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.rangerouting.encrypt.select.sql");
        BenchmarkResultBean proxyRangeRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyRangeRoutingEncryptSelectResult, ssSelectSql, "Encrypt", "ShardingProxy", "RangeRouting", "Select");
        System.out.println(proxyRangeRoutingEncryptSelectResult.toString());
        
        String shardingjdbcRangeRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.encrypt.select.result");
        Map shardingjdbcRangeRoutingEncryptSelectResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcRangeRoutingEncryptSelectResultPath);
        BenchmarkResultBean shardingjdbcRangeRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcRangeRoutingEncryptSelectResult, ssSelectSql, "Encrypt", "ShardingJDBC", "RangeRouting", "Select");
        System.out.println(shardingjdbcRangeRoutingEncryptSelectResult.toString());
        
        String jdbcRangeRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.encrypt.select.result");
        Map jdbcRangeRoutingEncryptSelectResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcRangeRoutingEncryptSelectResultPath);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.rangerouting.encrypt.select.sql");
        BenchmarkResultBean jdbcRangeRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcRangeRoutingEncryptSelectResult, jdbcSelectSql, "Encrypt", "MYSQL", "RangeRouting", "Select");
        System.out.println(jdbcRangeRoutingEncryptSelectResult.toString());
        rangeRoutingResult.add(proxyRangeRoutingEncryptInsertupdatedeleteResultBean);
        rangeRoutingResult.add(shardingjdbcRangeRoutingEncryptInsertupdatedeleteResultBean);
        rangeRoutingResult.add(jdbcRangeRoutingEncryptInsertupdatedeleteResultBean);
        rangeRoutingResult.add(proxyRangeRoutingEncryptSelectResultBean);
        rangeRoutingResult.add(shardingjdbcRangeRoutingEncryptSelectResultBean);
        rangeRoutingResult.add(jdbcRangeRoutingEncryptSelectResultBean);
    }
    
    /**
     *
     */
    public static void calculateRangeRoutingMasterslave(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion) {
        String proxyRangeRoutingMasterSlaveInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.masterslave.insertupdatedelete.result");
        Map proxyRangeRoutingMasterSlaveInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(proxyRangeRoutingMasterSlaveInsertupdatedeleteResultPath);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.rangerouting.masterslave.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.rangerouting.masterslave.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.rangerouting.masterslave.update.sql") +  ";\r\n";
        BenchmarkResultBean proxyRangeRoutingMasterSlaveInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxyRangeRoutingMasterSlaveInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "MasterSlave", "ShardingProxy", "RangeRouting", "Insert+Update+delete");
        System.out.println(proxyRangeRoutingMasterSlaveInsertupdatedeleteResult.toString());
        
        String shardingjdbcRangeRoutingMasterSlaveInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.masterslave.insertupdatedelete.result");
        Map shardingjdbcRangeRoutingMasterSlaveInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcRangeRoutingMasterSlaveInsertupdatedeleteResultPath);
        BenchmarkResultBean shardingjdbcRangeRoutingMasterSlaveInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcRangeRoutingMasterSlaveInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "MasterSlave", "ShardingJDBC", "RangeRouting", "Insert+Update+delete");
        System.out.println(shardingjdbcRangeRoutingMasterSlaveInsertupdatedeleteResult.toString());
        
        String jdbcRangeRoutingMasterSlaveInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.masterslave.insertupdatedelete.result");
        Map jdbcRangeRoutingMasterSlaveInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcRangeRoutingMasterSlaveInsertupdatedeleteResultPath);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.rangerouting.masterslave.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.rangerouting.masterslave.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.rangerouting.masterslave.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcRangeRoutingMasterSlaveInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcRangeRoutingMasterSlaveInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "MasterSlave", "MYSQL", "RangeRouting", "Insert+Update+delete");
        System.out.println(jdbcRangeRoutingMasterSlaveInsertupdatedeleteResult.toString());
        
        String proxyRangeRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.masterslave.select.result");
        Map proxyRangeRoutingMasterSlaveSelectResult = BenchmarkResultStatistic.benchmarkStatistic(proxyRangeRoutingMasterSlaveSelectResultPath);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.rangerouting.masterslave.select.sql");
        BenchmarkResultBean proxyRangeRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyRangeRoutingMasterSlaveSelectResult, ssSelectSql, "MasterSlave", "ShardingProxy", "RangeRouting", "Select");
        System.out.println(proxyRangeRoutingMasterSlaveSelectResult.toString());
        
        String shardingjdbcRangeRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.masterslave.select.result");
        Map shardingjdbcRangeRoutingMasterSlaveSelectResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcRangeRoutingMasterSlaveSelectResultPath);
        BenchmarkResultBean shardingjdbcRangeRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcRangeRoutingMasterSlaveSelectResult, ssSelectSql, "MasterSlave", "ShardingJDBC", "RangeRouting", "Select");
        System.out.println(shardingjdbcRangeRoutingMasterSlaveSelectResult.toString());
        
        String jdbcRangeRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.masterslave.select.result");
        Map jdbcRangeRoutingMasterSlaveSelectResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcRangeRoutingMasterSlaveSelectResultPath);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.rangerouting.masterslave.select.sql");
        BenchmarkResultBean jdbcRangeRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcRangeRoutingMasterSlaveSelectResult, jdbcSelectSql, "MasterSlave", "MYSQL", "RangeRouting", "Select");
        System.out.println(jdbcRangeRoutingMasterSlaveSelectResult.toString());
        rangeRoutingResult.add(proxyRangeRoutingMasterSlaveInsertupdatedeleteResultBean);
        rangeRoutingResult.add(shardingjdbcRangeRoutingMasterSlaveInsertupdatedeleteResultBean);
        rangeRoutingResult.add(jdbcRangeRoutingMasterSlaveInsertupdatedeleteResultBean);
        rangeRoutingResult.add(proxyRangeRoutingMasterSlaveSelectResultBean);
        rangeRoutingResult.add(shardingjdbcRangeRoutingMasterSlaveSelectResultBean);
        rangeRoutingResult.add(jdbcRangeRoutingMasterSlaveSelectResultBean);
    }
    
    /**
     *
     */
    public static void calculateRangeRoutingSharding(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion) {
        String proxyRangeRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.sharding.insertupdatedelete.result");
        Map proxyRangeRoutingShardingInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(proxyRangeRoutingShardingInsertupdatedeleteResultPath);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.rangerouting.sharding.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.rangerouting.sharding.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.rangerouting.sharding.update.sql") +  ";\r\n";
        BenchmarkResultBean proxyRangeRoutingShardingInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxyRangeRoutingShardingInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding", "ShardingProxy", "RangeRouting", "Insert+Update+delete");
        System.out.println(proxyRangeRoutingShardingInsertupdatedeleteResult.toString());
        
        String shardingjdbcRangeRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.sharding.insertupdatedelete.result");
        Map shardingjdbcRangeRoutingShardingInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcRangeRoutingShardingInsertupdatedeleteResultPath);
        BenchmarkResultBean shardingjdbcRangeRoutingShardingInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcRangeRoutingShardingInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding", "ShardingJDBC", "RangeRouting", "Insert+Update+delete");
        System.out.println(shardingjdbcRangeRoutingShardingInsertupdatedeleteResult.toString());
        
        String jdbcRangeRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.sharding.insertupdatedelete.result");
        Map jdbcRangeRoutingShardingInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcRangeRoutingShardingInsertupdatedeleteResultPath);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.rangerouting.sharding.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.rangerouting.sharding.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.rangerouting.sharding.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcRangeRoutingShardingInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcRangeRoutingShardingInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "Sharding", "MYSQL", "RangeRouting", "Insert+Update+delete");
        System.out.println(jdbcRangeRoutingShardingInsertupdatedeleteResult.toString());
        
        String proxyRangeRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.sharding.select.result");
        Map proxyRangeRoutingShardingSelectResult = BenchmarkResultStatistic.benchmarkStatistic(proxyRangeRoutingShardingSelectResultPath);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.rangerouting.sharding.select.sql");
        BenchmarkResultBean proxyRangeRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyRangeRoutingShardingSelectResult, ssSelectSql, "Sharding", "ShardingProxy", "RangeRouting", "Select");
        System.out.println(proxyRangeRoutingShardingSelectResult.toString());
        
        String shardingjdbcRangeRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.sharding.select.result");
        Map shardingjdbcRangeRoutingShardingSelectResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcRangeRoutingShardingSelectResultPath);
        BenchmarkResultBean shardingjdbcRangeRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcRangeRoutingShardingSelectResult, ssSelectSql, "Sharding", "ShardingJDBC", "RangeRouting", "Select");
        System.out.println(shardingjdbcRangeRoutingShardingSelectResult.toString());
        
        String jdbcRangeRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.sharding.select.result");
        Map jdbcRangeRoutingShardingSelectResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcRangeRoutingShardingSelectResultPath);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.rangerouting.sharding.select.sql");
        BenchmarkResultBean jdbcRangeRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcRangeRoutingShardingSelectResult, jdbcSelectSql, "Sharding", "MYSQL", "RangeRouting", "Select");
        System.out.println(jdbcRangeRoutingShardingSelectResult.toString());
        rangeRoutingResult.add(proxyRangeRoutingShardingInsertupdatedeleteResultBean);
        rangeRoutingResult.add(shardingjdbcRangeRoutingShardingInsertupdatedeleteResultBean);
        rangeRoutingResult.add(jdbcRangeRoutingShardingInsertupdatedeleteResultBean);
        rangeRoutingResult.add(proxyRangeRoutingShardingSelectResultBean);
        rangeRoutingResult.add(shardingjdbcRangeRoutingShardingSelectResultBean);
        rangeRoutingResult.add(jdbcRangeRoutingShardingSelectResultBean);
    }
    
    /**
     *
     */
    public static void calculateRangeRoutingShardingMasterslaveEncrypt(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion) {
        String proxyRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        Map proxyRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(proxyRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.rangerouting.shardingmasterslaveencrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.rangerouting.shardingmasterslaveencrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.rangerouting.shardingmasterslaveencrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean proxyRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxyRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding+Master+Slave+Encrypt", "ShardingProxy", "RangeRouting", "Insert+Update+delete");
        System.out.println(proxyRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult.toString());
        
        String shardingjdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingmasterslaveencrypt.dbc.rangerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        Map shardingjdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath);
        BenchmarkResultBean shardingjdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding+Master+Slave+Encrypt", "ShardingJDBC", "RangeRouting", "Insert+Update+delete");
        System.out.println(shardingjdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult.toString());
        
        String jdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        Map jdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "Sharding+Master+Slave+Encrypt", "MYSQL", "RangeRouting", "Insert+Update+delete");
        System.out.println(jdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult.toString());
        
        String proxyRangeRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.shardingmasterslaveencrypt.select.result");
        Map proxyRangeRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultStatistic.benchmarkStatistic(proxyRangeRoutingShardingMasterSlaveEncryptSelectResultPath);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.rangerouting.shardingmasterslaveencrypt.select.sql");
        BenchmarkResultBean proxyRangeRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyRangeRoutingShardingMasterSlaveEncryptSelectResult, ssSelectSql, "Sharding+Master+Slave+Encrypt", "ShardingProxy", "RangeRouting", "Select");
        System.out.println(proxyRangeRoutingShardingMasterSlaveEncryptSelectResult.toString());
        
        String shardingjdbcRangeRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingmasterslaveencrypt.dbc.rangerouting.shardingmasterslaveencrypt.select.result");
        Map shardingjdbcRangeRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcRangeRoutingShardingMasterSlaveEncryptSelectResultPath);
        BenchmarkResultBean shardingjdbcRangeRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcRangeRoutingShardingMasterSlaveEncryptSelectResult, ssSelectSql, "Sharding+Master+Slave+Encrypt", "ShardingJDBC", "RangeRouting", "Select");
        System.out.println(shardingjdbcRangeRoutingShardingMasterSlaveEncryptSelectResult.toString());
        
        String jdbcRangeRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.shardingmasterslaveencrypt.select.result");
        Map jdbcRangeRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcRangeRoutingShardingMasterSlaveEncryptSelectResultPath);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.select.sql");
        BenchmarkResultBean jdbcRangeRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcRangeRoutingShardingMasterSlaveEncryptSelectResult, jdbcSelectSql, "Sharding+Master+Slave+Encrypt", "MYSQL", "RangeRouting", "Select");
        System.out.println(jdbcRangeRoutingShardingMasterSlaveEncryptSelectResult.toString());
        rangeRoutingResult.add(proxyRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean);
        rangeRoutingResult.add(shardingjdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean);
        rangeRoutingResult.add(jdbcRangeRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean);
        rangeRoutingResult.add(proxyRangeRoutingShardingMasterSlaveEncryptSelectResultBean);
        rangeRoutingResult.add(shardingjdbcRangeRoutingShardingMasterSlaveEncryptSelectResultBean);
        rangeRoutingResult.add(jdbcRangeRoutingShardingMasterSlaveEncryptSelectResultBean);
    }
}
