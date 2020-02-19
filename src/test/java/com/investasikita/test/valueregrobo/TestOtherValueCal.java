/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.valueregrobo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author ali
 */
public class TestOtherValueCal {

    private WebDriver driver;

    @Test
    public void testOther() throws InterruptedException, FileNotFoundException, IOException {

        System.out.println("-----------------------------TEST ROBO OTHER!!!!!!!!!!!-----------------------------");
        driver.get(readStream().getProperty("url"));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Thread.sleep(15000);
        driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
        Thread.sleep(3000);

        driver.findElement(By.id("login-email")).sendKeys(readStream().getProperty("login.email"));
        driver.findElement(By.id("login-password")).sendKeys(readStream().getProperty("login.password"));
        Thread.interrupted();
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[@id='userMenu']/img")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//a[contains(text(),'Tabungan Saya')])[2]")).click();
        Thread.sleep(3000);

        //Click OTHER
        driver.findElement(By.xpath("//a[contains(text(),'Lainnya')]")).click();
        Thread.sleep(3000);

        Select dropdown = new Select(driver.findElement(By.id("individu-income")));
        List<WebElement> dd = dropdown.getOptions();
        int index = 0;
        if (!dd.isEmpty()) {
            Random rand = new Random();
            index = rand.nextInt(dd.size() - 1);
            System.out.println("ini index ke" + index);
        } else if (dd.size() < 1) {
        }
        if (index >= 0) {
            dropdown.selectByIndex(index);
        }
        Thread.sleep(3000);
        //SELECT RADIO BUTTON 
        Random rnd = new Random();
        List<WebElement> radios
                = //driver.findElements(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Profil Risiko'])[1]/following::label[1]"));
                //driver.findElements(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sangat Rendah'])[1]/following::label[1]"));
                //driver.findElements(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Rendah'])[1]/following::label[1]"));
                //driver.findElements(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sedang'])[1]/following::label[1]"));
                driver.findElements(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tinggi'])[1]/following::label[1]"));
        radios.get(rnd.nextInt(radios.size())).click();
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

        //CLICK MENU "SELENJUTNYA"
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sangat Tinggi'])[1]/following::button[2]")).click();
        Thread.sleep(3000);

        driver.findElement(By.id("invesment-goals")).sendKeys("test value");
        Thread.sleep(3000);

        driver.findElement(By.id("investor-goals")).sendKeys("DOA MINTA DUIT YANG BANYAK");
        Thread.sleep(3000);

        driver.findElement(By.id("targetAmount")).sendKeys("100000000");
        Thread.sleep(3000);

        driver.findElement(By.id("savings_period")).sendKeys("4");

        driver.findElement(By.id("frontInvestment")).sendKeys("20000000");
        Thread.sleep(2000);

        //select Hitung! !!!!!!!!!!!!!!!!
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tanggal Mulai'])[1]/following::button[1]")).click();
        Thread.sleep(1000);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        //get value goal
        WebElement goal = driver.findElement(By.xpath("//div[3]/div[2]/p"));
        System.out.println(goal.getText());

        //get value imbal
        WebElement waktu = driver.findElement(By.xpath("//div[4]/div[2]/p"));
        System.out.println(waktu.getText());

        //get value return
        WebElement imbal = driver.findElement(By.xpath("//div[2]/div[4]/div/p"));
        System.out.println(imbal.getText());

        //get value awal
        WebElement awal = driver.findElement(By.xpath("//div[@id='root']/div[2]/div[2]/section/div[6]/div[2]/p"));
        System.out.println(awal.getText());

////////////////////////////////////////////////////////////////////////////////////////////////////////
        //CHANGE TO DOUBLE GOAL
        String val1 = String.valueOf(goal.getText());
        String val2 = val1.replaceAll("Rp.", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val 2 = " + val2);
        double a = Double.valueOf(val2);
        System.out.println("Target pencapaian = " + a);

        //CHANGE TO DOUBLE WAKTU
        String val3 = String.valueOf(waktu.getText());
        String val4 = val3.replaceAll("TAHUN", "");
        System.out.println("val 4 = " + val4);
        double b = Double.valueOf(val4);
        System.out.println(" waktu  = " + b);
        
        //CHANGE TO DOUBLE RETURN
        String val5 = String.valueOf(imbal.getText());
        String val6 = val5.replaceAll("Target imbal hasil tahunan portfolio: ", "").replaceAll("\\,", ".").replaceAll("%", "");
        System.out.println("return = " + val6);
        double c = Double.valueOf(val6);
        double f = c / 100;
        System.out.println("f= "+ f);
        
        
        System.out.println("return = " + c);

        //CHANGE TO DOUBLE AWAL
        String val7 = String.valueOf(awal.getText());
        String val8 = val7.replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val 8 = " + val8);
        double d = Double.valueOf(val8);
        System.out.println("setoran awal = " + d);   
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        double nilaitetap1 = 1 ;
        double nilaitetap2 = 12 ;
        double e = b * 12;
        
        double hitung1 = (a - d);
        double hitung2 = (nilaitetap1 + c /nilaitetap2)*e;
        
        double hitung3 = (e - nilaitetap1);
        double hitung4 = ((nilaitetap1 + c)/nilaitetap2);
        
        System.out.println("-----------------------------end of TEST ROBO OTHER!!!!!!!!!!!-----------------------------");
        Thread.interrupted();
//        double dob = calculateSetoran(a, hitung4);

        // a = Target  ===> E
        // b = waktu menabung dalam satuan tahun
        
        // c = return (belum dibagi 12) ==> A 
        // d = setoran awal   ===> D
        // e = waktu menabung setelah di rubah dalam satuan bulan (b * 12) ===> C
        
        
        
        calculateSetoran(a, d, f, e, b);
        
        
        
        
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
    
    //  calculateSetoran(a, d, c, e);
    public double calculateSetoran(double target, double setoran_awal, double rtn, double n, double time) {
        
        System.out.println("target: " +target + "\n"
                            +"setoran_awal: " +setoran_awal + "\n"
                            +"return: " +rtn + "\n"
                            +"n: " +n+ "\n" 
                            +"time: " +time);
        
//        double rtns = 0.19219;
        
        int ts = (int)time;
        int tx = ts * 12;
        
        double r = rtn / 12;
        double s = 1 + r;
  
        List<Double> doubles = new ArrayList<>();
        for (int i = 1; i <= tx;i++){
            Double t = Math.pow(s, i); 
            doubles.add(t);
        }  
  
        if(doubles.size()>0)
            doubles.remove(doubles.size()- 1);
        
//            System.out.println("doubles:"+doubles);        
//            System.out.println("sum:"+summs(doubles));
        double total = 0;
        for (int j = 1; j <= tx;j++){
            Double t = Math.pow(s, j);

            Double val = target - (setoran_awal * t);
//            System.out.println("val: "+val);
            Double result = val / summs(doubles);
            
            System.out.println("result:"+result);
            total +=result;
        }
        
        System.out.println("hasil:"+total/n);
//    
//        
//        
//        
//        double t = Math.pow(s, n) ;
//        double u = setoran_awal * t;
//        double v = target - u;
//        
//        System.out.println("values: "+v);
//        
// 
//            // a = Target  ===> E
//        // b = waktu menabung dalam satuan tahun
//        
//        // c = return (belum dibagi 12) ==> A 
//        // d = setoran awal   ===> D
//        // e = waktu menabung setelah di rubah dalam satuan bulan (b * 12) ===> C
//        
//        
//        
//       
//        
        return 0;
    }
    
    public static double summs(List<Double> arrs){
        double dob = 0;
        for (Double d : arrs){
            dob += d;
        }
        return dob;
    }

}
