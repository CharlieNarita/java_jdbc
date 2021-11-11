package com.charlie.jdbc.apachedemo;

import com.charlie.jdbc.utils.JDBCUtilsByDruid;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author AC
 * @version 1.0
 */
public class POJOSimulation {
    public static void main(String[] args) {
        ArrayList<Actor> actors = new POJOSimulation().selectToArrayList();
        Iterator<Actor> iterator = actors.iterator();
        while (iterator.hasNext()) {
            Actor next =  iterator.next();
            System.out.println(next);
        }
    }

    /**
     * return ArrayList which contains objects by ResultSet data
     */
    @Test
    public ArrayList<Actor> selectToArrayList() {
        System.out.println("test by Druid way");
        Connection connection = null;
        String sql = "select * from actor";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //create list for storing Actor objects
        ArrayList<Actor> list = new ArrayList<>();

        try {
            connection = JDBCUtilsByDruid.getConnection();
            System.out.println(connection.getClass());
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                Date birthdate = resultSet.getDate("birthdate");
                String phone = resultSet.getString("phone");
                //add Actor objects into list by taking all resultSet
                list.add(new Actor(id, name, gender, birthdate, phone));
            }
            System.out.println("list contains: " + list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.closeResources(resultSet, preparedStatement, connection);
        }
        return list;
    }
}
