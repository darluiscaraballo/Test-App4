package com.demo.blaze.hook;

import com.demo.blaze.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUpDriver(){
        WebDriverManager.setUpDriver();
    }

    @After
    public void tearsDown(){
        WebDriverManager.getDriver().quit();
    }
}
