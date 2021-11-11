package com.charlie.jdbc.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author AC
 * @version 1.0
 */
public class C3P0_Demo {

    @Test
    public void testC3P0_01() throws IOException, PropertyVetoException, SQLException {
        //1.create data source object
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        //get info from mysql.properties
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        //2.set relate parameters for comboPooledDataSource
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        //initialize connection numbers
        comboPooledDataSource.setInitialPoolSize(16);
        //max numbers of connection
        comboPooledDataSource.setMaxPoolSize(64);

        //getConnection() method is implements from interface DataSource
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            //System.out.println("ok");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("combo pool connection cost time = " + (end-start));
    }

    /**
     * use the config file template
     */
    @Test
    public void testC3P0_02() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("charlie");
        Connection connection = comboPooledDataSource.getConnection();
        System.out.println("ok");
        connection.close();
    }
}
