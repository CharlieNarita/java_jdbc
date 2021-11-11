package com.charlie.jdbc.myjdbc;

/**
 * @author AC
 * @version 1.0
 */
public class MyJDBCImpl implements JDBCInterface{
    @Override
    public Object getConnection() {
        return "get the SQL database connection";
    }

    @Override
    public void crud() {
        System.out.println("operation crud of SQL database");
    }

    @Override
    public void close() {
        System.out.println("close the SQL connection");
    }
}
