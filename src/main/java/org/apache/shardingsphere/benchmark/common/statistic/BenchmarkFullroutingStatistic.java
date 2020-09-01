package org.apache.shardingsphere.benchmark.common.statistic;

import org.apache.shardingsphere.benchmark.bean.BenchmarkResultBean;
import org.apache.shardingsphere.benchmark.common.file.jmeter.BenchmarkResultParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BenchmarkFullroutingStatistic {
    
    public static List<BenchmarkResultBean> fullRoutingResult = new ArrayList<BenchmarkResultBean>();
    
    public static List<BenchmarkResultBean> calculateFullroutingScenarioResult(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion, int skipBegin, int skipEnd, int concurency, long updateTime, int dbShardingCount, int tableShardingCount) {
        calculateFullroutingEncrypt(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd, concurency, updateTime, dbShardingCount, tableShardingCount);
        calculateFullroutingMasterslave(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd, concurency, updateTime, dbShardingCount, tableShardingCount);
        calculateFullroutingSharding(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd, concurency, updateTime, dbShardingCount, tableShardingCount);
        calculateFullroutingShardingMasterslaveEncrypt(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd, concurency, updateTime, dbShardingCount, tableShardingCount);
        return fullRoutingResult;
    }
    
    public static void calculateFullroutingEncrypt(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion, int skipBegin, int skipEnd, int concurency, long updateTime, int dbShardingCount, int tableShardingCount) {
        
        String proxyFullRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.encrypt.select.result");
        Map proxyFullRoutingEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingEncryptSelectResultPath, skipBegin, skipEnd);
        if (proxyFullRoutingEncryptSelectResult.size() > 0){
            String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.encrypt.select.sql");
            BenchmarkResultBean proxyFullRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyFullRoutingEncryptSelectResult, ssSelectSql, "Encrypt", "ShardingProxy", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(proxyFullRoutingEncryptSelectResultBean);
        }
        String shardingjdbcFullRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.encrypt.select.result");
        Map shardingjdbcFullRoutingEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingEncryptSelectResultPath, skipBegin, skipEnd);
        if (shardingjdbcFullRoutingEncryptSelectResult.size() > 0){
            String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.encrypt.select.sql");
            BenchmarkResultBean shardingjdbcFullRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingEncryptSelectResult, ssSelectSql, "Encrypt", "ShardingJDBC", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(shardingjdbcFullRoutingEncryptSelectResultBean);
        }
        String jdbcFullRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.encrypt.select.result");
        Map jdbcFullRoutingEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingEncryptSelectResultPath, skipBegin, skipEnd);
        if (jdbcFullRoutingEncryptSelectResult.size() > 0){
            String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.fullrouting.encrypt.select.sql");
            BenchmarkResultBean jdbcFullRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingEncryptSelectResult, jdbcSelectSql, "Encrypt", "MYSQL", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(jdbcFullRoutingEncryptSelectResultBean);
        }
    }
    
    /**
     *
     */
    public static void calculateFullroutingMasterslave(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion, int skipBegin, int skipEnd, int concurency, long updateTime, int dbShardingCount, int tableShardingCount) {
        
        String proxyFullRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.masterslave.select.result");
        Map proxyFullRoutingMasterSlaveSelectResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingMasterSlaveSelectResultPath, skipBegin, skipEnd);
        if (proxyFullRoutingMasterSlaveSelectResult.size() > 0) {
            String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.masterslave.select.sql");
            BenchmarkResultBean proxyFullRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyFullRoutingMasterSlaveSelectResult, ssSelectSql, "MasterSlave", "ShardingProxy", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(proxyFullRoutingMasterSlaveSelectResultBean);
        }
        
        String shardingjdbcFullRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.masterslave.select.result");
        Map shardingjdbcFullRoutingMasterSlaveSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingMasterSlaveSelectResultPath, skipBegin, skipEnd);
        if (shardingjdbcFullRoutingMasterSlaveSelectResult.size() > 0) {
            String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.masterslave.select.sql");
            BenchmarkResultBean shardingjdbcFullRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingMasterSlaveSelectResult, ssSelectSql, "MasterSlave", "ShardingJDBC", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(shardingjdbcFullRoutingMasterSlaveSelectResultBean);
        }
        
        String jdbcFullRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.masterslave.select.result");
        Map jdbcFullRoutingMasterSlaveSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingMasterSlaveSelectResultPath, skipBegin, skipEnd);
        if (jdbcFullRoutingMasterSlaveSelectResult.size() > 0){
            String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.fullrouting.masterslave.select.sql");
            BenchmarkResultBean jdbcFullRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingMasterSlaveSelectResult, jdbcSelectSql, "MasterSlave", "MYSQL", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(jdbcFullRoutingMasterSlaveSelectResultBean);
        }
    }
    
    /**
     *
     */
    public static void calculateFullroutingSharding(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion, int skipBegin, int skipEnd, int concurency, long updateTime, int dbShardingCount, int tableShardingCount) {
        
        String proxyFullRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.sharding.select.result");
        Map proxyFullRoutingShardingSelectResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingShardingSelectResultPath, skipBegin, skipEnd);
        if (proxyFullRoutingShardingSelectResult.size() > 0) {
            String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.sharding.select.sql");
            BenchmarkResultBean proxyFullRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyFullRoutingShardingSelectResult, ssSelectSql, "Sharding", "ShardingProxy", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(proxyFullRoutingShardingSelectResultBean);
        }
        
        String shardingjdbcFullRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.sharding.select.result");
        Map shardingjdbcFullRoutingShardingSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingShardingSelectResultPath, skipBegin, skipEnd);
        if (shardingjdbcFullRoutingShardingSelectResult.size() > 0) {
            String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.sharding.select.sql");
            BenchmarkResultBean shardingjdbcFullRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingShardingSelectResult, ssSelectSql, "Sharding", "ShardingJDBC", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(shardingjdbcFullRoutingShardingSelectResultBean);
        }
        
        String jdbcFullRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.sharding.select.result");
        Map jdbcFullRoutingShardingSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingShardingSelectResultPath, skipBegin, skipEnd);
        if (jdbcFullRoutingShardingSelectResult.size() > 0) {
            String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.fullrouting.sharding.select.sql");
            BenchmarkResultBean jdbcFullRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingShardingSelectResult, jdbcSelectSql, "Sharding", "MYSQL", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(jdbcFullRoutingShardingSelectResultBean);
        }
    }
    
    /**
     *
     */
    public static void calculateFullroutingShardingMasterslaveEncrypt(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion, int skipBegin, int skipEnd, int concurency, long updateTime, int dbShardingCount, int tableShardingCount) {
        
        String proxyFullRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.shardingmasterslaveencrypt.select.result");
        Map proxyFullRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(proxyFullRoutingShardingMasterSlaveEncryptSelectResultPath, skipBegin, skipEnd);
        if (proxyFullRoutingShardingMasterSlaveEncryptSelectResult.size() > 0) {
            String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.shardingmasterslaveencrypt.select.sql");
            BenchmarkResultBean proxyFullRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxyFullRoutingShardingMasterSlaveEncryptSelectResult, ssSelectSql, "Sharding+Master+Slave+Encrypt", "ShardingProxy", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(proxyFullRoutingShardingMasterSlaveEncryptSelectResultBean);
        }
        String shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.shardingmasterslaveencrypt.select.result");
        Map shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResultPath, skipBegin, skipEnd);
        if (shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResult.size() > 0) {
            String ssSelectSql = (String)sqlConfig.get("ss.benchmark.fullrouting.shardingmasterslaveencrypt.select.sql");
            BenchmarkResultBean shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResult, ssSelectSql, "Sharding+Master+Slave+Encrypt", "ShardingJDBC", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(shardingjdbcFullRoutingShardingMasterSlaveEncryptSelectResultBean);
        }
        String jdbcFullRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.shardingmasterslaveencrypt.select.result");
        Map jdbcFullRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcFullRoutingShardingMasterSlaveEncryptSelectResultPath, skipBegin, skipEnd);
        if (jdbcFullRoutingShardingMasterSlaveEncryptSelectResult.size() > 0) {
            String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.fullrouting.shardingmasterslaveencrypt.select.sql");
            BenchmarkResultBean jdbcFullRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcFullRoutingShardingMasterSlaveEncryptSelectResult, jdbcSelectSql, "Sharding+Master+Slave+Encrypt", "MYSQL", "FullRouting", "Select", concurency, updateTime, dbShardingCount, tableShardingCount);
            fullRoutingResult.add(jdbcFullRoutingShardingMasterSlaveEncryptSelectResultBean);
        }
    }
}
