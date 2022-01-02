package com.yzx.dao;

import com.yzx.bean.Order;

import java.sql.Connection;
import java.util.List;

public interface OrderDao{
    int addOrder(Order order);

    List<Order> checkAllOrder( Integer id);


    List<Order> checkAllusersOrder();

    void sendOrderitem( String orderid);

    void completeorder( String orderid);
}
