/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.robodet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author ali
 */
public class TestRetirementDetailGoal {

    private WebDriver driver;

    @Test
    public void testRetirement() throws InterruptedException, FileNotFoundException, IOException {

        System.out.println("-----------------------------TEST ROBO RETIREMENT DETAIL GAOL!!!!!!!!!!!-----------------------------");
        driver.get(readStream().getProperty("url"));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Thread.sleep(15000);
        driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
        Thread.sleep(3000);

        driver.findElement(By.id("login-email")).sendKeys(readStream().getProperty("login.email"));
        driver.findElement(By.id("login-password")).sendKeys(readStream().getProperty("login.password"));
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Keluar'])[1]/following::img[3]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[3]/div/button/img")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@id='userMenu']/img")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//a[contains(text(),'Tabungan Saya')])[2]")).click();
        Thread.sleep(3000);
        
//        driver.findElement(By.xpath("//button[@id='userMenu']/img")).click();
//        Thread.sleep(3000);
//
//        driver.findElement(By.xpath("(//a[contains(text(),'Tabungan Saya')])[2]")).click();
//        Thread.sleep(3000);

        //click RETIREMENT
        driver.findElement(By.xpath("//a[contains(text(),'Jangan Dihapus Pensiun Dini')]")).click();
        Thread.sleep(3000);

        //SELECT goal
        driver.findElement(By.xpath("//button[3]")).click();
        Thread.sleep(3000);
//        //SELECT investasi
//        driver.findElement(By.xpath("//a[contains(text(),'Investasi')]")).click();
//        Thread.sleep(3000);
        //close poup
        driver.findElement(By.xpath("//div/div/div/div/div/button/span")).click();
        Thread.sleep(3000);

        try {
            long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

            while (true) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(2000);

                long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);

        System.out.println("haaaaaa");
        System.out.println("-----------------------------end of TEST ROBO RETIREMENT DETAIL GOAL!!!!!!!!!!!-----------------------------");

    }

    public Properties readStream() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/application-test.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.gecko.driver", readStream().getProperty("driver.gecko"));
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void afterTest() {
//        driver.quit();
    }

}