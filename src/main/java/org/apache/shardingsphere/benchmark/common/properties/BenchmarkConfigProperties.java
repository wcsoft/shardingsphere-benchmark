package org.apache.shardingsphere.benchmark.common.properties;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class BenchmarkConfigProperties {
    
    public static Properties getBenchmarkOutputConfig(String benchmarkBasePath, String outputBasePath) {
    
        HashMap benchmarkResultPath = new HashMap<String, String>();
        Properties benchmarkResultConfigProp = new Properties();
        try {
            InputStream in = BenchmarkConfigProperties.class.getResourceAsStream("/config/benchmark-result.properties");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            benchmarkResultConfigProp.load(in);
            Iterator<String> it = benchmarkResultConfigProp.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = benchmarkResultConfigProp.getProperty(key);
                value = value.replace("/basepath", outputBasePath);
                benchmarkResultConfigProp.setProperty(key, value);
            }
            in.close();
            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return benchmarkResultConfigProp;
    }
    
    public static void modifyBenchmarkOutputConfig(String benchmarkBasePath, String outputBasePath){
         FileOutputStream fileOutputStream = null;
        try {
            Properties benchmarkResultConfigProp = getBenchmarkOutputConfig(benchmarkBasePath, outputBasePath);
            String filePath = benchmarkBasePath + "/src/main/resources/config/benchmark-result.properties";
            System.out.println(filePath);
            fileOutputStream = new FileOutputStream(filePath);
            benchmarkResultConfigProp.store(fileOutputStream, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args){
        String benchmarkBasePath = "D:/shardingsphere-benchmark";
        String outputBasePath = "/export/shardingsphere-benchmark/result";
        modifyBenchmarkOutputConfig(benchmarkBasePath, outputBasePath);
    }
}
