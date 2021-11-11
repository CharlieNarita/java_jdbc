package com.charlie.jdbc.preparedstatement;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

/**
 * demonstrate prepared statement
 * @author AC
 * @version 1.0
 */
public class PreparedStatementDemo {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.print("please enter admin name:");
        String admin_name = scanner.nextLine();
        System.out.print("please enter admin pwd:");
        String admin_pwd = scanner.nextLine();

        //config file
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        //register driver
        Class.forName(driver);

        //get connection
        Connection connection = DriverManager.getConnection(url, user, password);

        //write sql, '?' is placeholder
        String sql = "select name, pwd from admin where name=? and pwd=?";

        //get PreparedStatement object which implement Statement interface
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //give '?' values
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_pwd);

        //execute sql
        /*
        use executeUpdate(sql) if dml(update, insert, delete)
        use executeQuery(sql) if just query
         */
        //attention: here executeQuery() has not parameter cause sql is already pass in
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            System.out.println("login ok");
        } else {
            System.out.println("login fail");
        }

        //close all resources
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
