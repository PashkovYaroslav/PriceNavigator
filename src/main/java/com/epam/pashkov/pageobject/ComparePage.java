package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import com.epam.pashkov.helpers.HelperUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaroslav on 01.06.2015.
 */
public class ComparePage extends AbstractPage {
    private final String DESCRIPTION_FIELDS = "//table[@class='compare']//tr/td[not(@colspan)][1]";
    private final String PRODUCT_DESCRIPTION = "//table[@class='compare']//tr/td[%s]";
    private final String DESCRIPTION_LINE = "(//table[@class='compare']//tr/td[not(@colspan)]/..)[%s]";
    private final String DIFFERENT_CLASS = "different";

    @FindBy(xpath = DESCRIPTION_FIELDS)
    private List<WebElement> descriptionFields;

    public List<WebElement> getDescriptionFields() {
        return descriptionFields;
    }

    public String getDescriptionLine() {
        return DESCRIPTION_LINE;
    }

    public String getDifferentClass() {
        return DIFFERENT_CLASS;
    }

    public List<WebElement> getProdDescription(String numberOfProduct){
        return setup.getDriver().findElements(By.xpath(String.format(PRODUCT_DESCRIPTION,numberOfProduct)));
    }

    public ComparePage(Setup setup) {
        super(setup);
    }
}
