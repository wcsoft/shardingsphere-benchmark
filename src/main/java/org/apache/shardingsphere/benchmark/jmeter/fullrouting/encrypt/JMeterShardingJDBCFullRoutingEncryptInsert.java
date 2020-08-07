package org.apache.shardingsphere.benchmark.jmeter.fullrouting.encrypt;

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

public class JMeterShardingJDBCFullRoutingEncryptInsert extends JMeterBenchmarkBase {

    public static DataSource dataSource;

    public static final String TABLE_NAME = "sbtest";

    public static final String INSERT_SQL = "INSERT INTO " + TABLE_NAME + " (k,c,pad) VALUES (?,?,?)";

    static{
        try {
            dataSource = ShardingJDBCDataSourceFactory.newInstance (ShardingConfigType.FULLROUTING_ENCRYPT_SHARDINGJDBC_CONFIG);
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
            String insertSql = (String)sqlConfig.get("ss.benchmark.fullrouting.encrypt.insert.sql");
            List insertParams = convertParams((List)sqlConfig.get("ss.benchmark.fullrouting.encrypt.insert.values"));
            JDBCDataSourceUtil.insert(connection, insertSql, insertParams);
            results.setSuccessful(true);
        } catch (SQLException e) {
            results.setSuccessful(false);
            e.printStackTrace();
        } catch (Exception e){
            results.setSuccessful(false);
            e.printStackTrace();
        }finally {
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
