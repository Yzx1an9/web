package com.yzx.bean;

public class C implements D{
    @Override
    public void init(int x) {
        System.out.println("C中的方法");
        this.init();
    }

    public void init(){
        System.out.println("C中的无参数INIT");
    }
    
    public void method(){
       int i = 1;
   }
}
