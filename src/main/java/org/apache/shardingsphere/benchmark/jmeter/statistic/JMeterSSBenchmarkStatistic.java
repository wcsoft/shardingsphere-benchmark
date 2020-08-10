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
import java.sql.SQLException;
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

        Connection connection = null;
        SampleResult results = new SampleResult();
        results.setSampleLabel("JMeterSSBenchmarkStatistic");
        results.sampleStart();

        List<BenchmarkResultBean> fullRoutingResult = BenchmarkFullroutingStatistic.calculateFullroutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion);
        List<BenchmarkResultBean> rangeRoutingResult = BenchmarkRangeroutingStatistic.calculateRangeRoutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion);
        List<BenchmarkResultBean> singleRoutingResult = BenchmarkSingleroutingStatistic.calculateSingleRoutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "full-routing", true, 0, fullRoutingResult);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "range-routing", false, 0, rangeRoutingResult);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "single-routing", false, 0, singleRoutingResult);
    
        /*updateBenchmarkRecordInDb(fullRoutingResult);
        updateBenchmarkRecordInDb(rangeRoutingResult);
        updateBenchmarkRecordInDb(singleRoutingResult);*/

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
                List insertParams = Arrays.asList(benchmarkResultBean.getProduct(),benchmarkResultBean.getVersion(), benchmarkResultBean.getScenario(), 
                        (double)benchmarkResultBean.getBenchmarkResult().get("tps"), (int)benchmarkResultBean.getBenchmarkResult().get("total"), (double)benchmarkResultBean.getBenchmarkResult().get("TP50th"),
                        (double)benchmarkResultBean.getBenchmarkResult().get("TP90th"), (double)benchmarkResultBean.getBenchmarkResult().get("TP95th"), (double)benchmarkResultBean.getBenchmarkResult().get("maxCost"), (double)benchmarkResultBean.getBenchmarkResult().get("minCost"), benchmarkResultBean.getSql());
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
    

  
}