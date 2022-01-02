package com.yzx.dao;

import com.yzx.bean.OrderItem;

import java.sql.Connection;
import java.util.List;

public interface OrderItemDao {
    int addOrderItem(OrderItem orderItem);

    List<OrderItem> checkorderitem( String orderid);
}
