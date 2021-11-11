package com.charlie.jdbc.connection;


import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author AC
 * @version 1.0
 */
public class JdbcReflection {
    public static void main(String[] args) throws Exception {
        //refection get Driver class object
        Class<?> cls = Class.forName("com.mysql.jdbc.Driver");
        Object o = cls.newInstance();
        Driver driver = (Driver) o;

        //url info
        String url = "jdbc:mysql://localhost:3306/jdbc_test";

        //create property
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "12345");

        //get Connection obj
        Connection connect = driver.connect(url, info);
        System.out.println(connect);

        //get Statement obj
        Statement statement = connect.createStatement();

        //prepare the sql for executing
        String sql = "insert into actor values(null, 'mary', 'F', '1999-5-6', 123098)";

        //send sql information to server database
        int row = statement.executeUpdate(sql);
        System.out.println(row > 0 ? "ok!" : "fail");

        statement.close();
        connect.close();
    }
}
