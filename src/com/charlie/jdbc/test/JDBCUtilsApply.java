package com.charlie.jdbc.test;

import com.charlie.jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author AC
 * @version 1.0
 */
public class JDBCUtilsApply {
    public static void main(String[] args) {
        new JDBCUtilsApply().testDML();
        new JDBCUtilsApply().testQuery();
    }

    //test query
    @Test
    public void testQuery() {
        Connection connection = null;

        String sql = "select * from actor where id=?";

        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String date = resultSet.getString("birthdate");
                String phone = resultSet.getString("phone");
                System.out.println(id + "\t" + name + "\t" + gender + "\t" + date + "\t" + phone);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResources(resultSet, preparedStatement, connection);
        }
    }

    //test insert, update, delete
    @Test
    public void testDML() {
        Connection connection = null;

        String sql = "update actor set name=? where id=?";

        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "cat");
            preparedStatement.setInt(2, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResources(null, preparedStatement, connection);
        }
    }
}
