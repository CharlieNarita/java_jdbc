package com.charlie.jdbc.statement;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author AC
 * @version 1.0
 */
public class StatementDemo {
    public static void main(String[] args) throws Exception {

        /*
        user nextLine() instead of next() method
        cause next() method just can read words without space
        nextLine() can read a line with space split
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("please enter admin name:");
        String admin_name = scanner.nextLine();
        System.out.print("please enter admin pwd:");
        String admin_pwd = scanner.nextLine();

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //register driver
        Class.forName(driver);

        //get connection by DriverManager
        Connection connection = DriverManager.getConnection(url, user, password);

        //get safe PreparedStatement object
        Statement statement = connection.createStatement();

        //sql
        String sql = "select name, pwd from admin where name='"
                + admin_name + "' and pwd = '" + admin_pwd + "'";

        //get result set by executeQuery(sql)
        ResultSet resultSet = statement.executeQuery(sql);

        //check result
        if (resultSet.next()) {
            System.out.println("login ok");
        } else {
            System.out.println("login fail");
        }

        //close resources
        resultSet.close();
        statement.close();
        connection.close();
    }
}
