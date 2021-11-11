package com.charlie.jdbc.transaction;

import com.charlie.jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * demonstrate how control transaction in jdbc
 *
 * @author AC
 * @version 1.0
 */
public class JDBCTransaction {
    /**
     * setAutoCommit() set the connection not commit automatically
     * when setAutoCommit(false), then start transaction!
     * when there is any exception happen the program will roll back
     * rollback() method will return the program to the transaction start point
     * the start point is generate by setAutoCommit(false)
     */
    @Test
    public void transaction() {
        Connection connection = null;
        String sql = "update account set balance=balance-100 where id=1";
        String sql2 = "update account set balance=balance+100 where id=2";
        PreparedStatement preparedStatement = null;

        try {
            /*
            in default case the connection object will commit automatically
             */
            connection = JDBCUtils.getConnection();

            /*
            set the connection not commit automatically
            meanwhile start transaction
             */
            connection.setAutoCommit(false);    //start transaction

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();  //execute then commit

            /*
            here we insert code which can produce exception
            that can break program running
            so sql2 would be not execute
             */

            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();

            //commit
            connection.commit();
        } catch (SQLException e) {
            /*
            here we can roll back, cancel the sql execution
            default back to setAutoCommit(false)
             */
            System.out.println("exception happened, roll back to save point");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResources(null, preparedStatement, connection);

        }
    }


    //test no transaction
    public void noTransaction() {
        Connection connection = null;
        String sql = "update account set balance=balance-100 where id=1";
        String sql2 = "update account set balance=balance+100 where id=2";
        PreparedStatement preparedStatement = null;


        try {
            connection = JDBCUtils.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            /*
            here we insert code which can produce exception
            that can break program running
            so sql2 would be not execute
             */

            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResources(null, preparedStatement, connection);
        }
    }


}
