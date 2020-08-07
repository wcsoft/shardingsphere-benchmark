package org.apache.shardingsphere.benchmark.jmeter.statistic;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.common.BenchmarkResultStatistic;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

import java.util.Map;

public class JMeterSSBenchmarkStatistic extends JMeterBenchmarkBase {


    @Override
    public SampleResult runTest(JavaSamplerContext context) {

        SampleResult results = new SampleResult();
        results.setSampleLabel("JMeterSSBenchmarkStatistic");
        results.sampleStart();

        calculateFullroutingScenarioResult();

        results.sampleEnd();
        return results;
    }


    public void calculateFullroutingScenarioResult(){
        calculateFullroutingEncrypt();
        calculateFullroutingMasterslave();
        calculateFullroutingSharding();
        calculateFullroutingShardingMasterslaveEncrypt();
    }

    /**
     *
     */
    public void calculateFullroutingEncrypt(){
        String proxyFullRoutingEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.encrypt.insertupdatedelete.result");
        Map proxyFullRoutingEncryptInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(proxyFullRoutingEncryptInsertupdatedeleteResultPath);
        proxyFullRoutingEncryptInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("ss.benchmark.fullrouting.encrypt.delete.sql"));
        proxyFullRoutingEncryptInsertupdatedeleteResult.put("insertSql", sqlConfig.get("ss.benchmark.fullrouting.encrypt.insert.sql"));
        proxyFullRoutingEncryptInsertupdatedeleteResult.put("updateSql", sqlConfig.get("ss.benchmark.fullrouting.encrypt.update.sql"));

        System.out.println(proxyFullRoutingEncryptInsertupdatedeleteResult.toString());

        String shardingjdbcFullRoutingEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.encrypt.insertupdatedelete.result");
        Map shardingjdbcFullRoutingEncryptInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcFullRoutingEncryptInsertupdatedeleteResultPath);
        shardingjdbcFullRoutingEncryptInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("ss.benchmark.fullrouting.encrypt.delete.sql"));
        shardingjdbcFullRoutingEncryptInsertupdatedeleteResult.put("insertSql", sqlConfig.get("ss.benchmark.fullrouting.encrypt.insert.sql"));
        shardingjdbcFullRoutingEncryptInsertupdatedeleteResult.put("updateSql", sqlConfig.get("ss.benchmark.fullrouting.encrypt.update.sql"));
        System.out.println(shardingjdbcFullRoutingEncryptInsertupdatedeleteResult.toString());

        String jdbcFullRoutingEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.encrypt.insertupdatedelete.result");
        Map jdbcFullRoutingEncryptInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcFullRoutingEncryptInsertupdatedeleteResultPath);
        jdbcFullRoutingEncryptInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("jdbc.benchmark.fullrouting.encrypt.delete.sql"));
        jdbcFullRoutingEncryptInsertupdatedeleteResult.put("insertSql", sqlConfig.get("jdbc.benchmark.fullrouting.encrypt.insert.sql"));
        jdbcFullRoutingEncryptInsertupdatedeleteResult.put("updateSql", sqlConfig.get("jdbc.benchmark.fullrouting.encrypt.update.sql"));
        System.out.println(jdbcFullRoutingEncryptInsertupdatedeleteResult.toString());

        String proxyFullRoutingEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.encrypt.select.result");
        Map proxyFullRoutingEncryptSelectResult = BenchmarkResultStatistic.benchmarkStatistic(proxyFullRoutingEncryptSelectResultPath);
        proxyFullRoutingEncryptSelectResult.put("selectSql", sqlConfig.get("ss.benchmark.fullrouting.encrypt.select.sql"));
        System.out.println(proxyFullRoutingEncryptSelectResult.toString());

        String shardingjdbcFullRoutingEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.encrypt.select.result");
        Map shardingjdbcFullRoutingEncryptSelectResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcFullRoutingEncryptSelectResultPath);
        shardingjdbcFullRoutingEncryptSelectResult.put("selectSql", sqlConfig.get("ss.benchmark.fullrouting.encrypt.select.sql"));
        System.out.println(shardingjdbcFullRoutingEncryptSelectResult.toString());

        String jdbcFullRoutingEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.encrypt.select.result");
        Map jdbcFullRoutingEncryptSelectResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcFullRoutingEncryptSelectResultPath);
        jdbcFullRoutingEncryptSelectResult.put("selectSql", sqlConfig.get("jdbc.benchmark.fullrouting.encrypt.select.sql"));
        System.out.println(jdbcFullRoutingEncryptSelectResult.toString());
    }

    /**
     *
     */
    public void calculateFullroutingMasterslave(){
        String proxyFullRoutingMasterslaveInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.masterslave.insertupdatedelete.result");
        Map proxyFullRoutingMasterslaveInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(proxyFullRoutingMasterslaveInsertupdatedeleteResultPath);
        proxyFullRoutingMasterslaveInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("ss.benchmark.fullrouting.masterslave.delete.sql"));
        proxyFullRoutingMasterslaveInsertupdatedeleteResult.put("insertSql", sqlConfig.get("ss.benchmark.fullrouting.masterslave.insert.sql"));
        proxyFullRoutingMasterslaveInsertupdatedeleteResult.put("updateSql", sqlConfig.get("ss.benchmark.fullrouting.masterslave.update.sql"));

        String shardingjdbcFullRoutingMasterslaveInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.masterslave.insertupdatedelete.result");
        Map shardingjdbcFullRoutingMasterslaveInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcFullRoutingMasterslaveInsertupdatedeleteResultPath);
        shardingjdbcFullRoutingMasterslaveInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("ss.benchmark.fullrouting.masterslave.delete.sql"));
        shardingjdbcFullRoutingMasterslaveInsertupdatedeleteResult.put("insertSql", sqlConfig.get("ss.benchmark.fullrouting.masterslave.insert.sql"));
        shardingjdbcFullRoutingMasterslaveInsertupdatedeleteResult.put("updateSql", sqlConfig.get("ss.benchmark.fullrouting.masterslave.update.sql"));

        String jdbcFullRoutingMasterslaveInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.masterslave.insertupdatedelete.result");
        Map jdbcFullRoutingMasterslaveInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcFullRoutingMasterslaveInsertupdatedeleteResultPath);
        jdbcFullRoutingMasterslaveInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("jdbc.benchmark.fullrouting.masterslave.delete.sql"));
        jdbcFullRoutingMasterslaveInsertupdatedeleteResult.put("insertSql", sqlConfig.get("jdbc.benchmark.fullrouting.masterslave.insert.sql"));
        jdbcFullRoutingMasterslaveInsertupdatedeleteResult.put("updateSql", sqlConfig.get("jdbc.benchmark.fullrouting.masterslave.update.sql"));

        String proxyFullRoutingMasterslaveSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.masterslave.select.result");
        Map proxyFullRoutingMasterslaveSelectResult = BenchmarkResultStatistic.benchmarkStatistic(proxyFullRoutingMasterslaveSelectResultPath);
        proxyFullRoutingMasterslaveSelectResult.put("selectSql", sqlConfig.get("ss.benchmark.fullrouting.masterslave.select.sql"));

        String shardingjdbcFullRoutingMasterslaveSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.masterslave.select.result");
        Map shardingjdbcFullRoutingMasterslaveSelectResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcFullRoutingMasterslaveSelectResultPath);
        shardingjdbcFullRoutingMasterslaveSelectResult.put("selectSql", sqlConfig.get("ss.benchmark.fullrouting.masterslave.select.sql"));

        String jdbcFullRoutingMasterslaveSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.masterslave.select.result");
        Map jdbcFullRoutingMasterslaveSelectResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcFullRoutingMasterslaveSelectResultPath);
        jdbcFullRoutingMasterslaveSelectResult.put("selectSql", sqlConfig.get("jdbc.benchmark.fullrouting.masterslave.select.sql"));
    }

    /**
     *
     */
    public void calculateFullroutingSharding(){
        String proxyFullRoutingShardingInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.sharding.insertupdatedelete.result");
        Map proxyFullRoutingShardingInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(proxyFullRoutingShardingInsertupdatedeleteResultPath);
        proxyFullRoutingShardingInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.delete.sql"));
        proxyFullRoutingShardingInsertupdatedeleteResult.put("insertSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.insert.sql"));
        proxyFullRoutingShardingInsertupdatedeleteResult.put("updateSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.update.sql"));

        String shardingjdbcFullRoutingShardingInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.sharding.insertupdatedelete.result");
        Map shardingjdbcFullRoutingShardingInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcFullRoutingShardingInsertupdatedeleteResultPath);
        shardingjdbcFullRoutingShardingInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.delete.sql"));
        shardingjdbcFullRoutingShardingInsertupdatedeleteResult.put("insertSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.insert.sql"));
        shardingjdbcFullRoutingShardingInsertupdatedeleteResult.put("updateSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.update.sql"));

        String jdbcFullRoutingShardingInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.sharding.insertupdatedelete.result");
        Map jdbcFullRoutingShardingInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcFullRoutingShardingInsertupdatedeleteResultPath);
        jdbcFullRoutingShardingInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("jdbc.benchmark.fullrouting.sharding.delete.sql"));
        jdbcFullRoutingShardingInsertupdatedeleteResult.put("insertSql", sqlConfig.get("jdbc.benchmark.fullrouting.sharding.insert.sql"));
        jdbcFullRoutingShardingInsertupdatedeleteResult.put("updateSql", sqlConfig.get("jdbc.benchmark.fullrouting.sharding.update.sql"));

        String proxyFullRoutingShardingSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.sharding.select.result");
        Map proxyFullRoutingShardingSelectResult = BenchmarkResultStatistic.benchmarkStatistic(proxyFullRoutingShardingSelectResultPath);
        proxyFullRoutingShardingSelectResult.put("selectSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.select.sql"));

        String shardingjdbcFullRoutingShardingSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.sharding.select.result");
        Map shardingjdbcFullRoutingShardingSelectResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcFullRoutingShardingSelectResultPath);
        shardingjdbcFullRoutingShardingSelectResult.put("selectSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.select.sql"));

        String jdbcFullRoutingShardingSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.sharding.select.result");
        Map jdbcFullRoutingShardingSelectResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcFullRoutingShardingSelectResultPath);
        jdbcFullRoutingShardingSelectResult.put("selectSql", sqlConfig.get("jdbc.benchmark.fullrouting.sharding.select.sql"));    }

    /**
     *
     */
    public void calculateFullroutingShardingMasterslaveEncrypt() {
        String proxyFullRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.sharding.insertupdatedelete.result");
        Map proxyFullRoutingShardingInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(proxyFullRoutingShardingInsertupdatedeleteResultPath);
        proxyFullRoutingShardingInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.delete.sql"));
        proxyFullRoutingShardingInsertupdatedeleteResult.put("insertSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.insert.sql"));
        proxyFullRoutingShardingInsertupdatedeleteResult.put("updateSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.update.sql"));

        String shardingjdbcFullRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.sharding.insertupdatedelete.result");
        Map shardingjdbcFullRoutingShardingInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcFullRoutingShardingInsertupdatedeleteResultPath);
        shardingjdbcFullRoutingShardingInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.delete.sql"));
        shardingjdbcFullRoutingShardingInsertupdatedeleteResult.put("insertSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.insert.sql"));
        shardingjdbcFullRoutingShardingInsertupdatedeleteResult.put("updateSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.update.sql"));

        String jdbcFullRoutingShardingInsertupdatedeleteResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.sharding.insertupdatedelete.result");
        Map jdbcFullRoutingShardingInsertupdatedeleteResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcFullRoutingShardingInsertupdatedeleteResultPath);
        jdbcFullRoutingShardingInsertupdatedeleteResult.put("deleteSql", sqlConfig.get("jdbc.benchmark.fullrouting.sharding.delete.sql"));
        jdbcFullRoutingShardingInsertupdatedeleteResult.put("insertSql", sqlConfig.get("jdbc.benchmark.fullrouting.sharding.insert.sql"));
        jdbcFullRoutingShardingInsertupdatedeleteResult.put("updateSql", sqlConfig.get("jdbc.benchmark.fullrouting.sharding.update.sql"));

        String proxyFullRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.proxy.fullrouting.sharding.select.result");
        Map proxyFullRoutingShardingSelectResult = BenchmarkResultStatistic.benchmarkStatistic(proxyFullRoutingShardingSelectResultPath);
        proxyFullRoutingShardingSelectResult.put("selectSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.select.sql"));

        String shardingjdbcFullRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.shardingjdbc.fullrouting.sharding.select.result");
        Map shardingjdbcFullRoutingShardingSelectResult = BenchmarkResultStatistic.benchmarkStatistic(shardingjdbcFullRoutingShardingSelectResultPath);
        shardingjdbcFullRoutingShardingSelectResult.put("selectSql", sqlConfig.get("ss.benchmark.fullrouting.sharding.select.sql"));

        String jdbcFullRoutingShardingSelectResultPath = (String) benchmarkResultPath.get("ss.benchmark.jdbc.fullrouting.sharding.select.result");
        Map jdbcFullRoutingShardingSelectResult = BenchmarkResultStatistic.benchmarkStatistic(jdbcFullRoutingShardingSelectResultPath);
        jdbcFullRoutingShardingSelectResult.put("selectSql", sqlConfig.get("jdbc.benchmark.fullrouting.sharding.select.sql"));
    }

    public void calculateRangeroutingScenarioResult(){

    }

    public void calculateRangeroutingEncrypt(){
        String proxyFullRoutingEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.encrypt.insertupdatedelete.result");
        String shardingjdbcFullRoutingEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.encrypt.insertupdatedelete.result");
        String jdbcFullRoutingEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.encrypt.insertupdatedelete.result");
        String proxyFullRoutingEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.encrypt.select.result");
        String shardingjdbcFullRoutingEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.encrypt.select.result");
        String jdbcFullRoutingEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.encrypt.select.result");

    }

    public void calculateRangeroutingMasterslave(){
        String proxyFullRoutingMasterslaveInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.masterslave.insertupdatedelete.result");
        String shardingjdbcFullRoutingMasterslaveInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.masterslave.insertupdatedelete.result");
        String jdbcFullRoutingMasterslaveInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.masterslave.insertupdatedelete.result");
        String proxyFullRoutingMasterslaveSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.masterslave.select.result");
        String shardingjdbcFullRoutingMasterslaveSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.masterslave.select.result");
        String jdbcFullRoutingMasterslaveSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.masterslave.select.result");

    }

    public void calculateRangeroutingSharding(){
        String proxyFullRoutingShardingInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.sharding.insertupdatedelete.result");
        String shardingjdbcFullRoutingShardingInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.sharding.insertupdatedelete.result");
        String jdbcFullRoutingShardingInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.sharding.insertupdatedelete.result");
        String proxyFullRoutingShardingSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.sharding.select.result");
        String shardingjdbcFullRoutingShardingSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.sharding.select.result");
        String jdbcFullRoutingShardingSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.sharding.select.result");
    }

    public void calculateRangeroutingShardingMasterslaveEncrypt(){
        String proxyFullRoutingShardingMasterslaveEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        String shardingdbcFullRoutingShardingMasterslaveEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        String jdbcFullRoutingShardingMasterslaveEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        String proxyFullRoutingShardingMasterslaveEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.rangerouting.shardingmasterslaveencrypt.select.result");
        String shardingdbcdbcFullRoutingShardingMasterslaveEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.rangerouting.shardingmasterslaveencrypt.select.result");
        String jdbcFullRoutingShardingMasterslaveEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.rangerouting.shardingmasterslaveencrypt.select.result");
    }

    public void calculateSingleroutingScenarioResult(){

    }

    public void calculateSingleroutingEncrypt(){
        String proxyFullRoutingEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.encrypt.insertupdatedelete.result");
        String shardingjdbcFullRoutingEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.encrypt.insertupdatedelete.result");
        String jdbcFullRoutingEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.encrypt.insertupdatedelete.result");
        String proxyFullRoutingEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.encrypt.select.result");
        String shardingjdbcFullRoutingEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.encrypt.select.result");
        String jdbcFullRoutingEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.encrypt.select.result");

    }

    public void calculateSingleroutingMasterslave(){
        String proxyFullRoutingMasterslaveInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.masterslave.insertupdatedelete.result");
        String shardingjdbcFullRoutingMasterslaveInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.masterslave.insertupdatedelete.result");
        String jdbcFullRoutingMasterslaveInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.masterslave.insertupdatedelete.result");
        String proxyFullRoutingMasterslaveSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.masterslave.select.result");
        String shardingjdbcFullRoutingMasterslaveSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.masterslave.select.result");
        String jdbcFullRoutingMasterslaveSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.masterslave.select.result");

    }

    public void calculateSingleroutingSharding(){
        String proxyFullRoutingShardingInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.sharding.insertupdatedelete.result");
        String shardingjdbcFullRoutingShardingInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.sharding.insertupdatedelete.result");
        String jdbcFullRoutingShardingInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.sharding.insertupdatedelete.result");
        String proxyFullRoutingShardingSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.sharding.select.result");
        String shardingjdbcFullRoutingShardingSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.sharding.select.result");
        String jdbcFullRoutingShardingSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.sharding.select.result");
    }

    public void calculateSingleroutingShardingMasterslaveEncrypt(){
        String proxyFullRoutingShardingMasterslaveEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        String shardingdbcFullRoutingShardingMasterslaveEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        String jdbcFullRoutingShardingMasterslaveEncryptInsertupdatedeleteResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.shardingmasterslaveencrypt.insertupdatedelete.result");
        String proxyFullRoutingShardingMasterslaveEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.proxy.singlerouting.shardingmasterslaveencrypt.select.result");
        String shardingdbcdbcFullRoutingShardingMasterslaveEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.shardingjdbc.singlerouting.shardingmasterslaveencrypt.select.result");
        String jdbcFullRoutingShardingMasterslaveEncryptSelectResultPath = (String)benchmarkResultPath.get("ss.benchmark.jdbc.singlerouting.shardingmasterslaveencrypt.select.result");
    }
}