package com.epam.pashkov.helpers;

import com.epam.pashkov.Setup;
import com.epam.pashkov.pageobject.ProductDetailsPage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yaroslav on 14.06.2015.
 */
public class ProductDetailsPageHelper {
    private ProductDetailsPage productDetailsPage;
    private WaiterHelper wait = new WaiterHelper();

    public Map<String,String> descriptionOfProduct(Setup setup){
        productDetailsPage = new ProductDetailsPage(setup);

        Map<String, String> description = new HashMap<String,String>();
        wait.waitVisibilityOfAll(setup, productDetailsPage.getKeysOfDescriptionFields());
        String str1 = productDetailsPage.getKeysOfDescriptionFields().get(0).getText();
        wait.waitVisibilityOfAll(setup, productDetailsPage.getValuesOfDescriptionFields());
        String str2 = productDetailsPage.getValuesOfDescriptionFields().get(0).getText();
        for(int i = 0; i<productDetailsPage.getKeysOfDescriptionFields().size(); i++){
            description.put(productDetailsPage.getKeysOfDescriptionFields().get(i).getText(), productDetailsPage.getValuesOfDescriptionFields().get(i).getText());
        }
        return description;
    }

    public String configureDescriptionStringForConditioner(Setup setup){
        productDetailsPage = new ProductDetailsPage(setup);

        StringBuilder sb = new StringBuilder();
        sb.append(productDetailsPage.getValuesOfDescriptionFields().get(0).getText());
        sb.append(" установка; ");
        sb.append(productDetailsPage.getValuesOfDescriptionFields().get(1).getText());
        sb.append("; ");
        sb.append("мощность охлаждения ");
        sb.append(productDetailsPage.getValuesOfDescriptionFields().get(2).getText());
        sb.append("; ");
        sb.append("мощность обогрева ");
        sb.append(productDetailsPage.getValuesOfDescriptionFields().get(3).getText());
        return sb.toString().replace("  ", " ").trim();
    }
}
