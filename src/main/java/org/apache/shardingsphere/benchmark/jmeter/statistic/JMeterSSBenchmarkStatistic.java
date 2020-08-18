package org.apache.shardingsphere.benchmark.jmeter.statistic;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.bean.BenchmarkResultBean;
import org.apache.shardingsphere.benchmark.common.excel.BenchmarkExcelWriter;
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

public class JMeterSSBenchmarkStatistic extends JMeterBenchmarkBase {
    
    public static DataSource dataSource;
    
    static {
        dataSource = JDBCDataSourceUtil.initDb((String) dbConfig.get("benchmark.result.datasource"),
                (String) dbConfig.get("benchmark.result.host"), (int) dbConfig.get("benchmark.result.port"),
                (String) dbConfig.get("benchmark.result.username"), (String) dbConfig.get("benchmark.result.password"));
    }
    
    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        
        SampleResult results = new SampleResult();
        results.setSampleLabel("JMeterSSBenchmarkStatistic");
        results.sampleStart();
        int concurrency = Integer.valueOf((String)benchmarkResultPath.get("ss.benchmark.result.concurrency")).intValue();
        int skipBegin = Integer.valueOf((String)benchmarkResultPath.get("ss.benchmark.result.skip.begin")).intValue();
        int skipEnd = Integer.valueOf((String)benchmarkResultPath.get("ss.benchmark.result.skip.end")).intValue();
        String currentTime = String.valueOf(System.currentTimeMillis());
        
        List<BenchmarkResultBean> fullRoutingResult = BenchmarkFullroutingStatistic.calculateFullroutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd, concurrency);
        List<BenchmarkResultBean> rangeRoutingResult = BenchmarkRangeroutingStatistic.calculateRangeRoutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd, concurrency);
        List<BenchmarkResultBean> singleRoutingResult = BenchmarkSingleroutingStatistic.calculateSingleRoutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd, concurrency);
    
        updateBenchmarkRecordInDb(fullRoutingResult);
        updateBenchmarkRecordInDb(rangeRoutingResult);
        updateBenchmarkRecordInDb(singleRoutingResult);
        
        List params = Arrays.asList(benchmarkVersion);
        List<BenchmarkResultBean> fullRoutingCalResult = new ArrayList<BenchmarkResultBean>();
        List<BenchmarkResultBean> rangeRoutingCalResult = new ArrayList<BenchmarkResultBean>();
        List<BenchmarkResultBean> singleRoutingCalResult = new ArrayList<BenchmarkResultBean>();
        
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
        
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "full-routing-" + currentTime, true, 1, fullRoutingResult);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "range-routing-" + currentTime, true, 1, rangeRoutingResult);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "single-routing-" + currentTime, true, 1, singleRoutingResult);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.avg.excel.result"), "full-routing-" + currentTime, true, 1, fullRoutingCalResult);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.avg.excel.result"), "range-routing-" + currentTime, true, 1, rangeRoutingCalResult);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.avg.excel.result"), "single-routing-" + currentTime, true, 1, singleRoutingCalResult);
        
        results.sampleEnd();
        return results;
    }
    
    public void updateBenchmarkRecordInDb(List<BenchmarkResultBean> benchMarkResults){
        
        Connection connection = null;
        String insertSql = (String) sqlConfig.get("ss.benchmark.result.insert.sql");
        
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
                        benchmarkResultBean.getDbAction());
                JDBCDataSourceUtil.insert(connection, insertSql, insertParams);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                if(null != connection){
                    try {
                        connection.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
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
                totalMaxTime = totalMaxTime + rs.getDouble(11);
                totalMinTime = totalMinTime + rs.getDouble(12);
                benchmarkResultBean.setSql(rs.getString(13));
                benchmarkResultBean.setDbAction(rs.getString(14));
                benchmarkResultBean.setConcurrency(rs.getInt(15));
            }
            
            if (totalCount == 0){
                totalCount = 1;
            }
            Map benchmarkPerformanceData = new HashMap<>();
            benchmarkPerformanceData.put("tps", totalTps / totalCount);
            benchmarkPerformanceData.put("total" , totalRequestCount / totalCount);
            benchmarkPerformanceData.put("maxCost" , totalMaxTime / totalCount);
            benchmarkPerformanceData.put("minCost" , totalMinTime / totalCount);
            benchmarkResultBean.setBenchmarkResult(benchmarkPerformanceData);
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return benchmarkResultBean;
    }
}