package com.joe.shardingjdbc;

import com.joe.shardingjdbc.bean.NewDataSource;
import com.joe.shardingjdbc.bean.Order;
import com.joe.shardingjdbc.dao.IOrderDao;
import com.joe.shardingjdbc.dao.impl.OrderDaoImpl;
import com.joe.shardingjdbc.util.DataSourceFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShardingJdbcTest {

    private static IOrderDao orderDao;

    @BeforeClass
    public static void init() throws SQLException {

        List<NewDataSource> dataSourceList = new ArrayList<>();

        //第一数据库
        NewDataSource dataSource1 = new NewDataSource();
        dataSource1.setDataIp("localhost");
        dataSource1.setDataName("ds_0");
        dataSource1.setDataPort("3306");
        dataSource1.setDataType("mysql");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");

        //第二数据库
        NewDataSource dataSource2 = new NewDataSource();
        dataSource2.setDataIp("localhost");
        dataSource2.setDataName("ds_1");
        dataSource2.setDataPort("3306");
        dataSource2.setDataType("mysql");
        dataSource2.setUsername("root");
        dataSource2.setPassword("root");

        dataSourceList.add(dataSource1);
        dataSourceList.add(dataSource2);

        DataSource dataSource = DataSourceFactory.getDataSource(dataSourceList);
        orderDao = new OrderDaoImpl(dataSource);
    }

    @Test
    public void test() {
        orderDao.createTableIfNotExists();
        orderDao.truncateTable();
        Assert.assertEquals(0, orderDao.select().size());
        Order order1 = new Order(1L, "Order 1");
        Order order2 = new Order(2L, "Order 2");
        order1.setOrderId(orderDao.insert(order1));
        order2.setOrderId(orderDao.insert(order2));
        List<Order> orders = orderDao.select();
        List<Order> expected = Arrays.asList(order1, order2);
        Assert.assertEquals(expected, orders);
        order1.setUserId(2L);
        order1.setDetails("Order 1 changed user id!");
        orderDao.update(order1);
        order2.setUserId(1L);
        order2.setDetails("Order 2 changed user id!");
        orderDao.update(order2);
        orderDao.delete(0L);
        orders = orderDao.select();
        Assert.assertEquals(expected, orders);
        orderDao.delete(order1.getOrderId());
        orders = orderDao.select();
        Assert.assertEquals(Collections.singletonList(order2), orders);
        orderDao.delete(order2.getOrderId());
        orders = orderDao.select();
        Assert.assertEquals(0, orders.size());
        orderDao.dropTable();
    }

}
