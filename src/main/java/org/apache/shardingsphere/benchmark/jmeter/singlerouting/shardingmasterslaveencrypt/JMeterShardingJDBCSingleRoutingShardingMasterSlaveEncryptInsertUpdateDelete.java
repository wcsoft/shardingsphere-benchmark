package org.apache.shardingsphere.benchmark.jmeter.singlerouting.shardingmasterslaveencrypt;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.db.jdbc.JDBCDataSourceUtil;
import org.apache.shardingsphere.benchmark.db.shardingjdbc.ShardingConfigType;
import org.apache.shardingsphere.benchmark.db.shardingjdbc.ShardingJDBCDataSourceFactory;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkUtil;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JMeterShardingJDBCSingleRoutingShardingMasterSlaveEncryptInsertUpdateDelete extends AbstractJavaSamplerClient {

    public static DataSource dataSource;
    public static Map sqlConfig = new HashMap<>();
    public static Map dbConfig = new HashMap<>();
    public static Map benchmarkResultPath = new HashMap<>();
    public static String benchmarkVersion;

    static {
        try {
            dataSource = ShardingJDBCDataSourceFactory.newInstance(ShardingConfigType.SINGLEROUTING_SHARDING_MASTERSLAVE_SHARDINGJDBC_CONFIG);
            sqlConfig = JMeterBenchmarkUtil.initSqlConfig();
            dbConfig = JMeterBenchmarkUtil.initDbConfig();
            benchmarkResultPath = JMeterBenchmarkUtil.initBenchmarkResultPath();
            benchmarkVersion = JMeterBenchmarkUtil.initBenchmarkVersion();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public SampleResult runTest(JavaSamplerContext context) {

        ResultSet rs = null;
        SampleResult results = new SampleResult();
        results.setSampleLabel("SJPerformanceMSSelect");
        results.sampleStart();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            String insertSql = (String) sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.insert.sql");
            List insertParams = JMeterBenchmarkUtil.convertParams((List) sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.insert.values"));
            JDBCDataSourceUtil.insert(connection, insertSql, insertParams);

            String updateSql = (String) sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.update.sql");
            List updateParams = JMeterBenchmarkUtil.convertParams((List) sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.update.values"));
            JDBCDataSourceUtil.update(connection, updateSql, updateParams);

            String deleteSql = (String) sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.delete.sql");
            List deleteParams = JMeterBenchmarkUtil.convertParams((List) sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.delete.values"));
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
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            results.sampleEnd();
        }

        return results;
    }
}
