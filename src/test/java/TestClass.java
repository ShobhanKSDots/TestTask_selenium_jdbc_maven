import db.DB;
import db.Item;
import db.ItemDao;
import db.ItemDaoImpl;
import mail.SendMailSSL;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import pages.MainPage;
import pages.SmartPage;
import webdriver.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex00x6 on 11.08.17.
 */
public class TestClass {
    private Map<String, String> results = new HashMap<>();

    @BeforeClass
    public void beforeClass(){
        WebDriverManager.setWebDriver(WebDriverFactory.createInstance("chrome", false));
    }

    @AfterClass
    public void afterClass(){
        Wait.sleep(5000);
        if (WebDriverManager.getDriver() != null) {
            WebDriverManager.getDriver().quit();
        }
    }

    @Test
    public void test(){
        MainPage mainPage = new MainPage();
        Action action = new Action();
        mainPage.openPage();
        action.moveToElement(MainPage.menuSmartTvElectronics);
        action.commonWaitingClick(MainPage.menuSmart);
        parsePage();
        action.commonWaitingClick(SmartPage.secondPageButton);
        parsePage();
        action.commonWaitingClick(SmartPage.thirdPageButton);
        parsePage();
        printMap();
        putResultsInTable();
        SendMailSSL.send(System.getProperty("email_login"), System.getProperty("email_password"),
                System.getProperty("target_email"), "rozetka_test", getResultsFromTable());
    }

    private void parsePage(){
        Wait.sleep(3000);
        List<WebElement> elements = WebDriverManager.getDriver().findElements(By.xpath(SmartPage.topSalesContainer));
        for (int i = 0; i<elements.size(); i++){
            try {
                String name = elements.get(i).findElement(By.xpath(SmartPage.nameInContainer)).getText();
                String price = elements.get(i).findElement(By.xpath(SmartPage.priceInContainer)).getText();
                results.put(name, price);
            }
            catch (StaleElementReferenceException e){
                String name = elements.get(i).findElement(By.xpath(SmartPage.nameInContainer)).getText();
                String price = elements.get(i).findElement(By.xpath(SmartPage.priceInContainer)).getText();
                results.put(name, price);
            }
        }
    }

    private void printMap(){
        for (Map.Entry<String, String> entry : results.entrySet())
        {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    private void putResultsInTable(){
        ItemDao itemDao = new ItemDaoImpl();
        for (Map.Entry<String, String> entry : results.entrySet())
        {
            Item item = new Item();
            item.setName(entry.getKey());
            item.setPrice(entry.getValue());
            itemDao.insert(item);
        }
    }

    private String getResultsFromTable(){
        ItemDao itemDao = new ItemDaoImpl();
        List<Item> result = itemDao.findAll();
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i< result.size(); i++){
            resultString.append(" \n ").append(result.get(i).getName()).append(" = ").append(result.get(i).getPrice());
        }
        return resultString.toString();
    }
}
