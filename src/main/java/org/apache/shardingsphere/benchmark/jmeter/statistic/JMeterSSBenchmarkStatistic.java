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
import java.util.List;

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
        int skipBegin = Integer.valueOf((String)benchmarkResultPath.get("ss.benchmark.result.skip.begin")).intValue();
        int skipEnd = Integer.valueOf((String)benchmarkResultPath.get("ss.benchmark.result.skip.end")).intValue();
        String currentTime = String.valueOf(System.currentTimeMillis());
        
        List<BenchmarkResultBean> fullRoutingResult = BenchmarkFullroutingStatistic.calculateFullroutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd);
        List<BenchmarkResultBean> rangeRoutingResult = BenchmarkRangeroutingStatistic.calculateRangeRoutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd);
        List<BenchmarkResultBean> singleRoutingResult = BenchmarkSingleroutingStatistic.calculateSingleRoutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion, skipBegin, skipEnd);
    
        updateBenchmarkRecordInDb(fullRoutingResult);
        updateBenchmarkRecordInDb(rangeRoutingResult);
        updateBenchmarkRecordInDb(singleRoutingResult);
    
        List<BenchmarkResultBean> allFullRoutingResult = new ArrayList<BenchmarkResultBean>();
        List<BenchmarkResultBean> allRangeRoutingResult = new ArrayList<BenchmarkResultBean>();
        List<BenchmarkResultBean> allSingleRoutingResult = new ArrayList<BenchmarkResultBean>();
        List params = Arrays.asList(benchmarkVersion);
        getTargetResult((String)sqlConfig.get("ss.benchmark.result.fullrouting.encrypt.mysql.select.sql"), params);
        
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "full-routing-" + currentTime, true, 1, fullRoutingResult);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "range-routing-" + currentTime, true, 1, rangeRoutingResult);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "single-routing-" + currentTime, true, 1, singleRoutingResult);
        


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
    
    public void getTargetResult(String sql, List params){
        Connection connection = null;
        try {
            double totalTps = 0;
            int totalCount = 0;
            int totalRequestCount = 0;
            double totalMaxTime = 0;
            double totalMinTime = 0;
            connection = dataSource.getConnection();
            ResultSet rs = JDBCDataSourceUtil.select(connection, sql, params);
            BenchmarkResultBean benchmarkResultBean = new BenchmarkResultBean();
            while(rs.next()){
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
                totalCount =  totalCount + 1;
            }
    
            //TODO bechmark result bean 中的result组织形式。
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}