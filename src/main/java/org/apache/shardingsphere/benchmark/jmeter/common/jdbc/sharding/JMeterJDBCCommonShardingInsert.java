package org.apache.shardingsphere.benchmark.jmeter.common.jdbc.sharding;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.db.jdbc.JDBCDataSourceUtil;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JMeterJDBCCommonShardingInsert extends JMeterBenchmarkBase {

    public static DataSource dataSource;

    static {
        dataSource = JDBCDataSourceUtil.initDb((String) dbConfig.get("jdbc.benchmark.fullrouting.sharding.ds0.datasource"),
                (String) dbConfig.get("jdbc.benchmark.fullrouting.sharding.ds0.host"), (int) dbConfig.get("jdbc.benchmark.fullrouting.sharding.ds0.port"),
                (String) dbConfig.get("jdbc.benchmark.fullrouting.sharding.ds0.username"), (String) dbConfig.get("jdbc.benchmark.fullrouting.sharding.ds0.password"));
    }

    @Override
    public SampleResult runTest(JavaSamplerContext context) {

        SampleResult results = new SampleResult();
        results.setSampleLabel("SJPerformanceMSInsert");
        results.sampleStart();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            String insertSql = (String) sqlConfig.get("common.jdbc.insert.sql");
            List insertParams = convertParams((List) sqlConfig.get("common.jdbc.insert.values"));
            insertRecords(connection, insertSql, insertParams);
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
