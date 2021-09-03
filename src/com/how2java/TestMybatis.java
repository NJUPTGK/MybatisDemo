package com.how2java;
 
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.how2java.pojo.Order;
import com.how2java.pojo.OrderItem;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
 
public class TestMybatis {
 
    public static void main(String[] args) throws IOException {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession session = sqlSessionFactory.openSession();
//
//
//        List<Category> cs = session.selectList("listCategory");
//        for (Category c : cs) {
//        	System.out.println(c);
//            List<Product> ps = c.getProducts();
//            for (Product p : ps) {
//				System.out.println("\t"+p);
//			}
//        }
//
//        session.commit();
//        session.close();
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

//        List<Product> ps = session.selectList("listProduct");
//        for (Product p : ps) {
//            System.out.println(p+" 对应的分类是 \t "+ p.getCategory());
//        }
        listOrder(session);
        session.commit();
        session.close();

    }
    private static void listOrder(SqlSession session) {
        List<Order> os = session.selectList("listOrder");
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois= o.getOrderItems();
            for (OrderItem oi : ois) {
                System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
            }
        }
    }
}