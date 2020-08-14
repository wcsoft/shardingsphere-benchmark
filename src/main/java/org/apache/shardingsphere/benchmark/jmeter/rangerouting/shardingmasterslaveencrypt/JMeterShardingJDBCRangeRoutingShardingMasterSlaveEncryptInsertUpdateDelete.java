package org.apache.shardingsphere.benchmark.jmeter.rangerouting.shardingmasterslaveencrypt;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.shardingsphere.benchmark.db.jdbc.JDBCDataSourceUtil;
import org.apache.shardingsphere.benchmark.db.shardingjdbc.ShardingConfigType;
import org.apache.shardingsphere.benchmark.db.shardingjdbc.ShardingJDBCDataSourceFactory;
import org.apache.shardingsphere.benchmark.jmeter.JMeterBenchmarkBase;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JMeterShardingJDBCRangeRoutingShardingMasterSlaveEncryptInsertUpdateDelete extends JMeterBenchmarkBase {

    public static DataSource dataSource;

    static {
        try {
            dataSource = ShardingJDBCDataSourceFactory.newInstance(ShardingConfigType.RANGEROUTING_SHARDING_MASTERSLAVE_SHARDINGJDBC_CONFIG);
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

            String insertSql = (String) sqlConfig.get("ss.benchmark.rangerouting.shardingmasterslaveencrypt.insert.sql");
            List insertParams = convertParams((List) sqlConfig.get("ss.benchmark.rangerouting.shardingmasterslaveencrypt.insert.values"));
            System.out.println(insertSql);
            System.out.println(insertParams);
            rs = JDBCDataSourceUtil.insertBatch(connection, insertSql, insertParams);
            
/*            rs.next();
            Long id1 = rs.getLong(1);
            rs.next();
            Long id2 = rs.getLong(1);*/


            String updateSql = (String) sqlConfig.get("ss.benchmark.rangerouting.shardingmasterslaveencrypt.update.sql");
            List updateParams = convertParams((List) sqlConfig.get("ss.benchmark.rangerouting.shardingmasterslaveencrypt.update.values"));
/*            updateParams.add(id1);
            updateParams.add(id2);*/
            JDBCDataSourceUtil.update(connection, updateSql, updateParams);

            String deleteSql = (String) sqlConfig.get("ss.benchmark.rangerouting.shardingmasterslaveencrypt.delete.sql");
            List deleteParams = convertParams((List) sqlConfig.get("ss.benchmark.rangerouting.shardingmasterslaveencrypt.delete.values"));
/*            deleteParams.add(id1);
            deleteParams.add(id2);*/
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
