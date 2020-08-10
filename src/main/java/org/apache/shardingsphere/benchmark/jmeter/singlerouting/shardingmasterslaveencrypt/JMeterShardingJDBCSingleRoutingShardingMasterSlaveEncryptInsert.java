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
import java.util.List;

public class JMeterShardingJDBCSingleRoutingShardingMasterSlaveEncryptInsert extends JMeterBenchmarkBase {

    public static DataSource dataSource;

    static {
        try {
            dataSource = ShardingJDBCDataSourceFactory.newInstance(ShardingConfigType.SINGLEROUTING_SHARDING_MASTERSLAVE_SHARDINGJDBC_CONFIG);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public SampleResult runTest(JavaSamplerContext context) {

        SampleResult results = new SampleResult();
        results.setSampleLabel("JMeterShardingJDBCFullRoutingEncryptInsert");
        results.sampleStart();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String insertSql = (String) sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.insert.sql");
            List insertParams = convertParams((List) sqlConfig.get("ss.benchmark.singlerouting.shardingmasterslaveencrypt.insert.values"));
            JDBCDataSourceUtil.insert(connection, insertSql, insertParams);
            results.setSuccessful(true);
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
