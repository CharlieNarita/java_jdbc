package com.charlie.jdbc.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author AC
 * @version 1.0
 */
public class JdbcDriverManager {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) cls.newInstance();

        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String password = "12345";

        DriverManager.registerDriver(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

        String sql = "insert into actor values(null, 'lisa', 'F', '1998-7-6', 188665)";

        Statement statement = connection.createStatement();
        int row = statement.executeUpdate(sql);
        System.out.println(row > 0 ? "ok" : "fail");

        statement.close();
        connection.close();
    }
}
