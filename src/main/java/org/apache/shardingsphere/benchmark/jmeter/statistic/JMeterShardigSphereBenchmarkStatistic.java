package org.apache.shardingsphere.benchmark.jmeter.statistic;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.bean.BenchmarkResultBean;
import org.apache.shardingsphere.benchmark.common.file.excel.BenchmarkExcelWriter;
import org.apache.shardingsphere.benchmark.common.statistic.BenchmarkFullroutingStatistic;
import org.apache.shardingsphere.benchmark.common.statistic.BenchmarkRangeroutingStatistic;
import org.apache.shardingsphere.benchmark.common.statistic.BenchmarkSingleroutingStatistic;
import org.apache.shardingsphere.benchmark.db.jdbc.JDBCDataSourceUtil;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JMeter test plan for benchmark statistic.
 */
public class JMeterShardigSphereBenchmarkStatistic extends JMeterBenchmarkBase {
    
    public static DataSource dataSource;
    static {
        dataSource = JDBCDataSourceUtil.initDb((String) userConfig.get("shardingsphere.benchmark.result.datasource"),
                (String) userConfig.get("shardingsphere.benchmark.result.host"), Integer.valueOf((String)userConfig.get("shardingsphere.benchmark.result.port")).intValue(),
                (String) userConfig.get("shardingsphere.benchmark.result.username"), (String) userConfig.get("shardingsphere.benchmark.result.password"));
    }
    
    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        
        SampleResult results = new SampleResult();
        results.setSampleLabel("JMeterSSBenchmarkStatistic");
        results.sampleStart();
        int concurrency = Integer.valueOf((String)userConfig.get("shardingsphere.jmeter.concurrency.count")).intValue();
        String[] sampleArray = ((String)userConfig.get("shardingsphere.result.sample.amount")).split(",");
        int skipBegin = Integer.valueOf(sampleArray[0]).intValue();
        int skipEnd = Integer.valueOf(sampleArray[1]).intValue();
        long updateTime = System.currentTimeMillis();
        int dbShardingCount = Integer.valueOf((String)userConfig.get("shardingsphere.sharding.db.count")).intValue();
        int tableShardingCount = Integer.valueOf((String)userConfig.get("shardingsphere.sharding.table.count")).intValue();
        String benchmarkVersion = (String)userConfig.get("shardingsphere.version");
        String benchmarkInsertSql = (String) sqlConfig.get("ss.benchmark.result.insert.sql");
        String benchmarkAvgInsertSql = (String)sqlConfig.get("ss.benchmark.avg.result.insert.sql");
        String currentTime = String.valueOf(System.currentTimeMillis());
        List<BenchmarkResultBean> fullRoutingResult = BenchmarkFullroutingStatistic.calculateFullroutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd, concurrency, updateTime, dbShardingCount, tableShardingCount);
        List<BenchmarkResultBean> rangeRoutingResult = BenchmarkRangeroutingStatistic.calculateRangeRoutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd, concurrency, updateTime, dbShardingCount, tableShardingCount);
        List<BenchmarkResultBean> singleRoutingResult = BenchmarkSingleroutingStatistic.calculateSingleRoutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd, concurrency, updateTime, dbShardingCount, tableShardingCount);
        updateBenchmarkRecordInDb(fullRoutingResult, benchmarkInsertSql);
        updateBenchmarkRecordInDb(rangeRoutingResult, benchmarkInsertSql);
        updateBenchmarkRecordInDb(singleRoutingResult, benchmarkInsertSql);
        List params = Arrays.asList(benchmarkVersion, tableShardingCount, dbShardingCount, concurrency);
        List<BenchmarkResultBean> fullRoutingCalResult = new ArrayList<BenchmarkResultBean>(10);
        List<BenchmarkResultBean> rangeRoutingCalResult = new ArrayList<BenchmarkResultBean>(10);
        List<BenchmarkResultBean> singleRoutingCalResult = new ArrayList<BenchmarkResultBean>(10);
        
        fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.encrypt.shardingjdbc.select.sql"), params));
        fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.encrypt.proxy.select.sql"), params));
        fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.encrypt.mysql.select.sql"), params));
        fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.masterslave.shardingjdbc.select.sql"), params));
        fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.masterslave.proxy.select.sql"), params));
        fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.masterslave.mysql.select.sql"), params));
        fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.sharding.shardingjdbc.select.sql"), params));
        fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.sharding.proxy.select.sql"), params));
        fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.sharding.mysql.select.sql"), params));
       fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.shardingmasterslaveencrypt.shardingjdbc.select.sql"), params));
        fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.shardingmasterslaveencrypt.proxy.select.sql"), params));
        fullRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.shardingmasterslaveencrypt.mysql.select.sql"), params));
      
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.encrypt.shardingjdbc.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.encrypt.proxy.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.encrypt.mysql.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.encrypt.shardingjdbc.insertupdatedelete.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.encrypt.proxy.insertupdatedelete.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.encrypt.mysql.insertupdatedelete.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.masterslave.shardingjdbc.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.masterslave.proxy.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.masterslave.mysql.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.masterslave.shardingjdbc.insertupdatedelete.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.masterslave.proxy.insertupdatedelete.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.masterslave.mysql.insertupdatedelete.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.sharding.shardingjdbc.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.sharding.proxy.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.sharding.mysql.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.sharding.shardingjdbc.insertupdatedelete.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.sharding.proxy.insertupdatedelete.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.sharding.mysql.insertupdatedelete.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.shardingmasterslaveencrypt.shardingjdbc.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.shardingmasterslaveencrypt.proxy.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.shardingmasterslaveencrypt.mysql.select.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.shardingmasterslaveencrypt.shardingjdbc.insertupdatedelete.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.shardingmasterslaveencrypt.proxy.insertupdatedelete.sql"), params));
        rangeRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.rangerouting.shardingmasterslaveencrypt.mysql.insertupdatedelete.sql"), params));
        
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.encrypt.shardingjdbc.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.encrypt.proxy.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.encrypt.mysql.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.encrypt.shardingjdbc.insertupdatedelete.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.encrypt.proxy.insertupdatedelete.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.encrypt.mysql.insertupdatedelete.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.masterslave.shardingjdbc.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.masterslave.proxy.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.masterslave.mysql.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.masterslave.shardingjdbc.insertupdatedelete.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.masterslave.proxy.insertupdatedelete.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.masterslave.mysql.insertupdatedelete.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.sharding.shardingjdbc.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.sharding.proxy.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.sharding.mysql.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.sharding.shardingjdbc.insertupdatedelete.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.sharding.proxy.insertupdatedelete.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.sharding.mysql.insertupdatedelete.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.shardingmasterslaveencrypt.shardingjdbc.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.shardingmasterslaveencrypt.proxy.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.shardingmasterslaveencrypt.mysql.select.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.shardingmasterslaveencrypt.shardingjdbc.insertupdatedelete.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.shardingmasterslaveencrypt.proxy.insertupdatedelete.sql"), params));
        singleRoutingCalResult.add(getTargetResult((String)sqlConfig.get("ss.benchmark.result.singlerouting.shardingmasterslaveencrypt.mysql.insertupdatedelete.sql"), params));
    
        updateBenchmarkRecordInDb(fullRoutingCalResult, benchmarkAvgInsertSql);
        updateBenchmarkRecordInDb(rangeRoutingCalResult, benchmarkAvgInsertSql);
        updateBenchmarkRecordInDb(singleRoutingCalResult, benchmarkAvgInsertSql);
        
        String resultExcelPath = (String)userConfig.get("shardingsphere.benchmark.result.base.path") + "/" + (String)userConfig.get("shardingsphere.benchmark.result.excel.name");
        String resultAvgExcelPath = (String)userConfig.get("shardingsphere.benchmark.result.base.path")  + "/" + (String)userConfig.get("shardingsphere.benchmark.avg.result.excel.name");
        BenchmarkExcelWriter.writeExcel(resultExcelPath, "full-routing-" + currentTime, true, 1, fullRoutingResult);
        BenchmarkExcelWriter.writeExcel(resultExcelPath, "range-routing-" + currentTime, true, 1, rangeRoutingResult);
        BenchmarkExcelWriter.writeExcel(resultExcelPath, "single-routing-" + currentTime, true, 1, singleRoutingResult);
        BenchmarkExcelWriter.writeExcel(resultAvgExcelPath, "full-routing-" + currentTime, true, 1, fullRoutingCalResult);
        BenchmarkExcelWriter.writeExcel(resultAvgExcelPath, "range-routing-" + currentTime, true, 1, rangeRoutingCalResult);
        BenchmarkExcelWriter.writeExcel(resultAvgExcelPath, "single-routing-" + currentTime, true, 1, singleRoutingCalResult);
        
        results.sampleEnd();
        return results;
    }
    
    public void updateBenchmarkRecordInDb(List<BenchmarkResultBean> benchMarkResults, String sql){
        Connection connection = null;
        for(int i = 0; i < benchMarkResults.size(); i++){
            try {
                connection = dataSource.getConnection();
                BenchmarkResultBean benchmarkResultBean = benchMarkResults.get(i);
                List insertParams = Arrays.asList(
                        benchmarkResultBean.getProduct(), 
                        benchmarkResultBean.getVersion(),
                        benchmarkResultBean.getScenario(),
                        benchmarkResultBean.getRules(),
                        (double)benchmarkResultBean.getBenchmarkResult().get("tps"),
                        (int)benchmarkResultBean.getBenchmarkResult().get("total"),
                        (double)benchmarkResultBean.getBenchmarkResult().get("tp50th"),
                        (double)benchmarkResultBean.getBenchmarkResult().get("tp90th"),
                        (double)benchmarkResultBean.getBenchmarkResult().get("tp95th"),
                        (double)benchmarkResultBean.getBenchmarkResult().get("maxCost"),
                        (double)benchmarkResultBean.getBenchmarkResult().get("minCost"),
                        benchmarkResultBean.getSql(),
                        benchmarkResultBean.getDbAction(),
                        benchmarkResultBean.getConcurrency(),
                        benchmarkResultBean.getUpdateTime(),
                        benchmarkResultBean.getTableShardingCount(),
                        benchmarkResultBean.getDbShardingCount());
                JDBCDataSourceUtil.insert(connection, sql, insertParams);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if(null != connection){
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    
    public BenchmarkResultBean getTargetResult(String sql, List params){
        Connection connection = null;
        BenchmarkResultBean benchmarkResultBean = new BenchmarkResultBean();
        try {
            double totalTps = 0;
            int totalCount = 0;
            int totalRequestCount = 0;
            double totalMaxTime = 0;
            double totalMinTime = 0;
            double totalTp95Th = 0;
            double totalTp90Th = 0;
            double totalTp50Th = 0;
            connection = dataSource.getConnection();
            ResultSet rs = JDBCDataSourceUtil.select(connection, sql, params);
            while(rs.next()){
                totalCount =  totalCount + 1;
                benchmarkResultBean.setProduct(rs.getString(2));
                benchmarkResultBean.setVersion(rs.getString(3));
                benchmarkResultBean.setScenario(rs.getString(4));
                benchmarkResultBean.setRules(rs.getString(5));
                totalTps = totalTps + rs.getDouble(6);
                totalRequestCount = totalRequestCount + rs.getInt(7);
                totalTp50Th = totalTp50Th + rs.getDouble(8);
                totalTp90Th = totalTp50Th + rs.getDouble(9);
                totalTp95Th = totalTp50Th + rs.getDouble(10);
                totalMaxTime = totalMaxTime + rs.getDouble(11);
                totalMinTime = totalMinTime + rs.getDouble(12);
                benchmarkResultBean.setSql(rs.getString(13));
                benchmarkResultBean.setDbAction(rs.getString(14));
                benchmarkResultBean.setConcurrency(rs.getInt(15));
                benchmarkResultBean.setUpdateTime(rs.getLong(16));
                benchmarkResultBean.setDbShardingCount(rs.getInt(17));
                benchmarkResultBean.setTableShardingCount(rs.getInt(18));
            }
            if (totalCount == 0){
                totalCount = 1;
            }
            Map benchmarkPerformanceData = new HashMap<>();
            benchmarkPerformanceData.put("tps", totalTps / totalCount);
            benchmarkPerformanceData.put("total" , totalRequestCount / totalCount);
            benchmarkPerformanceData.put("maxCost" , totalMaxTime / totalCount);
            benchmarkPerformanceData.put("minCost" , totalMinTime / totalCount);
            benchmarkPerformanceData.put("tp50th", totalTp50Th / totalCount);
            benchmarkPerformanceData.put("tp90th", totalTp90Th / totalCount);
            benchmarkPerformanceData.put("tp95th", totalTp95Th / totalCount);
            benchmarkResultBean.setBenchmarkResult(benchmarkPerformanceData);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return benchmarkResultBean;
    }
}