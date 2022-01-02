package com.yzx.service;

import com.yzx.bean.Cart;
import com.yzx.bean.Order;
import com.yzx.bean.OrderItem;

import java.sql.Connection;
import java.util.List;

public interface OrderService {
    Order addOrder( Cart cart, Integer Userid);

    List<Order> checAllOrder(Integer userId);

    List<OrderItem> checkorderitem( String orderid);

    List<Order> showalluserorder();

    void sendOrderitem( String orderid);

    void completeorder( String orderid);
}
