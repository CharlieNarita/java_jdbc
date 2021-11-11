package com.charlie.jdbc.myjdbc;

/**
 * @author AC
 * @version 1.0
 */
public class SimiOracleJDBC implements JDBCInterface {
    @Override
    public Object getConnection() {
        return "Oracle DataBase connection OK";
    }

    @Override
    public void crud() {
        System.out.println("Oracle DataBase CRUD operation");
    }

    @Override
    public void close() {
        System.out.println("close the Oracle DataBase connection");
    }
}
