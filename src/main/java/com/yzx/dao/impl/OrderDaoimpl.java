package com.yzx.dao.impl;

import com.yzx.bean.Order;
import com.yzx.dao.Basedao;
import com.yzx.dao.OrderDao;

import java.sql.Connection;
import java.util.List;

public class OrderDaoimpl extends Basedao implements OrderDao {

    @Override
    public int addOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,status,user_id)values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }


    @Override
    public List<Order> checkAllOrder( Integer id) {
        String sql = "select order_id orderId,create_time createTime,price,status from t_order where user_id = ?";
        return queryForList(Order.class,sql,id);
    }

    @Override
    public List<Order> checkAllusersOrder() {
        String sql = "select order_id orderId,create_time createTime,price,status from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public void sendOrderitem( String orderid) {
        String sql = "update t_order set status = ? where order_id = ?";
        update(sql,1,orderid);
    }

    @Override
    public void completeorder( String orderid) {
        String sql = "update t_order set status = ? where order_id = ?";
        update(sql,2,orderid);
    }
}
