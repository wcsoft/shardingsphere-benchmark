package org.apache.shardingsphere.benchmark.jmeter.common.datapreparation.jdbc;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.db.jdbc.JDBCDataSourceUtil;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class JMeterJDBCCommonInsert extends JMeterBenchmarkBase {

    //public static Connection connection;

    public static DataSource dataSource;

    public static final int DB_PORT = 3306;

    public static final String DB_PASSWORD = "";

    public static final String DB_USER_NAME = "root";

    public static final String DB_HOST = "benchmark.db.jdbc.sbtest3";

    public static final String DB_NAME = "baitiao_test";

    public static final String TABLE_NAME = "sbtest3";

    public static final String INSERT_SQL = "INSERT INTO " + TABLE_NAME + " (k,c,pad) VALUES (?,?,?)";

    // Init mysql dataSource.
    static {
        dataSource = JDBCDataSourceUtil.initDb(DB_NAME, DB_HOST, DB_PORT, DB_USER_NAME, DB_PASSWORD);
    }

    /**
     * Execute insert action.
     *
     * @param context
     * @return
     */
    @Override
    public SampleResult runTest(JavaSamplerContext context) {

        SampleResult results = new SampleResult();
        results.setSampleLabel("JdbcInsert");
        results.sampleStart();
        Connection connection = null;

        try {

            connection = dataSource.getConnection();
            List insertParams = Arrays.asList(3, "##-####", "##-####");
            JDBCDataSourceUtil.insert(connection, INSERT_SQL, insertParams);
            results.setSuccessful(true);

        } catch (SQLException e) {
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
