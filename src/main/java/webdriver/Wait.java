package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
}
