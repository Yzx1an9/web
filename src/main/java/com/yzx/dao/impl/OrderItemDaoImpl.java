package com.yzx.dao.impl;

import com.yzx.bean.OrderItem;
import com.yzx.dao.Basedao;
import com.yzx.dao.OrderItemDao;

import java.sql.Connection;
import java.util.List;

public class OrderItemDaoImpl extends Basedao implements OrderItemDao {
    @Override
    public int addOrderItem(OrderItem orderItem) {
        String sql = "insert into order_item(name,count,price,total_price,order_id)values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalprice(),orderItem.getOrder_id());
    }

    @Override
    public List<OrderItem> checkorderitem( String orderid) {
        String sql = "select order_id,name,count,price,total_price totalprice from order_item where order_id = ?";
        return queryForList(OrderItem.class,sql,orderid);
    }
}
