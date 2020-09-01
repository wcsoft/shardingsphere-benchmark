
package org.apache.shardingsphere.benchmark.jmeter.common.datapreparation.jdbc;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.db.jdbc.JDBCDataSourceUtil;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JMeterJDBCCommonClear extends JMeterBenchmarkBase {
    public static DataSource dataSource;

    public static final int DB_PORT = 3306;

    public static final String DB_PASSWORD = "";

    public static final String DB_USER_NAME = "root";

    public static final String DB_HOST = "benchmark.db.jdbc.sbtest3";

    public static final String DB_NAME = "baitiao_test";

    public static final String TABLE_NAME = "sbtest3";

    public static final String DELETE_SQL = "truncate table " + TABLE_NAME;

    // Init mysql dataSource.
    static {
        dataSource = JDBCDataSourceUtil.initDb(DB_NAME, DB_HOST, DB_PORT, DB_USER_NAME, DB_PASSWORD);
    }

    /**
     * Execute truncate action to clear data.
     *
     * @param context
     * @return
     */
    @Override
    public SampleResult runTest(JavaSamplerContext context) {

        SampleResult results = new SampleResult();
        results.setSampleLabel("JdbcSingleTableDelete");
        results.sampleStart();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            JDBCDataSourceUtil.delete(connection, DELETE_SQL, null);
            results.setSuccessful(true);
        } catch (SQLException e) {
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

    @Override
    public Arguments getDefaultParameters() {
        return null;
    }

    /**
     * Get database connection.
     *
     * @param context
     */
    @Override
    public void setupTest(JavaSamplerContext context) {
    }

    /**
     * Close dataSource connection.
     *
     * @param context
     */
    @Override
    public void teardownTest(JavaSamplerContext context) {
    }
}

