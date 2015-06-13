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
    private final String CATEGORY = "//a[contains(text(),'%s')][1]";

    public HomePage(Setup setup) {
        super(setup);
        setup.getDriver().get(ResourceBundle.getBundle("config").getString("homepage.url"));
    }

    public ListPage goToPage(String categoryName) {
        setup.getDriver().findElement(By.xpath(String.format(CATEGORY, categoryName))).click();
        return new ListPage(setup);
    }
}
