package com.epam.pashkov.helpers;

import com.epam.pashkov.Setup;
import com.epam.pashkov.pageobject.ComparePage;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaroslav on 14.06.2015.
 */
public class ComparePageHelper {
    private ComparePage comparePage;
    private WaiterHelper wait = new WaiterHelper();

    public boolean verifyCompareOfProducts(Setup setup, List<Map<String, String>> listOfComparableProducts) {
        comparePage = new ComparePage(setup);

        for (int k = 0; k < listOfComparableProducts.size(); k++) {
            Map<String, String> prodDescriptionMap = new HashMap<String, String>();

            for (int i = 0; i < comparePage.getDescriptionFields().size(); i++) {
                prodDescriptionMap.put(comparePage.getDescriptionFields().get(i).getText(), comparePage.getProdDescription(String.valueOf(k + 2)).get(i).getText());
                String currentFieldProd;

                if (listOfComparableProducts.get(k).containsKey(comparePage.getDescriptionFields().get(i).getText())) {
                    String fieldOnPDP = HelperUtils.descriptionField(listOfComparableProducts.get(k).get(comparePage.getDescriptionFields().get(i).getText()));
                    currentFieldProd = HelperUtils.descriptionField(prodDescriptionMap.get(comparePage.getDescriptionFields().get(i).getText()));
                    if (!fieldOnPDP.equals(currentFieldProd)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean verifyColorOfDifferentValues(Setup setup, List<Map<String, String>> listOfComparableProducts) {
        comparePage = new ComparePage(setup);

        for (int k = 0; k < listOfComparableProducts.size() - 1; k++) {
            Map<String, String> prodDescriptionMap = new HashMap<String, String>();
            Map<String, String> prodDescriptionMapNextProd = new HashMap<String, String>();

            for (int i = 0; i < comparePage.getDescriptionFields().size(); i++) {
                prodDescriptionMap.put(comparePage.getDescriptionFields().get(i).getText(), comparePage.getProdDescription(String.valueOf(k + 2)).get(i).getText());
                prodDescriptionMapNextProd.put(comparePage.getDescriptionFields().get(i).getText(), comparePage.getProdDescription(String.valueOf(k + 3)).get(i).getText());

                String currentFieldProd = HelperUtils.descriptionField(prodDescriptionMap.get(comparePage.getDescriptionFields().get(i).getText()));
                String currentFieldProdNext = HelperUtils.descriptionField(prodDescriptionMapNextProd.get(comparePage.getDescriptionFields().get(i).getText()));

                if (!currentFieldProd.equals(currentFieldProdNext)) {
                    String linePosition = String.valueOf(i + 1);
                    String currentTR = String.format(comparePage.getDescriptionLine(), linePosition);
                    if (!setup.getDriver().findElement(By.xpath(currentTR)).getAttribute("class").equals(comparePage.getDifferentClass())) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
