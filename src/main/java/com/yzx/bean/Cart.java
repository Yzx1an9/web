package com.yzx.bean;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Integer Itemscount = 0;

    private BigDecimal totalmoney = new BigDecimal(0);
    private Map<Integer,Item> Items = new LinkedHashMap<>();

    public Cart() {
    }

    public Integer getItemscount() {
        return Itemscount;
    }

    public BigDecimal getTotalmoney() {
        return totalmoney;
    }

    @Override
    public String toString() {
        return "cart{" +
                "Itemscount=" + Itemscount +
                ", totalmoney=" + totalmoney +
                ", Items=" + Items +
                '}';
    }


    public void addItem(Item Item){
        Integer id = Item.getId();
        if(Items.containsKey(id)){
            Item Item1 = Items.get(id);
            Item1.setCount(Item1.getCount() + 1);
            Item1.setTotalprice(Item1.getTotalprice().add(Item1.getPrice()));
            Items.put(id,Item1);
        }else{
            Items.put(id,Item);
        }

        totalmoney = totalmoney.add(Item.getPrice());
        Itemscount++;
    }

    public void deleteItem(Integer id){
        totalmoney = totalmoney.subtract(Items.get(id).getTotalprice());
        Itemscount -= Items.get(id).getCount();
        Items.remove(id);

    }

    public void clearcart(){
        Items.clear();
        totalmoney = new BigDecimal(0);
        Itemscount = 0;
    }

    public void updatecart(Integer id,Integer count){
        if(Items.containsKey(id) && count == 0){
            deleteItem(id);
            return;
        }
        if(Items.containsKey(id)){
            Item Item = Items.get(id);
            int updatevalue = count - Item.getCount();
            BigDecimal bigDecimal = new BigDecimal(updatevalue);
            BigDecimal multiply = Item.getPrice().multiply(bigDecimal);
            totalmoney = totalmoney.add(multiply);
            Itemscount += updatevalue;
            Item.setCount(count);
            Item.setTotalprice(new BigDecimal(count).multiply(Item.getPrice()));
            Items.put(id,Item);

        }
    }

//    public Integer getItemscount() {
//        int res = 0;
//        for (Item i : Items.values()) {
//            res += i.getCount();
//        }
//        return res;
//    }
//
//    public BigDecimal getTotalmoney() {
//        BigDecimal bigDecimal = new BigDecimal(0 );
//        for (Item i : Items.values()) {
//                bigDecimal = bigDecimal.add(i.getTotalprice());
//            }
//            return bigDecimal;
//    }

    public Map<Integer, Item> getItems() {
        return Items;
    }
    
    

}
