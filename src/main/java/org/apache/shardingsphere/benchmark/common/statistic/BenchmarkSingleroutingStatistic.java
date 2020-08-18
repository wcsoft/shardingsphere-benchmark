package org.apache.shardingsphere.benchmark.common.statistic;

import org.apache.shardingsphere.benchmark.bean.BenchmarkResultBean;
import org.apache.shardingsphere.benchmark.common.resultParser.BenchmarkResultParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BenchmarkSingleroutingStatistic {
    
    public static List<BenchmarkResultBean> singleRoutingResult = new ArrayList<BenchmarkResultBean>();
    
    public static List<BenchmarkResultBean> calculateSingleRoutingScenarioResult(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion, int skipBegin, int skipEnd) {
        calculateSingleRoutingEncrypt(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd);
        calculateSingleRoutingMasterslave(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd);
        calculateSingleRoutingSharding(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd);
        calculateSingleRoutingShardingMasterslaveEncrypt(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd);
        return singleRoutingResult;
    }
    
    public static void calculateSingleRoutingEncrypt(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion, int skipBegin, int skipEnd) {
        String proxySingleRoutingEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.encrypt.insertupdatedelete.result");
        Map proxySingleRoutingEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(proxySingleRoutingEncryptInsertupdatedeleteResultPath, skipBegin, skipEnd);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.singlerouting.encrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.singlerouting.encrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.singlerouting.encrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean proxySingleRoutingEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxySingleRoutingEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Encrypt", "ShardingProxy", "SingleRouting", "Insert+Update+delete");
        //System.out.println(proxySingleRoutingEncryptInsertupdatedeleteResult.toString());
        
        String shardingjdbcSingleRoutingEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.encrypt.insertupdatedelete.result");
        Map shardingjdbcSingleRoutingEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcSingleRoutingEncryptInsertupdatedeleteResultPath, skipBegin, skipEnd);
        BenchmarkResultBean shardingjdbcSingleRoutingEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcSingleRoutingEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Encrypt", "ShardingJDBC", "SingleRouting", "Insert+Update+delete");
        //System.out.println(shardingjdbcSingleRoutingEncryptInsertupdatedeleteResult.toString());
        
        String jdbcSingleRoutingEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.encrypt.insertupdatedelete.result");
        Map jdbcSingleRoutingEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(jdbcSingleRoutingEncryptInsertupdatedeleteResultPath, skipBegin, skipEnd);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.singlerouting.encrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.singlerouting.encrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.singlerouting.encrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcSingleRoutingEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcSingleRoutingEncryptInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "Encrypt", "MYSQL", "SingleRouting", "Insert+Update+delete");
        //System.out.println(jdbcSingleRoutingEncryptInsertupdatedeleteResult.toString());
        
        String proxySingleRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.encrypt.select.result");
        Map proxySingleRoutingEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(proxySingleRoutingEncryptSelectResultPath, skipBegin, skipEnd);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.singlerouting.encrypt.select.sql");
        BenchmarkResultBean proxySingleRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxySingleRoutingEncryptSelectResult, ssSelectSql, "Encrypt", "ShardingProxy", "SingleRouting", "Select");
        //System.out.println(proxySingleRoutingEncryptSelectResult.toString());
        
        String shardingjdbcSingleRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.encrypt.select.result");
        Map shardingjdbcSingleRoutingEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcSingleRoutingEncryptSelectResultPath, skipBegin, skipEnd);
        BenchmarkResultBean shardingjdbcSingleRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcSingleRoutingEncryptSelectResult, ssSelectSql, "Encrypt", "ShardingJDBC", "SingleRouting", "Select");
        //System.out.println(shardingjdbcSingleRoutingEncryptSelectResult.toString());
        
        String jdbcSingleRoutingEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.encrypt.select.result");
        Map jdbcSingleRoutingEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcSingleRoutingEncryptSelectResultPath, skipBegin, skipEnd);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.singlerouting.encrypt.select.sql");
        BenchmarkResultBean jdbcSingleRoutingEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcSingleRoutingEncryptSelectResult, jdbcSelectSql, "Encrypt", "MYSQL", "SingleRouting", "Select");
        //System.out.println(jdbcSingleRoutingEncryptSelectResult.toString());
        singleRoutingResult.add(proxySingleRoutingEncryptInsertupdatedeleteResultBean);
        singleRoutingResult.add(shardingjdbcSingleRoutingEncryptInsertupdatedeleteResultBean);
        singleRoutingResult.add(jdbcSingleRoutingEncryptInsertupdatedeleteResultBean);
        singleRoutingResult.add(proxySingleRoutingEncryptSelectResultBean);
        singleRoutingResult.add(shardingjdbcSingleRoutingEncryptSelectResultBean);
        singleRoutingResult.add(jdbcSingleRoutingEncryptSelectResultBean);
    }
    
    /**
     *
     */
    public static void calculateSingleRoutingMasterslave(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion, int skipBegin, int skipEnd) {
        String proxySingleRoutingMasterSlaveInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.masterslave.insertupdatedelete.result");
        Map proxySingleRoutingMasterSlaveInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(proxySingleRoutingMasterSlaveInsertupdatedeleteResultPath, skipBegin, skipEnd);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.singlerouting.masterslave.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.singlerouting.masterslave.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.singlerouting.masterslave.update.sql") +  ";\r\n";
        BenchmarkResultBean proxySingleRoutingMasterSlaveInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxySingleRoutingMasterSlaveInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "MasterSlave", "ShardingProxy", "SingleRouting", "Insert+Update+delete");
        //System.out.println(proxySingleRoutingMasterSlaveInsertupdatedeleteResult.toString());
        
        String shardingjdbcSingleRoutingMasterSlaveInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.masterslave.insertupdatedelete.result");
        Map shardingjdbcSingleRoutingMasterSlaveInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcSingleRoutingMasterSlaveInsertupdatedeleteResultPath, skipBegin, skipEnd);
        BenchmarkResultBean shardingjdbcSingleRoutingMasterSlaveInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcSingleRoutingMasterSlaveInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "MasterSlave", "ShardingJDBC", "SingleRouting", "Insert+Update+delete");
        //System.out.println(shardingjdbcSingleRoutingMasterSlaveInsertupdatedeleteResult.toString());
        
        String jdbcSingleRoutingMasterSlaveInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.masterslave.insertupdatedelete.result");
        Map jdbcSingleRoutingMasterSlaveInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(jdbcSingleRoutingMasterSlaveInsertupdatedeleteResultPath, skipBegin, skipEnd);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.singlerouting.masterslave.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.singlerouting.masterslave.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.singlerouting.masterslave.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcSingleRoutingMasterSlaveInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcSingleRoutingMasterSlaveInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "MasterSlave", "MYSQL", "SingleRouting", "Insert+Update+delete");
        //System.out.println(jdbcSingleRoutingMasterSlaveInsertupdatedeleteResult.toString());
        
        String proxySingleRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.masterslave.select.result");
        Map proxySingleRoutingMasterSlaveSelectResult = BenchmarkResultParser.benchmarkStatistic(proxySingleRoutingMasterSlaveSelectResultPath, skipBegin, skipEnd);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.singlerouting.masterslave.select.sql");
        BenchmarkResultBean proxySingleRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxySingleRoutingMasterSlaveSelectResult, ssSelectSql, "MasterSlave", "ShardingProxy", "SingleRouting", "Select");
        //System.out.println(proxySingleRoutingMasterSlaveSelectResult.toString());
        
        String shardingjdbcSingleRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.masterslave.select.result");
        Map shardingjdbcSingleRoutingMasterSlaveSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcSingleRoutingMasterSlaveSelectResultPath, skipBegin, skipEnd);
        BenchmarkResultBean shardingjdbcSingleRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcSingleRoutingMasterSlaveSelectResult, ssSelectSql, "MasterSlave", "ShardingJDBC", "SingleRouting", "Select");
        //System.out.println(shardingjdbcSingleRoutingMasterSlaveSelectResult.toString());
        
        String jdbcSingleRoutingMasterSlaveSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.masterslave.select.result");
        Map jdbcSingleRoutingMasterSlaveSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcSingleRoutingMasterSlaveSelectResultPath, skipBegin, skipEnd);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.singlerouting.masterslave.select.sql");
        BenchmarkResultBean jdbcSingleRoutingMasterSlaveSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcSingleRoutingMasterSlaveSelectResult, jdbcSelectSql, "MasterSlave", "MYSQL", "SingleRouting", "Select");
        //System.out.println(jdbcSingleRoutingMasterSlaveSelectResult.toString());
        singleRoutingResult.add(proxySingleRoutingMasterSlaveInsertupdatedeleteResultBean);
        singleRoutingResult.add(shardingjdbcSingleRoutingMasterSlaveInsertupdatedeleteResultBean);
        singleRoutingResult.add(jdbcSingleRoutingMasterSlaveInsertupdatedeleteResultBean);
        singleRoutingResult.add(proxySingleRoutingMasterSlaveSelectResultBean);
        singleRoutingResult.add(shardingjdbcSingleRoutingMasterSlaveSelectResultBean);
        singleRoutingResult.add(jdbcSingleRoutingMasterSlaveSelectResultBean);
    }
    
    /**
     *
     */
    public static void calculateSingleRoutingSharding(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion, int skipBegin, int skipEnd) {
        String proxySingleRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.sharding.insertupdatedelete.result");
        Map proxySingleRoutingShardingInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(proxySingleRoutingShardingInsertupdatedeleteResultPath, skipBegin, skipEnd);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.singlerouting.sharding.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.singlerouting.sharding.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.singlerouting.sharding.update.sql") +  ";\r\n";
        BenchmarkResultBean proxySingleRoutingShardingInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxySingleRoutingShardingInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding", "ShardingProxy", "SingleRouting", "Insert+Update+delete");
        //System.out.println(proxySingleRoutingShardingInsertupdatedeleteResult.toString());
        
        String shardingjdbcSingleRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.sharding.insertupdatedelete.result");
        Map shardingjdbcSingleRoutingShardingInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcSingleRoutingShardingInsertupdatedeleteResultPath, skipBegin, skipEnd);
        BenchmarkResultBean shardingjdbcSingleRoutingShardingInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcSingleRoutingShardingInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding", "ShardingJDBC", "SingleRouting", "Insert+Update+delete");
        //System.out.println(shardingjdbcSingleRoutingShardingInsertupdatedeleteResult.toString());
        
        String jdbcSingleRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.sharding.insertupdatedelete.result");
        Map jdbcSingleRoutingShardingInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(jdbcSingleRoutingShardingInsertupdatedeleteResultPath, skipBegin, skipEnd);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.singlerouting.sharding.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.singlerouting.sharding.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.singlerouting.sharding.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcSingleRoutingShardingInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcSingleRoutingShardingInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "Sharding", "MYSQL", "SingleRouting", "Insert+Update+delete");
        //System.out.println(jdbcSingleRoutingShardingInsertupdatedeleteResult.toString());
        
        String proxySingleRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.sharding.select.result");
        Map proxySingleRoutingShardingSelectResult = BenchmarkResultParser.benchmarkStatistic(proxySingleRoutingShardingSelectResultPath, skipBegin, skipEnd);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.singlerouting.sharding.select.sql");
        BenchmarkResultBean proxySingleRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxySingleRoutingShardingSelectResult, ssSelectSql, "Sharding", "ShardingProxy", "SingleRouting", "Select");
        //System.out.println(proxySingleRoutingShardingSelectResult.toString());
        
        String shardingjdbcSingleRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.sharding.select.result");
        Map shardingjdbcSingleRoutingShardingSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcSingleRoutingShardingSelectResultPath, skipBegin, skipEnd);
        BenchmarkResultBean shardingjdbcSingleRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcSingleRoutingShardingSelectResult, ssSelectSql, "Sharding", "ShardingJDBC", "SingleRouting", "Select");
        //System.out.println(shardingjdbcSingleRoutingShardingSelectResult.toString());
        
        String jdbcSingleRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.sharding.select.result");
        Map jdbcSingleRoutingShardingSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcSingleRoutingShardingSelectResultPath, skipBegin, skipEnd);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.singlerouting.sharding.select.sql");
        BenchmarkResultBean jdbcSingleRoutingShardingSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcSingleRoutingShardingSelectResult, jdbcSelectSql, "Sharding", "MYSQL", "SingleRouting", "Select");
        //System.out.println(jdbcSingleRoutingShardingSelectResult.toString());
        singleRoutingResult.add(proxySingleRoutingShardingInsertupdatedeleteResultBean);
        singleRoutingResult.add(shardingjdbcSingleRoutingShardingInsertupdatedeleteResultBean);
        singleRoutingResult.add(jdbcSingleRoutingShardingInsertupdatedeleteResultBean);
        singleRoutingResult.add(proxySingleRoutingShardingSelectResultBean);
        singleRoutingResult.add(shardingjdbcSingleRoutingShardingSelectResultBean);
        singleRoutingResult.add(jdbcSingleRoutingShardingSelectResultBean);
    }
    
    /**
     *
     */
    public static void calculateSingleRoutingShardingMasterslaveEncrypt(Map benchmarkResultPath, Map sqlConfig, String benchmarkVersion, int skipBegin, int skipEnd) {
        String proxySingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        Map proxySingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(proxySingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath, skipBegin, skipEnd);
        String ssInsertUpdateDeleteSQL = (String)sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean proxySingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion,proxySingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding+Master+Slave+Encrypt", "ShardingProxy", "SingleRouting", "Insert+Update+delete");
        //System.out.println(proxySingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult.toString());
        
        String shardingjdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        Map shardingjdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath, skipBegin, skipEnd);
        BenchmarkResultBean shardingjdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult, ssInsertUpdateDeleteSQL, "Sharding+Master+Slave+Encrypt", "ShardingJDBC", "SingleRouting", "Insert+Update+delete");
        //System.out.println(shardingjdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult.toString());
        
        String jdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        Map jdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult = BenchmarkResultParser.benchmarkStatistic(jdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultPath, skipBegin, skipEnd);
        String jdbcInsertUpdateDeleteSQL = (String)sqlConfig.get("jdbc.benchmark.singlerouting.shardingmasterslaveencrypt.delete.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.singlerouting.shardingmasterslaveencrypt.insert.sql") + ";\r\n" + (String)sqlConfig.get("jdbc.benchmark.singlerouting.shardingmasterslaveencrypt.update.sql") +  ";\r\n";
        BenchmarkResultBean jdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult, jdbcInsertUpdateDeleteSQL, "Sharding+Master+Slave+Encrypt", "MYSQL", "SingleRouting", "Insert+Update+delete");
        //System.out.println(jdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResult.toString());
                
        String proxySingleRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.shardingmasterslaveencrypt.select.result");
        Map proxySingleRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(proxySingleRoutingShardingMasterSlaveEncryptSelectResultPath, skipBegin, skipEnd);
        String ssSelectSql = (String)sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.select.sql");
        BenchmarkResultBean proxySingleRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, proxySingleRoutingShardingMasterSlaveEncryptSelectResult, ssSelectSql, "Sharding+Master+Slave+Encrypt", "ShardingProxy", "SingleRouting", "Select");
        //System.out.println(proxySingleRoutingShardingMasterSlaveEncryptSelectResult.toString());
        
        String shardingjdbcSingleRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.shardingmasterslaveencrypt.select.result");
        Map shardingjdbcSingleRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(shardingjdbcSingleRoutingShardingMasterSlaveEncryptSelectResultPath, skipBegin, skipEnd);
        BenchmarkResultBean shardingjdbcSingleRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, shardingjdbcSingleRoutingShardingMasterSlaveEncryptSelectResult, ssSelectSql, "Sharding+Master+Slave+Encrypt", "ShardingJDBC", "SingleRouting", "Select");
        //System.out.println(shardingjdbcSingleRoutingShardingMasterSlaveEncryptSelectResult.toString());
        
        String jdbcSingleRoutingShardingMasterSlaveEncryptSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.shardingmasterslaveencrypt.select.result");
        Map jdbcSingleRoutingShardingMasterSlaveEncryptSelectResult = BenchmarkResultParser.benchmarkStatistic(jdbcSingleRoutingShardingMasterSlaveEncryptSelectResultPath, skipBegin, skipEnd);
        String jdbcSelectSql = (String)sqlConfig.get("jdbc.benchmark.singlerouting.shardingmasterslaveencrypt.select.sql");
        BenchmarkResultBean jdbcSingleRoutingShardingMasterSlaveEncryptSelectResultBean = new BenchmarkResultBean(benchmarkVersion, jdbcSingleRoutingShardingMasterSlaveEncryptSelectResult, jdbcSelectSql, "Sharding+Master+Slave+Encrypt", "MYSQL", "SingleRouting", "Select");
        //System.out.println(jdbcSingleRoutingShardingMasterSlaveEncryptSelectResult.toString());
        singleRoutingResult.add(proxySingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean);
        singleRoutingResult.add(shardingjdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean);
        singleRoutingResult.add(jdbcSingleRoutingShardingMasterSlaveEncryptInsertupdatedeleteResultBean);
        singleRoutingResult.add(proxySingleRoutingShardingMasterSlaveEncryptSelectResultBean);
        singleRoutingResult.add(shardingjdbcSingleRoutingShardingMasterSlaveEncryptSelectResultBean);
        singleRoutingResult.add(jdbcSingleRoutingShardingMasterSlaveEncryptSelectResultBean);
    }
}
