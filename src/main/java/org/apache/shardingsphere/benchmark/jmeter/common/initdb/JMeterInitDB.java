
package org.apache.shardingsphere.benchmark.jmeter.common.initdb;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.db.jdbc.JDBCDataSourceUtil;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JMeterInitDB extends JMeterBenchmarkBase {

    @Override
    public SampleResult runTest(JavaSamplerContext context) {

        SampleResult results = new SampleResult();
        results.setSampleLabel("JMeterInitDB");
        results.sampleStart();

        int tableCount = Integer.valueOf((String)dbConfig.get("benchmark.table.count")).intValue();
        String createdDatabaseName = (String)dbConfig.get("benchmark.init.db.database.name");
        String createTableName = (String)dbConfig.get("benchmark.init.db.table.name");

        try {
            initDb((String) dbConfig.get("benchmark.init.db1.host"), createdDatabaseName, createTableName, tableCount);
            initDb((String) dbConfig.get("benchmark.init.db2.host"), createdDatabaseName, createTableName, tableCount);
            initDb((String) dbConfig.get("benchmark.init.db3.host"), createdDatabaseName, createTableName, tableCount);
            initDb((String) dbConfig.get("benchmark.init.db4.host"), createdDatabaseName, createTableName, tableCount);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            results.sampleEnd();
        }
        return results;
    }
    
    public void initDb(String host, String createdDatabaseName, String createdTableName, int tableCount) throws SQLException {
        
        String createdDatabaseSql = initDbSqlList.get(0);
        createdDatabaseSql = createdDatabaseSql.replace("default_database", createdDatabaseName);
        String createdTableSql = initDbSqlList.get(1);
        
        DataSource dataSource1 = JDBCDataSourceUtil.initDb("information_schema", host, (int) dbConfig.get("jdbc.benchmark.fullrouting.encrypt.ds0.port"), (String) dbConfig.get("jdbc.benchmark.fullrouting.encrypt.ds0.username"), (String) dbConfig.get("jdbc.benchmark.fullrouting.encrypt.ds0.password"));
        Connection connection1 = dataSource1.getConnection();
        Statement stat1 = connection1.createStatement();
        stat1.executeUpdate(createdDatabaseSql);
        stat1.close();
        connection1.close();
    
        DataSource createdDataSource1 = JDBCDataSourceUtil.initDb(createdDatabaseName, host, (int) dbConfig.get("jdbc.benchmark.fullrouting.encrypt.ds0.port"), (String) dbConfig.get("jdbc.benchmark.fullrouting.encrypt.ds0.username"), (String) dbConfig.get("jdbc.benchmark.fullrouting.encrypt.ds0.password"));
        Connection createdConnection1 = createdDataSource1.getConnection();
        Statement createdStat1 = createdConnection1.createStatement();
        String createdNoShardingSql = createdTableSql.replace("default_table", createdTableName);
        createdStat1.executeUpdate(createdNoShardingSql);
        createdStat1.close();
        createdConnection1.close();
        
        String shardingTableSql = "";
        for (int i = 0; i < tableCount; i++) {
            Connection createdConnection2 = createdDataSource1.getConnection();
            Statement createdStat2 = createdConnection2.createStatement();
            shardingTableSql = createdTableSql.replace("default_table", createdTableName + i);
            System.out.println(shardingTableSql);
            createdStat2.executeUpdate(shardingTableSql);
            createdStat2.close();
            createdConnection2.close();
        }
    
        
    }
    
}

