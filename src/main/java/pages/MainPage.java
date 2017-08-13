package pages;

import webdriver.WebDriverManager;

/**
 * Created by alex00x6 on 11.08.17.
 */
public class MainPage {
    public static final String menuSmartTvElectronics = "//*[@data-title=\"Смартфоны, ТВ и электроника\"]";
    public static final String menuSmart = "//*[@class=\"f-menu-sub-l-i-link novisited\" and descendant::text()=\" Смартфоны \"]";
    private static final String url = "https://rozetka.com.ua";

    public void openPage(){
        WebDriverManager.getDriver().get(url);
    }
}
