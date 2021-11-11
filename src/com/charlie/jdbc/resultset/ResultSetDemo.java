package com.charlie.jdbc.resultset;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author AC
 * @version 1.0
 */
public class ResultSetDemo {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //register driver
        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

        //get statement
        Statement statement = connection.createStatement();

        //sql
        String sql = "select id, name, gender, birthdate from actor";
        /*

         */
        ResultSet resultSet = statement.executeQuery(sql);

        //get data by while loop
        while (resultSet.next()) {   //next() return false if no data
            int id = resultSet.getInt(1); //get this column one row
            String name = resultSet.getString(2);
            String gender = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            System.out.println(id + "\t" + name + "\t" + gender + "\t" + date);
        }

        //close resource
        resultSet.close();
        statement.close();
        connection.close();
    }
}
