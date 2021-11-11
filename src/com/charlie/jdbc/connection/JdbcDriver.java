package com.charlie.jdbc.connection;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * do some simple operation
 * @author AC
 * @version 1.0
 */
public class JdbcDriver {
    public static void main(String[] args) throws SQLException {
        //1.register driver
        Driver driver = new Driver();

        //2.get connection, it is socket connection actually
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        Properties properties = new Properties();
        properties.setProperty("user","root"); //user
        properties.setProperty("password", "12345"); //password

        Connection connect = driver.connect(url, properties);

        //3.execute SQL instruction
        String sql = "update actor set name='jack' where id=1";
        Statement statement = connect.createStatement();
        int row = statement.executeUpdate(sql);
        System.out.println(row > 0 ? "ok!" : "fail");

        //4.close the resource
        statement.close();
        connect.close();
    }
}
