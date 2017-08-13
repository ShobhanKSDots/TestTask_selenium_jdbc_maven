package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by alex00x6 on 11.08.17.
 */
public class Action extends Wait {

    public void commonWaitingClick(String xpath) {
        new JSWaiter().waitUntilJSReady();
        forVisibilityByXpath(xpath);
        forClickabilityByXpath(xpath);
        retryingFindClickByXpath(xpath);
    }

    public void clickableWaitingClick(String xpath) {
        new JSWaiter().waitJQueryAngular();
        forClickabilityByXpath(xpath);
        clickByXpath(xpath);
    }

    public void clickByXpath(String xpath){
        WebDriverManager.getDriver().findElement(By.xpath(xpath)).click();
    }

    public void moveToElement(String xpath){
        Actions actions = new Actions(WebDriverManager.getDriver());
        actions.moveToElement(WebDriverManager.getDriver().findElement(By.xpath(xpath))).perform();
    }


    public boolean retryingFindClickByXpath(String xpath) {
        WebDriver driver = WebDriverManager.getDriver();
        boolean result = false;
        int attempts = 0;
        int failedAttempts = 0;
        while (!result && attempts <= 2) {
            try{
                driver.findElement(By.xpath(xpath)).click();
                result = true;
                break;
            }
            catch (WebDriverException e){
                result = false;
                failedAttempts++;
            }
            attempts++;
        }
        if (attempts!=0 || failedAttempts!=0 || !result) {
            System.out.println("Attempts: " + attempts);
            System.out.println("Failed attempts: " + failedAttempts);
            System.out.println("Result :" + result);
        }
        return result;
    }
}
