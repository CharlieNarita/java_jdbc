package com.charlie.jdbc.apachedemo;

import com.charlie.jdbc.utils.JDBCUtils;
import com.charlie.jdbc.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author AC
 * @version 1.0
 */
public class DBUtilsDemo {
    //use apache-dbutils class + druid to do crud operation
    @Test
    public void queryMulti() throws SQLException {
        //1.get connection
        Connection connection = JDBCUtilsByDruid.getConnection();

        //2.use DBUtils class interface, import relate jar
        //3.create QueryRunner
        QueryRunner queryRunner = new QueryRunner();

        String sql = "select * from actor";
        //4.execute relate method, return ArrayList resultSet
        //(1)query method execute sql terms and get the Result Set then packaging into ArrayList
        //(2)return the ArrayList
        /*
        parameters:
            1.connection: the connection object
            2.sql: the sql String type terms
            3.new BeanListHandler<>(Actor.class): encapsulation the resultSet->Actor objects into array list
                this step based on Reflection to get Actor class attribute
            4.query method will close resultSet and preparedStatement automatically
        */
        List<Actor> list =
                queryRunner.query(connection, sql, new BeanListHandler<>(Actor.class));
        //the query close or release the resultSet and preparedStatement!!!

        System.out.println("output the list information");
        for (Actor actor : list) {
            System.out.println(actor);
        }

        //release resource
        JDBCUtilsByDruid.closeResources(null, null, connection);
    }

    //apache-dbutils + druid return single record(single object)
    @Test
    public void querySingle() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from actor where id=?";

        //return single object, BeanHandler<>()
        Actor actor =
                queryRunner.query(connection, sql, new BeanHandler<>(Actor.class), 1);

        System.out.println("output the query object");
        System.out.println(actor);

        JDBCUtilsByDruid.closeResources(null, null, connection);
    }

    //apache+dbutils + druid return single rows single column
    @Test
    public void queryScalar() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select name from actor where id=?";

        Object obj = queryRunner.query(connection, sql, new ScalarHandler(), 2);
        System.out.println("output the query scalar");
        System.out.println(obj);

        JDBCUtilsByDruid.closeResources(null, null, connection);
    }

    //apache+dbutils + druid DML operation terms
    @Test
    public void testDML() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        //String sql = "update actor set name=? where id=?";
        String sql = "insert into actor values(null, ?,?,?,?)";

        //update the query return the row change happened
        //int affectedRow = queryRunner.update(connection, sql, "mary", 2);
        int affectedRow =
                queryRunner.update(connection, sql, "dog", "M", "1999-8-5", "136119");

        System.out.println(affectedRow > 0 ? "OK!" : "execution is not affect the table");

        JDBCUtilsByDruid.closeResources(null, null, connection);
    }
}
