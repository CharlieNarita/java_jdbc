package com.charlie.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author AC
 * @version 1.0
 */
public class JdbcDriverManager2 {
    public static void main(String[] args) throws Exception {
        /*
        reflection load Driver class
        meanwhile finished the register

        //look this, register action is already done!
        DriverManager.registerDriver(new Driver());

        source code:
        static {
            try {
                DriverManager.registerDriver(new Driver());
            } catch (SQLException var1) {
                throw new RuntimeException("Can't register driver!");
            }
        }
         */
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String user = "root";
        String password = "12345";

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
