package org.apache.shardingsphere.benchmark.jmeter.common.ss.masterslave;

import org.apache.jmeter.config.Arguments;
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

public class JMeterSSCommonMasterSlaveInsert extends JMeterBenchmarkBase {
    public static DataSource dataSource;

    static{
        try {
            dataSource = ShardingJDBCDataSourceFactory.newInstance(ShardingConfigType.FULLROUTING_MASTER_SLAVE_SHARDINGJDBC_CONFIG);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public SampleResult runTest(JavaSamplerContext context) {

        SampleResult results = new SampleResult();
        results.setSampleLabel("SJPerformanceMSInsert");
        results.sampleStart();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            String insertSql = (String)sqlConfig.get("common.ss.insert.sql");
            List insertParams = convertParams((List)sqlConfig.get("common.ss.insert.values"));
            insertRecords(connection, insertSql, insertParams);
        } catch (SQLException e) {
            results.setSuccessful(false);
            e.printStackTrace();
        } catch (Exception e){
            results.setSuccessful(false);
            e.printStackTrace();
        }finally {
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
