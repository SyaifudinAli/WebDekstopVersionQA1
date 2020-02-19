/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.robodetpersen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author ali
 */
public class TestValueTravellPersen {

    private WebDriver driver;

    @Test
    public void testChat() throws InterruptedException, FileNotFoundException, IOException {
        System.out.println("-----------------------------TEST ROBO VALUE !!!!!!!!!!!-----------------------------");

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

        driver.findElement(By.xpath("//button[@id='userMenu']/img")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//a[contains(text(),'Tabungan Saya')])[2]")).click();
        Thread.sleep(3000);

        //click travell
        driver.findElement(By.xpath("//a[contains(text(),'1jangan Dihapus Travell')]")).click();
        Thread.sleep(3000);

        //SELECT goal
        driver.findElement(By.xpath("//button[3]")).click();
        Thread.sleep(3000);
        //SELECT investasi
        driver.findElement(By.xpath("//a[contains(text(),'Investasi')]")).click();
        Thread.sleep(3000);

        //get value goals
        WebElement goal = driver.findElement(By.xpath("//input[@id='price']"));
        String goalVal = goal.getAttribute("value");
        System.out.println("goalVal: " + goalVal);

        //close poup
        driver.findElement(By.xpath("//div/div/div/div/div/button/span")).click();
        Thread.sleep(3000);

        //get value imbal saat ini
        WebElement tabungan = driver.findElement(By.xpath("//div[2]/div[2]/p"));
        System.out.println(tabungan.getText());

        //get value pencapaian
        WebElement persen = driver.findElement(By.xpath("//div[3]/div[2]/p"));
        System.out.println(persen.getText());

        //get value periode 
        WebElement periode = driver.findElement(By.xpath("//div[4]/div[2]/p"));
        System.out.println(periode.getText());

        //get value imbal
        WebElement imbal = driver.findElement(By.xpath("//div[5]/div[2]/p"));
        System.out.println(imbal.getText());

        String val5 = String.valueOf(persen.getText());
        String val6 = val5.replaceAll("\\,", ".").replaceAll("%", "");

        String val = String.valueOf(tabungan.getText());
        String val2 = val.replaceAll(",00", "").replaceAll("\\.", "");

        String val4 = goalVal.replaceAll("\\.", "");

        double a = Double.valueOf(val4);
        double b = Double.valueOf(val2);
        double c = Double.valueOf(val6);

        double hasil = (b / a) * 100;
        System.out.printf("%.2f", hasil);
        System.out.println(" %");

        double out = Math.round(hasil * 100.0) / 100.0;
        System.out.println("hasil: " + Math.round(hasil * 100.0) / 100.0);

        if (out == c) {
            System.out.println("true");
            System.out.println(out + " %");
        } else {
            System.out.println("false");
            driver.findElement(By.xpath("//sengaja salah')]")).click();
        }

        System.out.println("-----------------------------end of TEST ROBO VALUE!!!!!!!!!!!-----------------------------");

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
        driver.quit();
    }

}
