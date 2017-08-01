package com.test.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import java.io.File;
import java.util.UUID;

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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File[] listOfFiles= folder.listFiles();

        //Make sure the directory is not empty
        assertThat(listOfFiles.length, is(not(0)));
        for(File file : folder.listFiles()){
            assertThat(file.length(), is(not((long)0)));
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
