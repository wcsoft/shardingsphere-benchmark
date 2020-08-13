package org.apache.shardingsphere.benchmark.common;

import java.util.HashMap;
import java.util.Map;

public class BenchmarkResultConverter {

    public static Map convertResult(String eachJMeterResult){

        Map performanceInfo = new HashMap<>();

        String[] eachJMeterDetails =  eachJMeterResult.split(",");
        performanceInfo.put("jMeterTime", Long.valueOf(eachJMeterDetails[0]).longValue());
        performanceInfo.put("jMeterCost", Integer.valueOf(eachJMeterDetails[1]).intValue());
        performanceInfo.put("isJMeterSuccess", (String)eachJMeterDetails[7]);
        return performanceInfo;
    }
}
