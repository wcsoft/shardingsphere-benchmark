package org.apache.shardingsphere.benchmark.common.prepare;

import org.apache.shardingsphere.benchmark.common.file.util.FileUtil;
import org.apache.shardingsphere.benchmark.common.file.util.ProcessUtil;

import java.io.File;
import java.util.Map;

public class ProxyPreparation {
    
    public static void prepare(Map userConfig, String ruleConfigPath, String serverConfigFile){
        String benchmarkVersion = (String)userConfig.get("shardingsphere_version");
        String shardingSphereBasePath = (String)userConfig.get("shardingsphere_project_base_path");
        String proxyBasePath = (String)userConfig.get("shardingsphere_benchmark_proxy_machine_proxy_dest_base_path");
        String benchmarkBasePath = (String)userConfig.get("shardingsphere_benchmark_project_base_path");
        String ruleConfigFullPath = benchmarkBasePath + "/" + ruleConfigPath;
        String serverConfigFullFile = benchmarkBasePath + "/" + serverConfigFile;
    
        String proxySourcePath = "";
        String proxyDestBasePath = "";
    
        if (benchmarkVersion.equals("4.1.1")){
            proxySourcePath = shardingSphereBasePath + "/sharding-distribution/sharding-proxy-distribution/target/apache-shardingsphere-5.0.0-RC1-SNAPSHOT-sharding-proxy-bin.tar.gz";
            proxyDestBasePath = proxyBasePath + "/apache-shardingsphere-5.0.0-RC1-SNAPSHOT-sharding-proxy-bin";
        } else {
            proxySourcePath = shardingSphereBasePath + "/shardingsphere-distribution/shardingsphere-proxy-distribution/target/apache-shardingsphere-5.0.0-RC1-SNAPSHOT-shardingsphere-proxy-bin.tar.gz";
            proxyDestBasePath = proxyBasePath + "/apache-shardingsphere-5.0.0-RC1-SNAPSHOT-shardingsphere-proxy-bin";
        }
    
        ProcessUtil.killProcess("proxy");
    
        if (FileUtil.isExisted(proxyDestBasePath)){
            FileUtil.deleteFileByRecursion(new File(proxyDestBasePath));
        }
        FileUtil.decompressFile(proxySourcePath, proxyBasePath);
    
        String proxyConfigPath = proxyDestBasePath + "/conf";
        FileUtil.deleteFileBySuffix(proxyConfigPath, "yaml");
        FileUtil.copyFile(ruleConfigFullPath, proxyConfigPath);
        FileUtil.copyFile(serverConfigFullFile, proxyConfigPath);
    
        String startUpScript = "";
        if (System.getProperty("os.name").toLowerCase().contains("linux")){
            startUpScript = proxyDestBasePath + "/bin/start.sh";
        } else {
            startUpScript = proxyDestBasePath + "/bin/start.bat";
        }
    
        ProcessUtil.startUpProcess(startUpScript);
    }
}
