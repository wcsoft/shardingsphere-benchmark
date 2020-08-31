package org.apache.shardingsphere.benchmark.jmeter.common.config;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.common.properties.BenchmarkConfigProperties;
import org.apache.shardingsphere.benchmark.common.xml.BenchmarkConfigJmx;
import org.apache.shardingsphere.benchmark.common.yaml.BenchmarkConfigYaml;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

public class JMeterConfigBenchmark  extends JMeterBenchmarkBase {
    
    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult results = new SampleResult();
        results.setSampleLabel("JMeterConfigBenchmark");
        results.sampleStart();
        
        String benchmarkBasePath = (String)userConfig.get("shardingsphere_benchmark_project_base_path");
        String benchmarkOutputBasePath = (String)userConfig.get("shardingsphere_benchmark_result_base_path");
        
        int shardingDbCount = Integer.valueOf((String)userConfig.get("shardingsphere_sharding_db_count")).intValue();
        int shardingTableCount = Integer.valueOf((String)userConfig.get("shardingsphere_sharding_table_count")).intValue();
        int maxConnectionCount = Integer.valueOf((String)userConfig.get("shardingsphere_maximum_connection_count")).intValue();
        int minConnectionCount = Integer.valueOf((String)userConfig.get("shardingsphere_minimum_connection_count")).intValue();
        int maxConnectionPerQueryCount = Integer.valueOf((String)userConfig.get("shardingsphere_maximum_connection_count_for_each_query")).intValue();
        int jmeterLoopCount = Integer.valueOf((String)userConfig.get("shardingsphere_jmeter_loop_count")).intValue();
        int jmeterConcurrencyCount = Integer.valueOf((String)userConfig.get("shardingsphere_jmeter_concurrency_count")).intValue();
    
    
        BenchmarkConfigJmx.modifyBenchmarkOutputBasePath(benchmarkBasePath, benchmarkOutputBasePath, jmeterConcurrencyCount, jmeterLoopCount);
        BenchmarkConfigProperties.modifyBenchmarkOutputConfig(benchmarkBasePath, benchmarkOutputBasePath);
        BenchmarkConfigYaml.modifyBenchmarkYamlFile(benchmarkBasePath, shardingDbCount, shardingTableCount, maxConnectionCount, minConnectionCount, maxConnectionPerQueryCount);
    
        results.sampleEnd();
        return results;
    }
    
}
