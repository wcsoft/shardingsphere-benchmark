package org.apache.shardingsphere.benchmark.jmeter.singlerouting.shardingmasterslaveencrypt;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.db.jdbc.JDBCDataSourceUtil;
import org.apache.shardingsphere.benchmark.db.shardingjdbc.ShardingConfigType;
import org.apache.shardingsphere.benchmark.db.shardingjdbc.ShardingJDBCDataSourceFactory;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class JMeterShardingJDBCSingleRoutingShardingMasterSlaveEncryptSelect extends JMeterBenchmarkBase {

    public static DataSource dataSource;
/*    public static Map sqlConfig = new HashMap<>();
    public static Map dbConfig = new HashMap<>();
    public static Map benchmarkResultPath = new HashMap<>();
    public static String benchmarkVersion;*/

    static {
        try {
            dataSource = ShardingJDBCDataSourceFactory.newInstance(ShardingConfigType.SINGLEROUTING_SHARDING_MASTERSLAVE_SHARDINGJDBC_CONFIG);
/*            sqlConfig = JMeterBenchmarkUtil.initSqlConfig();
            dbConfig = JMeterBenchmarkUtil.initDbConfig();
            benchmarkResultPath = JMeterBenchmarkUtil.initBenchmarkResultPath();
            benchmarkVersion = JMeterBenchmarkUtil.initBenchmarkVersion();*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public SampleResult runTest(JavaSamplerContext context) {

        SampleResult results = new SampleResult();
        results.setSampleLabel("JMeterShardingJDBCFullRoutingEncryptSelect");
        results.sampleStart();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            String selectSql = (String) sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.select.sql");
            //List selectParams = convertParams((List) sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.select.values"));
            //String selectSql = "select id, k from sbtest where k=1";
            //List selectParams = JMeterBenchmarkUtil.convertParams((List) sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.select.values"));
            JDBCDataSourceUtil.select(connection, selectSql, null);
            results.setSuccessful(true);
        } catch (SQLException e) {
            results.setSuccessful(false);
            e.printStackTrace();
        } catch (Exception e) {
            results.setSuccessful(false);
            e.printStackTrace();

        } finally {
            results.sampleEnd();
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return results;
    }
}
