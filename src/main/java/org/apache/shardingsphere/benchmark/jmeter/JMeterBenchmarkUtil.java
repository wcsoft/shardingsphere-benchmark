package org.apache.shardingsphere.benchmark.jmeter;

import org.apache.shardingsphere.benchmark.common.PropertiesUtil;
import org.apache.shardingsphere.benchmark.db.jdbc.JDBCDataSourceUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JMeterBenchmarkUtil {
    
    /**
     * Init dataSource config for all of benchmark scenarios.
     */
    public static Map initDbConfig() {
        Map dbConfig = new HashMap<>();
        Properties dbConfigProp = new Properties();
        try {
            InputStream in = PropertiesUtil.class.getResourceAsStream("/config/dbconfig.properties");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            dbConfigProp.load(in);
            Iterator<String> it = dbConfigProp.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                if (key.contains(".port") || key.equals("maximumpoolsize") || key.equals("connectiontimeout") || key.equals("idletimeout")
                        || key.equals("maxlifetime") || key.equals("prepstmtcachesize") || key.equals("prepstmtcachesqllimit") || key.equals("nettimeoutforstreamingresults")) {
                    dbConfig.put(key, Integer.valueOf(dbConfigProp.getProperty(key)).intValue());
                } else {
                    dbConfig.put(key, dbConfigProp.getProperty(key));
                }
            }
            in.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConfig;
    }

    /**
     * Init sql config for all of benchmark scenarios.
     */
    public static Map initSqlConfig() {
        Properties sqlProp = new Properties();
        Map sqlConfig = new HashMap<>();
        try {
            InputStream in = PropertiesUtil.class.getResourceAsStream("/config/sqlconfig.properties");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            sqlProp.load(in);
            String values = "";
            Iterator<String> it = sqlProp.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                if (key.contains(".values")) {
                    values = sqlProp.getProperty(key);
                    if (values != null && values.length() > 0) {
                        String[] arrayValues = values.split(",");
                        List listValues = new ArrayList();
                        for (int i = 0; i < arrayValues.length; i++) {
                            String[] subs = arrayValues[i].split(":");
                            if ("Int".equals(subs[0])) {
                                listValues.add(Integer.valueOf(subs[1]).intValue());
                            } else if ("Float".equals(subs[0])) {
                                listValues.add(Float.valueOf(subs[1]).floatValue());
                            } else if ("Long".equals(subs[0])) {
                                listValues.add(Long.valueOf(subs[1]).longValue());
                            } else if ("String".equals(subs[0])) {
                                listValues.add(String.valueOf(subs[1]));
                            } else if ("".equals(((String) arrayValues[i]))) {
                                listValues.add((String) arrayValues[i]);
                            } else {
                                listValues.add((String) subs[1]);
                            }
                        }
                        sqlConfig.put(key, listValues);
                    } else {
                        sqlConfig.put(key, sqlProp.getProperty(key));
                    }

                } else {
                    sqlConfig.put(key, sqlProp.getProperty(key));
                }


            }
            in.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlConfig;
    }

    /**
     * Store path of JMeter result file for benchmark into memory.
     */
    public static Map initBenchmarkResultPath() {
        Properties dbConfigProp = new Properties();
        Map benchmarkResultPath = new HashMap<>();
        try {
            InputStream in = PropertiesUtil.class.getResourceAsStream("/config/benchmark-result.properties");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            dbConfigProp.load(in);
            Iterator<String> it = dbConfigProp.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                benchmarkResultPath.put(key, dbConfigProp.getProperty(key));
            }
            in.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return benchmarkResultPath;
    }
    

    public static  List setParams(List values) {
        List sqlValues = new ArrayList();
        if (values != null) {
            for (int i = 0; i < values.size(); i++) {
                sqlValues.add(values.get(i));
            }
        }
        return sqlValues;
    }

    public static List convertParams(List values) {
        List sqlValues = new ArrayList();
        if (values != null) {
            for (int i = 0; i < values.size(); i++) {
                sqlValues.add(values.get(i));
            }
        }
        return sqlValues;
    }

    public void insertRecords(Connection connection, String sql, List params, Map dbConfig) throws SQLException {
        int tableCount = Integer.valueOf((String)dbConfig.get("benchmark.table.count")).intValue();
        if (params != null) {
            for (int i = 0; i < tableCount; i++) {
                List dynamicParams = new ArrayList(params.size());
                for (int j = 0; j < params.size(); j++) {
                    if (params.get(j) instanceof Integer) {
                        dynamicParams.add(i);
                    } else if (params.get(j) instanceof String) {
                        dynamicParams.add((String) params.get(j) + i);
                    }
                }
                JDBCDataSourceUtil.insert(connection, sql, dynamicParams);
            }
        }
    }
    
    public static String initBenchmarkVersion(){
        Properties dbConfigProp = new Properties();
        String benchmarkVersion = "";
        try {
            InputStream in = PropertiesUtil.class.getResourceAsStream("/config/benchmark-version.properties");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            dbConfigProp.load(in);
            Iterator<String> it = dbConfigProp.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                if("ss.benchmark.version".equals(key)){
                    benchmarkVersion = dbConfigProp.getProperty(key);
                    break;
                }
            }
            in.close();
            br.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return benchmarkVersion;
    }
}

