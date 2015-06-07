package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ResourceBundle;

/**
 * Created by Yaroslav on 01.06.2015.
 */
public class HomePage extends AbstractPage {
    public static final String REFRIGERATOR_CATEGORY = "//a[contains(text(),'Холодильники')]";
    public static final String MICROVAWE_CATEGORY = "//a[contains(text(),'Микроволновки')]";
    public static final String WASHING_MACHINE_CATEGORY = "//a[contains(text(),'Стиральные машины')]";
    public static final String BREAD_MACHINE_CATEGORY = "//a[contains(text(),'Хлебопечи')]";
    public static final String CONDITIONER_CATEGORY = "(//a[contains(text(),'Кондиционеры')])[1]";

    @FindBy(xpath = REFRIGERATOR_CATEGORY)
    private WebElement refrigeratorCategory;

    @FindBy(xpath = MICROVAWE_CATEGORY)
    private WebElement microvaweCategory;

    @FindBy(xpath = WASHING_MACHINE_CATEGORY)
    private WebElement washingMachineCategory;

    @FindBy(xpath = BREAD_MACHINE_CATEGORY)
    private WebElement breadMachineCategory;

    public HomePage(Setup setup) {
        super(setup);
        setup.getDriver().get(ResourceBundle.getBundle("config").getString("homepage.url"));
    }

    public ListPage goToPage(String xpathOfCategory) {
        setup.getDriver().findElement(By.xpath(xpathOfCategory)).click();
        return new ListPage(setup);
    }
}
