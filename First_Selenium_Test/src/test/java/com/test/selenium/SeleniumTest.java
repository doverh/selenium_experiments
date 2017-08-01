package com.test.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
    private WebDriver driver;
    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\dovico\\Documents\\jars\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void succeeded() {
        //Step 2- Navigation: Open a website
        driver.get("https://www.teknosa.com/");

        //Step 3- Assertion: Check its title is correct
        //assertEquals method Parameters: Message, Expected Value, Actual Value
        Assert.assertEquals("Title check failed!", "Teknosa Alışveriş Sitesi - Herkes İçin Teknoloji", driver.getTitle());

    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
