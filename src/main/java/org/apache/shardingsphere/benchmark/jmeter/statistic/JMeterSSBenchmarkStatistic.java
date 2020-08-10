package org.apache.shardingsphere.benchmark.jmeter.statistic;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.bean.BenchmarkResultBean;
import org.apache.shardingsphere.benchmark.common.BenchmarkExcelWriter;
import org.apache.shardingsphere.benchmark.common.BenchmarkFullroutingStatistic;
import org.apache.shardingsphere.benchmark.common.BenchmarkRangeroutingStatistic;
import org.apache.shardingsphere.benchmark.common.BenchmarkSingleroutingStatistic;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JMeterSSBenchmarkStatistic extends JMeterBenchmarkBase {
    
    public static Map<String, List> benchmarkResult = new HashMap<String, List>();
    public static List<BenchmarkResultBean> fullRoutingResult = new ArrayList<BenchmarkResultBean>();
    public static List<BenchmarkResultBean> rangeRoutingResult = new ArrayList<BenchmarkResultBean>();
    public static List<BenchmarkResultBean> singleRoutingResult = new ArrayList<BenchmarkResultBean>();
    


    @Override
    public SampleResult runTest(JavaSamplerContext context) {

        SampleResult results = new SampleResult();
        results.setSampleLabel("JMeterSSBenchmarkStatistic");
        results.sampleStart();

        List<BenchmarkResultBean> fullRoutingResult = BenchmarkFullroutingStatistic.calculateFullroutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion);
        List<BenchmarkResultBean> rangeRoutingResult = BenchmarkRangeroutingStatistic.calculateRangeRoutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion);
        List<BenchmarkResultBean> singleRoutingResult = BenchmarkSingleroutingStatistic.calculateSingleRoutingScenarioResult(benchmarkResultPath, sqlConfig, benchmarkVersion);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "full-routing", true, 0, fullRoutingResult);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "range-routing", false, 0, rangeRoutingResult);
        BenchmarkExcelWriter.writeExcel((String)benchmarkResultPath.get("ss.benchmark.excel.result"), "single-routing", false, 0, singleRoutingResult);
        results.sampleEnd();
        return results;
    }
    

  
}