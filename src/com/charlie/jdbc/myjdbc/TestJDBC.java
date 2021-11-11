package com.charlie.jdbc.myjdbc;

/**
 * @author AC
 * @version 1.0
 */
public class TestJDBC {
    public static void main(String[] args) {
        Object connection = new MyJDBCImpl().getConnection();
        System.out.println(connection);
        new MyJDBCImpl().crud();
        new MyJDBCImpl().close();

        Object oracleConnection = new SimiOracleJDBC().getConnection();
        System.out.println(oracleConnection);
        new SimiOracleJDBC().crud();
        new SimiOracleJDBC().close();
    }
}
