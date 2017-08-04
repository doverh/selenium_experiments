package com.test.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.Keys.ALT;
import static org.openqa.selenium.Keys.ENTER;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class DownloadFile {

    WebDriver driver;
    File folder;

    @Before
    public void setUp(){
        folder = new File(UUID.randomUUID().toString());
        folder.mkdir();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir",folder.getAbsolutePath());
        profile.setPreference("browser.download.folderList",2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg application/pdf, application/octetstream");
        profile.setPreference("pref.downloads.disable_button.edit_actions", true);
        profile.setPreference("pdfjs.disable",true);
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\dovico\\Documents\\jars\\geckodriver.exe");
        driver = new FirefoxDriver(profile);




    }

    @Test
    public void download(){
        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.cssSelector(".example a")).click();

        //Wait 3 seconds to download the file
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Switch to popup window
        // Now you are in the popup window, perform necessary actions here
        Alert alert =  driver.switchTo().alert();
        String alertMessage= driver.switchTo().alert().getText();

        // Displaying alert message
        System.out.println("RESULT"+alertMessage);

        File[] listOfFiles= folder.listFiles();

        //Make sure the directory is not empty
            assertThat(listOfFiles.length, is(not(0)));
        for(File file : folder.listFiles()){
            assertThat(file.length(), is(not((long)0)));
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @After
    public void tearDown() throws Exception{
        driver.quit();
        for(File file : folder.listFiles()){
            file.delete();
        }
        folder.delete();
    }
}
