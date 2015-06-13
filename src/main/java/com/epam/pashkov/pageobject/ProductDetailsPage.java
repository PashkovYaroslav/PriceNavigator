package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaroslav on 06.06.2015.
 */
public class ProductDetailsPage extends AbstractPage {
    private final String KEYS_FIELDS = "//div[@class='panel corner item-stats']/div[@class='row']/span[1]";
    private final String VALUES_FIELDS = "//div[@class='panel corner item-stats']/div[@class='row']/span[2]";
    private final String BREADCRUMBS_TO_LIST_PAGE = "//div[@id='page-breadcrumbs']/a[3]";

    @FindBy(xpath = KEYS_FIELDS)
    private List<WebElement> keysOfDescriptionFields;

	@FindBy(xpath = VALUES_FIELDS)
	private List<WebElement> valuesOfDescriptionFields;

	@FindBy(xpath = BREADCRUMBS_TO_LIST_PAGE)
	private WebElement breadcrumbsToListPage;

    public List<WebElement> getKeysOfDescriptionFields() {
        return keysOfDescriptionFields;
    }

    public List<WebElement> getValuesOfDescriptionFields() {
        return valuesOfDescriptionFields;
    }

    public WebElement getBreadcrumbsToListPage() {
        return breadcrumbsToListPage;
    }

    public ProductDetailsPage(Setup setup) {
        super(setup);
    }

    public ListPage goToListPage(){
        breadcrumbsToListPage.click();
        return new ListPage(setup);
    }
}
