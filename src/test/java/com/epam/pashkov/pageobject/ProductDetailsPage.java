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
    public static final String KEYS_FIELDS = "//div[@class='panel corner item-stats']/div[@class='row']/span[1]";
    public static final String VALUES_FIELDS = "//div[@class='panel corner item-stats']/div[@class='row']/span[2]";
    public static final String BREADCRUMBS_TO_LIST_PAGE = "//div[@id='page-breadcrumbs']/a[3]";

    @FindBy(xpath = KEYS_FIELDS)
    private List<WebElement> keysOfDescriptionFields;

	@FindBy(xpath = VALUES_FIELDS)
	private List<WebElement> valuesOfDescriptionFields;

	@FindBy(xpath = BREADCRUMBS_TO_LIST_PAGE)
	private WebElement breadcrumbsToListPage;

    public ProductDetailsPage(Setup setup) {
        super(setup);
    }

    public Map<String,String> descriptionOfProduct(){
        Map<String, String> description = new HashMap<String,String>();
        wait.waitVisibilityOfAll(setup, keysOfDescriptionFields);
        String str1 = keysOfDescriptionFields.get(0).getText();
        wait.waitVisibilityOfAll(setup, valuesOfDescriptionFields);
        String str2 = valuesOfDescriptionFields.get(0).getText();
        for(int i = 0; i<keysOfDescriptionFields.size(); i++){
            description.put(keysOfDescriptionFields.get(i).getText(), valuesOfDescriptionFields.get(i).getText());
        }
        return description;
    }

    public ListPage goToListPage(){
        breadcrumbsToListPage.click();
        return new ListPage(setup);
    }

    public String configureDescriptionStringForConditioner(){
        StringBuilder sb = new StringBuilder();
        sb.append(valuesOfDescriptionFields.get(0).getText());
        sb.append(" установка; ");
        sb.append(valuesOfDescriptionFields.get(1).getText());
        sb.append("; ");
        sb.append("мощность охлаждения ");
        sb.append(valuesOfDescriptionFields.get(2).getText());
        sb.append("; ");
        sb.append("мощность обогрева ");
        sb.append(valuesOfDescriptionFields.get(3).getText());
        return sb.toString().replace("  ", " ").trim();
    }
}
