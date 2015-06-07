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
    public static final String DESCRIPTION_FIELDS = "//table[@class='compare']//tr/td[not(@colspan)][1]";
    public static final String PRODUCT1_DESCRIPTION = "//table[@class='compare']//tr/td[2]";
    public static final String PRODUCT2_DESCRIPTION = "//table[@class='compare']//tr/td[3]";
    public static final String DESCRIPTION_LINE = "(//table[@class='compare']//tr/td[not(@colspan)]/..)[%s]";
    public static final String DIFFERENT_CLASS = "different";

    @FindBy(xpath = DESCRIPTION_FIELDS)
    private List<WebElement> descriptionFields;

    @FindBy(xpath = PRODUCT1_DESCRIPTION)
    private List<WebElement> prod1Description;

    @FindBy(xpath = PRODUCT2_DESCRIPTION)
    private List<WebElement> prod2Description;

    public ComparePage(Setup setup) {
        super(setup);
    }

    public boolean verifyCompareOfProducts(Map<String, String> prod1, Map<String, String> prod2) {
        wait.waitVisibilityOfAll(setup, prod1Description);
        wait.waitVisibilityOfAll(setup, prod2Description);
        wait.waitVisibilityOfAll(setup, descriptionFields);

        Map<String, String> prod1DescriptionMap = new HashMap<String, String>();
        Map<String, String> prod2DescriptionMap = new HashMap<String, String>();

        for (int i = 0; i < descriptionFields.size(); i++) {
            prod1DescriptionMap.put(descriptionFields.get(i).getText(), prod1Description.get(i).getText());
            prod2DescriptionMap.put(descriptionFields.get(i).getText(), prod2Description.get(i).getText());
            String currentFieldProd1;
            String currentFieldProd2;

            if (prod1.containsKey(descriptionFields.get(i).getText())) {
                String fieldOnPDP = HelperUtils.descriptionField(prod1.get(descriptionFields.get(i).getText()));
                currentFieldProd1 = HelperUtils.descriptionField(prod1DescriptionMap.get(descriptionFields.get(i).getText()));
                if (!fieldOnPDP.equals(currentFieldProd1)) {
                    return false;
                }

            }
            if (prod2.containsKey(descriptionFields.get(i).getText())) {
                String fieldOnPDP = HelperUtils.descriptionField(prod2.get(descriptionFields.get(i).getText()));
                currentFieldProd2 = HelperUtils.descriptionField(prod2DescriptionMap.get(descriptionFields.get(i).getText()));
                if (!fieldOnPDP.equals(currentFieldProd2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verifyColorOfDifferentValues() {
        wait.waitVisibilityOfAll(setup, prod1Description);
        wait.waitVisibilityOfAll(setup, prod2Description);
        wait.waitVisibilityOfAll(setup, descriptionFields);

        Map<String, String> prod1DescriptionMap = new HashMap<String, String>();
        Map<String, String> prod2DescriptionMap = new HashMap<String, String>();

        for (int i = 0; i < descriptionFields.size(); i++) {
            prod1DescriptionMap.put(descriptionFields.get(i).getText(), prod1Description.get(i).getText());
            prod2DescriptionMap.put(descriptionFields.get(i).getText(), prod2Description.get(i).getText());

            String currentFieldProd1 = HelperUtils.descriptionField(prod1DescriptionMap.get(descriptionFields.get(i).getText()));
            String currentFieldProd2 = HelperUtils.descriptionField(prod2DescriptionMap.get(descriptionFields.get(i).getText()));

            if (!currentFieldProd1.equals(currentFieldProd2)) {
                String linePosition = String.valueOf(i + 1);
                String currentTR = String.format(DESCRIPTION_LINE, linePosition);
                if (!setup.getDriver().findElement(By.xpath(currentTR)).getAttribute("class").equals(DIFFERENT_CLASS)) {
                    return false;
                }
            }
        }

        return true;
    }
}
