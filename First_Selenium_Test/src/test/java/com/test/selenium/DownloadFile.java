package com.test.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

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
        driver = new FirefoxDriver(profile);
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
