package com.demo.blaze.logic;

public class Page {
    public static BlazeProductListLogic demoBlaze(){
        return new BlazeProductListLogic();
    }
    public void staticWait(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}