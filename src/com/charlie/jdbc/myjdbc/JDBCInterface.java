package com.charlie.jdbc.myjdbc;

/**
 * define a interface
 * @author AC
 * @version 1.0
 */
public interface JDBCInterface {
    //connect the database
    public Object getConnection();

    //crud
    public void crud();

    //close the connection
    public void close();
}
