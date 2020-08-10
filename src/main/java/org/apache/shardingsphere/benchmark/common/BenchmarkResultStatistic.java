package org.apache.shardingsphere.benchmark.common;

import java.io.*;
import java.util.*;

public class BenchmarkResultStatistic {

    public static Map benchmarkStatistic(String filePath) {
        FileInputStream fileStream = null;

        int failCount = 0;
        int successCount = 0;
        Map benchmarkResultStatistic = new HashMap();
        List jMeterCostsList = new ArrayList<>();
        List jMeterTimeList = new ArrayList<>();

        try {
            int totalCount = 0;
            fileStream = new FileInputStream(filePath);
            InputStreamReader readStream = new InputStreamReader(fileStream);
            BufferedReader reader = new BufferedReader(readStream);
            String eachJMeterResult = "";

            while ((eachJMeterResult = reader.readLine()) != null) {
                totalCount = totalCount + 1;
                if (totalCount > 1000000) {
                    Map eachPerformanceInfo = BenchmarkResultConverter.convertResult(eachJMeterResult);
                    jMeterCostsList.add(eachPerformanceInfo.get("jMeterCost"));
                    jMeterTimeList.add(eachPerformanceInfo.get("jMeterTime"));
                    if ("true".equals(eachPerformanceInfo.get("isJMeterSuccess"))) {
                        successCount = successCount + 1;
                    } else {
                        failCount = failCount + 1;
                    }
                }
            }

            int concurrentCount = jMeterCostsList.size();
            double startTime = Double.valueOf((Double) jMeterTimeList.get(0)).doubleValue();
            double endTime = Double.valueOf((Double) jMeterTimeList.get(concurrentCount - 1)).doubleValue();
            double totalTimeCost = (endTime - startTime) / 1000;
            double benchmarkTps = concurrentCount / totalTimeCost;
            Collections.sort(jMeterCostsList);
            int tp50thIndex = (int) 0.5 * jMeterCostsList.size();
            double tp50th = Double.valueOf((Double) jMeterCostsList.get(tp50thIndex)).doubleValue();
            int tp90thIndex = (int) 0.9 * jMeterCostsList.size();
            double tp90th = Double.valueOf((Double) jMeterCostsList.get(tp90thIndex)).doubleValue();
            int tp95thIndex = (int) 0.95 * jMeterCostsList.size();
            double tp95th = Double.valueOf((Double) jMeterCostsList.get(tp95thIndex)).doubleValue();
            double maxCost = Double.valueOf((Double) jMeterCostsList.get(concurrentCount - 1)).doubleValue();
            double minCost = Double.valueOf((Double) jMeterCostsList.get(0)).doubleValue();

            benchmarkResultStatistic.put("tps", benchmarkTps);
            benchmarkResultStatistic.put("total", concurrentCount);
            benchmarkResultStatistic.put("tp50th", tp50th);
            benchmarkResultStatistic.put("tp90th", tp90th);
            benchmarkResultStatistic.put("tp95th", tp95th);
            benchmarkResultStatistic.put("maxCost", maxCost);
            benchmarkResultStatistic.put("minCost", minCost);

            return benchmarkResultStatistic;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return benchmarkResultStatistic;
    }

}


