package com.charlie.jdbc.batch;

import com.charlie.jdbc.myjdbc.JDBCInterface;
import com.charlie.jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author AC
 * @version 1.0
 */

public class BatchTest {
    //batch way to add 5000 rows data into table admin2

    /**
     * add all sql into batch package to execute all
     * the batch similar like a buffer zone to store sql terms
     *
     * @throws Exception
     */
    @Test
    public void batch() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values(null, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("batch execution start...");
        long start = System.currentTimeMillis();

        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "cat" + i);
            preparedStatement.setString(2, "123456");

            //add sql into batch package
            /*
            public void addBatch() throws SQLException {
                synchronized(this.checkClosed().getConnectionMutex()) {
                    if (this.batchedArgs == null) {
                        this.batchedArgs = new ArrayList(); //create a array list
                    }

                    for(int i = 0; i < this.parameterValues.length; ++i) {
                        this.checkAllParametersSet(this.parameterValues[i], this.parameterStreams[i], i);
                    }

                    this.batchedArgs.add(new PreparedStatement.BatchParams(this.parameterValues, this.parameterStreams, this.isStream, this.streamLengths, this.isNull));
                }
            }
             */
            preparedStatement.addBatch();

            //execute sql when achieve 1000 rows each time
            if ((i + 1) % 1000 == 0) {
                preparedStatement.executeBatch();

                //clear buf
                preparedStatement.clearBatch();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Batch way to execute sql cost time = " + (end - start));

        JDBCUtils.closeResources(null, preparedStatement, connection);

    }


    //traditional method add 5000 rows data into table admin2
    @Test
    public void noBatch() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values(null, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("execution start...");
        long start = System.currentTimeMillis();    //start time

        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "uname" + i);
            preparedStatement.setString(2, "pwd");
            preparedStatement.executeUpdate();
        }

        long end = System.currentTimeMillis(); //end time
        System.out.println("traditional method cost time = " + (end - start));

        JDBCUtils.closeResources(null, preparedStatement, connection);
    }
}
