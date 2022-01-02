package com.yzx.bean;

import java.math.BigDecimal;

public class OrderItem extends Item{
    private String order_id;

    public OrderItem() {
        super();
    }

    public OrderItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalprice, String order_id) {
        super(id, name, count, price, totalprice);
        this.order_id = order_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return super.toString() + "OrderItem{" +
                "order_id='" + order_id + '\'' +
                '}';
    }
}
