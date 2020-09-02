package org.apache.shardingsphere.benchmark.jmeter.fullrouting.encrypt;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.common.prepare.ProxyPreparation;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

public class JMeterProxyFullRoutingEncryptPreparation extends JMeterBenchmarkBase {
    
    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        
        SampleResult results = new SampleResult();
        results.setSampleLabel("JMeterProxyFullRoutingEncryptPreparation");
        results.sampleStart();
        String proxyEncryptYamlFile =  "src/main/resources/yaml/fullrouting/encrypt/proxy/config-proxy-fullrouting-encrypt.yaml";
        String proxyServerYamlFile =  "src/main/resources/yaml/server.yaml";
    
        ProxyPreparation.prepare(userConfig, proxyEncryptYamlFile, proxyServerYamlFile);
    
        results.sampleEnd();
        return results;
    }
    
    
}
