package com.charlie.jdbc.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author AC
 * @version 1.0
 */
public class DruidDemo {
    @Test
    public void testDruid() throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\druid.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);


        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Connection connection = dataSource.getConnection();
            //System.out.println("OK~");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("druid cost time=" + (end-start));
    }
}
