package com.epam.pashkov.helpers;

import com.epam.pashkov.Setup;
import com.epam.pashkov.pageobject.ListPage;
import com.epam.pashkov.pageobject.ProductDetailsPage;

/**
 * Created by Yaroslav on 13.06.2015.
 */
public class ListPageHelper {
    private ListPage listPage;
    private WaiterHelper wait = new WaiterHelper();

    public boolean verifySortByProductName(Setup setup){
        listPage = new ListPage(setup);

        for(int i = 0; i<listPage.getListOfProducts().size()-1; i++){
            String currentProdName = listPage.getListOfProducts().get(i).getText();
            String nextProdName = listPage.getListOfProducts().get(i + 1).getText();
            if(currentProdName.compareTo(nextProdName)>0){
                return false;
            }
        }
        return true;
    }

    public boolean verifySortByPrice(Setup setup){
        listPage = new ListPage(setup);

        for(int i = 0; i<listPage.getListOfPrices().size()-1; i++){
            double currentProdPrice = HelperUtils.selectPriceOnly(listPage.getListOfPrices().get(i).getText());
            double nextProdPrice = HelperUtils.selectPriceOnly(listPage.getListOfPrices().get(i + 1).getText());
            if(Double.valueOf(currentProdPrice).compareTo(nextProdPrice)>0){
                return false;
            }
        }
        return true;
    }

    public boolean setAndVerifyPriceFilter(Setup setup){
        listPage = new ListPage(setup);

        double min = Double.valueOf(listPage.getMinPriceFilter().getText());
        listPage.getMinPriceFilter().click();
        wait.waitVisibilityOf(setup, listPage.getMaxPriceFilter());
        double max = Double.valueOf(listPage.getMaxPriceFilter().getText());
        listPage.getMaxPriceFilter().click();
        for(int i = 0; i<listPage.getListOfPrices().size(); i++){
            if(HelperUtils.selectPriceOnly(listPage.getListOfPrices().get(i).getText())>=min && HelperUtils.selectPriceOnly(listPage.getListOfPrices().get(i).getText())<=max){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean setAndVerifyBrandFilter(Setup setup, String brandName){
        listPage = new ListPage(setup);

        listPage.getBrandFilter(brandName).click();
        for(int i = 0; i<listPage.getListOfProducts().size(); i++){
            if(listPage.getListOfProducts().get(i).getText().contains(listPage.getBrandFilter(brandName).getText())){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean setAndVerifyWeightRegulatorFilter(Setup setup){
        listPage = new ListPage(setup);

        listPage.getWeightRegulatorFilter().click();
        for(int i = 0; i<listPage.getListOfDescriptions().size(); i++){
            String s1 = listPage.getListOfDescriptions().get(i).getText();
            String s2 = listPage.getWeightRegulatorFilter().getText();
            if(listPage.getListOfDescriptions().get(i).getText().contains(listPage.getWeightRegulatorFilter().getText())){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean informationEquals(Setup setup){
        listPage = new ListPage(setup);
        ProductDetailsPageHelper productDetailsPageHelper = new ProductDetailsPageHelper();

        wait.waitVisibilityOfAll(setup, listPage.getListOfDescriptions());
        for(int i = 0; i<5; i++){
            String descriptionOnListPage = listPage.getListOfDescriptions().get(i).getText();
            String productName = listPage.getListOfProducts().get(i).getText();
            ProductDetailsPage pdp = listPage.openProduct(i);
            String descriptionPDP = productDetailsPageHelper.configureDescriptionStringForConditioner(setup);
            if(!descriptionOnListPage.contains(descriptionPDP)){
                return false;
            }
            pdp.goToListPage();
        }
        return true;
    }

    public boolean informationEqualsWithPrice(Setup setup){
        listPage = new ListPage(setup);
        ProductDetailsPageHelper productDetailsPageHelper = new ProductDetailsPageHelper();

        for(int i = 0; i<5; i++){
            String descriptionOnListPage = listPage.getListOfDescriptions().get(i).getText();
            ProductDetailsPage pdp = listPage.openProductInPrice(i);
            String descriptionPDP = productDetailsPageHelper.configureDescriptionStringForConditioner(setup);
            if(!descriptionOnListPage.contains(descriptionPDP)){
                return false;
            }
            pdp.goToListPage();
        }
        return true;
    }
}
