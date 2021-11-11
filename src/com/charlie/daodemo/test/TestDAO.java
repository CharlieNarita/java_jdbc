package com.charlie.daodemo.test;

import com.charlie.daodemo.dao.ActorDAO;
import com.charlie.daodemo.dao.GoodsDAO;
import com.charlie.daodemo.domain.Actor;
import com.charlie.daodemo.domain.Goods;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author AC
 * @version 1.0
 */
public class TestDAO {
    public static void main(String[] args) {

    }


    @Test
    public void testActorDAO() {
        ActorDAO actorDAO = new ActorDAO();

        //query
        String sql = "select * from actor";
        List<Actor> actors = actorDAO.queryMulti(sql, Actor.class);
        System.out.println("query result:");
        for (Actor actor : actors) {
            System.out.println(actor);
        }

        //query single
        String sql2 = "select * from actor where id=?";
        Actor actor = actorDAO.querySingle(sql2, Actor.class, 3);
        System.out.println("\nsingle query result: ");
        System.out.println(actor);

        //query scalar
        String sql3 = "select name from actor where id=?";
        Object o = actorDAO.queryScalar(sql3,  2);
        System.out.println("\nscalar query result: ");
        System.out.println(o);

    }

    @Test
    public void testGoodsDAO() {
        GoodsDAO goodsDAO = new GoodsDAO();

        //update
        //String sql = "insert into goods values(null, ?, ?)";
        //int row = goodsDAO.update(sql, "iphone", "800");
        //System.out.println(row>0 ? "OK" : "sql operation is not affect any row");

        //query
        String sql2 = "select * from goods where id=?";
        Goods good = goodsDAO.querySingle(sql2, Goods.class,1);
        System.out.println(good);
    }
}
