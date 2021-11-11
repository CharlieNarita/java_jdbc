package com.charlie.daodemo.dao;

import com.charlie.daodemo.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * develop the basic DAO as super class of other DAO
 *
 * @author AC
 * @version 1.0
 */
public class BasicDAO<T> {  //use generic
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * @param sql        sql terms
     * @param parameters pass ? values
     * @return the row number which affect by update query action
     */
    //develop general dml method
    public int update(String sql, Object... parameters) {   //dml == update
        Connection connection = null;

        try {
            connection = JDBCUtilsByDruid.getConnection();
            return queryRunner.update(connection, sql, parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeResource(null, null, connection);
        }
    }

    /**
     * @param sql        sql terms
     * @param clazz      some class' Class object by reflection
     * @param parameters pass ? values
     * @return return the list contain objects
     */
    //return multi objects(ResultSet has multi rows)
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;

        try {
            connection = JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeResource(null, null, connection);
        }
    }

    /**
     * @param sql        sql terms
     * @param clazz      some class' Class object by reflection
     * @param parameters pass ? values
     * @return return the single Object from table
     */
    public T querySingle(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;

        try {
            connection = JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeResource(null, null, connection);
        }
    }

    /**
     *
     * @param sql   sql terms
     * @param parameters pass ? value
     * @return return Object which represent the value
     */
    //query single row and single column, return single value
    public Object queryScalar(String sql, Object...parameters) {
        Connection connection = null;

        try {
            connection = JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection, sql, new ScalarHandler<>(), parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeResource(null, null, connection);
        }
    }



}
