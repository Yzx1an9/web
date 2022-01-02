package com.yzx.service.impl;

import com.yzx.bean.Cart;
import com.yzx.bean.Item;
import com.yzx.bean.Order;
import com.yzx.bean.OrderItem;
import com.yzx.dao.OrderDao;
import com.yzx.dao.OrderItemDao;
import com.yzx.dao.impl.OrderDaoimpl;
import com.yzx.dao.impl.OrderItemDaoImpl;
import com.yzx.service.OrderService;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    OrderDao od = new OrderDaoimpl();
    OrderItemDao oid = new OrderItemDaoImpl();
    @Override
    public Order addOrder( Cart cart, Integer Userid) {
        Map<Integer, Item> items = cart.getItems();
        String order_id = System.currentTimeMillis() + "" + Userid;
        Order order = new Order(order_id,new Timestamp(System.currentTimeMillis()),cart.getTotalmoney(),0,Userid);
        od.addOrder(order);
        for(Map.Entry<Integer,Item> entry : items.entrySet()){
            Item value = entry.getValue();
            oid.addOrderItem(new OrderItem(null,value.getName(),value.getCount(),value.getPrice(),value.getTotalprice(),order_id));
        }
        cart.clearcart();
        return order;
    }

    @Override
    public List<Order> checAllOrder( Integer userId) {
        return od.checkAllOrder(userId);
    }

    @Override
    public List<OrderItem> checkorderitem( String orderid) {
        return oid.checkorderitem(orderid);
    }

    @Override
    public List<Order> showalluserorder() {
        return od.checkAllusersOrder();
    }

    @Override
    public void sendOrderitem( String orderid) {
        od.sendOrderitem(orderid);
    }

    @Override
    public void completeorder( String orderid) {
        od.completeorder(orderid);
    }
}
