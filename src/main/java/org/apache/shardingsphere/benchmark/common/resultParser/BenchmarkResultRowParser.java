package org.apache.shardingsphere.benchmark.common.resultParser;

import java.util.HashMap;
import java.util.Map;

public class BenchmarkResultRowParser {

    public static Map convertResult(String eachJMeterResult) {

        Map performanceInfo = new HashMap<>();

        String[] eachJMeterDetails = eachJMeterResult.split(",");
        performanceInfo.put("jMeterTime", Double.valueOf(eachJMeterDetails[0]).doubleValue());
        performanceInfo.put("jMeterCost", Double.valueOf(eachJMeterDetails[1]).doubleValue());
        performanceInfo.put("isJMeterSuccess", (String) eachJMeterDetails[7]);
        return performanceInfo;
    }
}
