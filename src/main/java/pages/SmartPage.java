package pages;

/**
 * Created by alex00x6 on 11.08.17.
 */
public class SmartPage {
    public static final String topSalesContainer = "//*[@class=\"g-i-tile g-i-tile-catalog\" and descendant::*[@class=\"g-tag g-tag-icon-middle-popularity sprite\"]]";
    public static final String nameInContainer = ".//*[@class=\"g-i-tile-i-title clearfix\"]//*[text()]";
    public static final String priceInContainer = ".//*[@class=\"g-price-uah\"]";

    public static final String secondPageButton = "//*[@id=\"page2\"]";
    public static final String thirdPageButton = "//*[@id=\"page3\"]";
}
