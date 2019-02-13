package com.aqacourses.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Marina on 14.02.2019.
 */
public class DefinePriceDifferenceTest {

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

        System.out.println("DefinePriceDifferenceTest: ");
    }


    /**
     * Open site with products and define the price difference
     */
    @Test
    public void definePriceDifferenceTest() {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Open https://supsystic.com/example/comparison-example/
        driver.get("https://supsystic.com/example/comparison-example/");

        //   find discountPrice for Samsung Galaxy S6
        WebElement discountPrice = driver.findElement(By.xpath("//span[contains(text(), \"Samsung Galaxy S6\")]" +
                "/ancestor::div[@class=\"ptsColDesc\"]/following-sibling::div[@class=\"ptsRows ui-sortable\"]" +
                "/descendant::div[@class=\"ptsCell\"]/div[@class=\"ptsEl\"]/p/span[contains(text(), \"$\")]"));

        //   find price for Samsung Galaxy S6
        WebElement price = driver.findElement(By.xpath("//span[contains(text(), \"Samsung Galaxy S6\")]/" +
                "ancestor::div[@class=\"ptsColDesc\"]/following-sibling::div[@class=\"ptsColFooter\"]/" +
                "div[@class=\"ptsEl\"]/p/span[contains(text(), \"$\")]"));

        // move to element discountPrice
        Actions action = new Actions(driver);
        action.moveToElement(discountPrice).build().perform();

        // wait when element discountPrice will be visible
        wait.until(ExpectedConditions.visibilityOf(discountPrice));

        //  get discountPrice for telephone
        String priceDiscountSum = discountPrice.getText().substring(1);

        System.out.println("discountPrice for Galaxy S6: " + price.getText());

        float convertDiscountPrice = Float.parseFloat(priceDiscountSum);

        String telephonePrice = price.getText().substring(1);

        float convertPrice = Float.parseFloat(telephonePrice);

        System.out.println("Sale for Galaxy S6: " + price.getText());

        float differencePrice = convertPrice - convertDiscountPrice;

        System.out.println("Difference in discountPrice: " + differencePrice);

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
