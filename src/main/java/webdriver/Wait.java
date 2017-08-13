package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by alex00x6 on 11.08.17.
 */
public class Wait {
    private static final int defaultTimeout = 20;

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void forVisibilityByXpath(String xpath) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), defaultTimeout, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void forClickabilityByXpath(String xpath) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }


    public boolean elementExistsByXpath(String xpath) {
        WebDriver driver = WebDriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        boolean exists =  !driver.findElements(By.xpath(xpath)).isEmpty();
        driver.manage().timeouts().implicitlyWait(WebDriverFactory.webDriverImplicitlyWait, TimeUnit.SECONDS);
        return exists;
    }

    public boolean elementDisplayedByXpath(String xpath) {
        WebDriver driver = WebDriverManager.getDriver();
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

}
