package com.charlie.jdbc.utils;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author AC
 * @version 1.0
 */
public class JDBCUtils {
    //define 4 static attributes
    private static String user;
    private static String password;
    private static String driver;
    private static String url;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\mysql.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            Class.forName(driver);
        } catch (Exception e) {
            /*
            1.convert compile exception as runtime exception
            2.invoker can choose catch the exception or treat it as default
             */
            throw new RuntimeException(e);
        }
    }


    /**
     * define the method to connect database return Connection
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * close resources
     * Statement is interface of class PrepareStatement
     */
    public static void closeResources(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(connection!= null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
