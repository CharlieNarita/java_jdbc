package com.charlie.jdbc.preparedstatement;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

/**
 * demonstrate dml sql
 *
 * @author AC
 * @version 1.0
 */
public class PreparedStatementDemo2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("please enter admin:");
        String admin_name = scanner.nextLine();
        System.out.print("please enter pwd:");
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

        //sql
        //String sql = "update admin set pwd=? where name=?";
        String sql = "insert into admin values(?, ?)";

        //get connection
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //give '?' values
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_pwd);

        //execute update operation
        int row = preparedStatement.executeUpdate();
        System.out.println(row > 0 ? "ok" : "fail");

        //close all resource
        preparedStatement.close();
        connection.close();
    }
}
