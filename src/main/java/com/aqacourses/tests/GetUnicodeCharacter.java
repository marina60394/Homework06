package com.aqacourses.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * Created by Marina on 14.02.2019.
 */
public class GetUnicodeCharacter {

    //  Instance of Webdriver
    private WebDriver driver;

    /**
     * Set up method
     */
    @Before
    public void setUp() {

        //  Disable infobars
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");

        //  Initialize path to chromedriver
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        // Initialize instance of ChromeDriver and add options
        driver = new ChromeDriver(options);

        // Set 10 seconds to implicitly waits
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //  Maximize window
        driver.manage().window().maximize();

        System.out.println("GetUnicodeCharacterTest: ");
    }

    /**
     * Open google, search, get search result and go to page https://wwww.qasymphony.com
     */
    @Test
    public void searchGoogleTest() {

        // Open Unicode-Table
        driver.get("https://unicode-table.com/ru/");

        //  find character Q
        WebElement elementQ = driver.findElement(By.xpath("//ul[@class=\"unicode_table u0000\"]/li[@data-code=\"81\"]"));

        System.out.println("Unicode Character Q: " + elementQ.getText());

        //  find character &
        WebElement elementAmp = driver.findElement(By.xpath("//ul[@class=\"unicode_table u0000\"]/li[@data-code=\"38\"]"));

        System.out.println("Unicode Character &: " + elementAmp.getText());

        //  find character A
        WebElement elementA = driver.findElement(By.xpath("//ul[@class=\"unicode_table u0000\"]/li[@data-code=\"65\"]"));

        System.out.println("Unicode Character A: " + elementA.getText());

    }

    /**
     * After method, quit driver
     */
    @After
    public void tearDown() {

        // close window
        driver.quit();
    }
}

