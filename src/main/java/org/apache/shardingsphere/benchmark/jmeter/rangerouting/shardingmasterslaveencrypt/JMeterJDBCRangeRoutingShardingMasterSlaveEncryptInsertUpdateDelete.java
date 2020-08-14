package org.apache.shardingsphere.benchmark.jmeter.rangerouting.shardingmasterslaveencrypt;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.db.jdbc.JDBCDataSourceUtil;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JMeterJDBCRangeRoutingShardingMasterSlaveEncryptInsertUpdateDelete extends JMeterBenchmarkBase {

    public static DataSource dataSource;

    static {
        dataSource = JDBCDataSourceUtil.initDb((String) dbConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.ds0.datasource"),
                (String) dbConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.ds0.host"), (int) dbConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.ds0.port"),
                (String) dbConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.ds0.username"), (String) dbConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.ds0.password"));
    }

    @Override
    public SampleResult runTest(JavaSamplerContext context) {
    
        ResultSet rs = null;
        SampleResult results = new SampleResult();
        results.setSampleLabel("SJPerformanceMSInsert");
        results.sampleStart();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            String insertSql = (String) sqlConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.insert.sql");
            List insertParams = convertParams((List) sqlConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.insert.values"));
            rs = JDBCDataSourceUtil.insert(connection, insertSql, insertParams);

            String updateSql = (String) sqlConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.update.sql");
            List updateParams = convertParams((List) sqlConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.update.values"));
            JDBCDataSourceUtil.update(connection, updateSql, updateParams);

            String deleteSql = (String) sqlConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.delete.sql");
            List deleteParams = convertParams((List) sqlConfig.get("jdbc.benchmark.rangerouting.shardingmasterslaveencrypt.delete.values"));
            JDBCDataSourceUtil.delete(connection, deleteSql, deleteParams);

            results.setSuccessful(true);
        } catch (SQLException e) {
            results.setSuccessful(false);
            e.printStackTrace();
        } catch (Exception e) {
            results.setSuccessful(false);
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            results.sampleEnd();
        }

        return results;
    }
}