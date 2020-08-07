package org.apache.shardingsphere.benchmark.jmeter.fullrouting.masterslave;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.db.jdbc.JDBCDataSourceUtil;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Refactor old case test_plan/jdbc_sharding_new/select_one.jmx
 *
 * Sharding scenario

 *  Its actualDataNodes: ds{0..3}.sbtest${0..1023}
 *  Its dataSource.algorithm expression: ds_${id % 4}
 *  Its table.algorithm expression: sbtest${k % 1024}
 *  Its type: INLINE
 **/

public class JMeterProxyFullRoutingMasterSlaveUpdate extends JMeterBenchmarkBase {

    public static DataSource dataSource;

    static {
        dataSource = JDBCDataSourceUtil.initDb((String)dbConfig.get("ss.proxy.db.datasource"),
                (String)dbConfig.get("ss.proxy.host"), (int)dbConfig.get("ss.proxy.port"),
                (String)dbConfig.get("ss.proxy.db.username"), (String)dbConfig.get("ss.proxy.db.password"));
    }

    @Override
    public SampleResult runTest(JavaSamplerContext context) {

        SampleResult results = new SampleResult();
        results.setSampleLabel("JMeterProxyFullRoutingEncryptUpdate");
        results.sampleStart();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String updateSql = (String)sqlConfig.get("ss.benchmark.fullrouting.masterslave.update.sql");
            List updateParams = convertParams((List)sqlConfig.get("ss.benchmark.fullrouting.masterslave.update.values"));
            JDBCDataSourceUtil.update(connection, updateSql, updateParams);
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
