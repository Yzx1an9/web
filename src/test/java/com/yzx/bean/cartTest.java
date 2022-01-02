package com.yzx.bean;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class cartTest {

    @Test
    void additem() {
        Cart cart = new Cart();
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));

        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(2,"azasf",1,new BigDecimal(111),new BigDecimal(111)));
        Map<Integer, Item> items = cart.getItems();
        System.out.println(items);
    }

    @Test
    void deleteitem() {
        Cart cart = new Cart();
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));

        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(2,"azasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.deleteItem(1);
        Map<Integer, Item> items = cart.getItems();

        System.out.println(items);
    }

    @Test
    void clearcart() {
        Cart cart = new Cart();
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));

        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(2,"azasf",1,new BigDecimal(111),new BigDecimal(111)));
        Map<Integer, Item> items = cart.getItems();
        System.out.println(items);
    }

    @Test
    void updatecart() {
        Cart cart = new Cart();
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));

        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(2,"azasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.updatecart(1,10);
        Map<Integer, Item> items = cart.getItems();
        System.out.println(items);
    }

    @Test
    void getItemscount() {
        Cart cart = new Cart();
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));

        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(2,"azasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));

        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(1,"afdasf",1,new BigDecimal(111),new BigDecimal(111)));
        cart.addItem(new Item(2,"azasf",1,new BigDecimal(111),new BigDecimal(222)));
        cart.updatecart(1, 0);
        Map<Integer, Item> items = cart.getItems();
        System.out.println(items);

        cart.addItem(new Item(3,"zzzf",1,new BigDecimal(1111),new BigDecimal(1111)));
        cart.addItem(new Item(3,"zzzf",1,new BigDecimal(1111),new BigDecimal(1111)));
        cart.addItem(new Item(3,"zzzf",1,new BigDecimal(1111),new BigDecimal(1111)));
        System.out.println(cart.getItemscount() +  " " +  cart.getTotalmoney());
        System.out.println(cart.getItemscount() +  " " +  cart.getTotalmoney());



    }

    @Test
    void getTotalmoney() {
    }

    @Test
    void getItems() {
    }
}