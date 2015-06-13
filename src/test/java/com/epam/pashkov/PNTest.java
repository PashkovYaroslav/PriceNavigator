package com.epam.pashkov;

import com.epam.pashkov.helpers.ComparePageHelper;
import com.epam.pashkov.helpers.ListPageHelper;
import com.epam.pashkov.helpers.ProductDetailsPageHelper;
import com.epam.pashkov.pageobject.ComparePage;
import com.epam.pashkov.pageobject.HomePage;
import com.epam.pashkov.pageobject.ListPage;
import com.epam.pashkov.pageobject.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaroslav on 24.05.2015.
 */
public class PNTest extends BaseTest {
    @Test
    public void verifySortByNameAndByPrice() {
        ListPageHelper listPageHelper = new ListPageHelper();
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage("Холодильники");
        listPage.clickOnSortByName();
        Assert.assertTrue(listPageHelper.verifySortByProductName(setup), "Verify sort of products by title.");
        listPage.clickOnSortByPrice();
        Assert.assertTrue(listPageHelper.verifySortByPrice(setup), "Verify sort of products by price.");
    }

    @Test
    public void verifyCompareOfTwoProducts() {
        ComparePageHelper comparePageHelper = new ComparePageHelper();
        ProductDetailsPageHelper productDetailsPageHelper = new ProductDetailsPageHelper();
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage("Микроволновки");
        List<Map<String, String>> listOfComparableProducts = new ArrayList<Map<String, String>>();
        ProductDetailsPage productPage = listPage.openProduct(0);
        listOfComparableProducts.add(productDetailsPageHelper.descriptionOfProduct(setup));
        productPage.goToListPage();
        productPage = listPage.openProduct(1);
        listOfComparableProducts.add(productDetailsPageHelper.descriptionOfProduct(setup));
        productPage.goToListPage();
        listPage.addToCompare(1);
        listPage.addToCompare(2);
        ComparePage comparePage = listPage.compareProducts();
        Assert.assertTrue(comparePageHelper.verifyCompareOfProducts(setup, listOfComparableProducts), "Verify that all properties of products is displayed on Compare Page.");
        Assert.assertTrue(comparePageHelper.verifyColorOfDifferentValues(setup, listOfComparableProducts), "Verify that different properties are mark by color.");
    }

    @Test
    public void verifyPriceFilter() {
        ListPageHelper listPageHelper = new ListPageHelper();
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage("Стиральные машины");
        Assert.assertTrue(listPageHelper.setAndVerifyPriceFilter(setup), "Verify price filter.");
    }

    @Test
    public void verifyBrandFilter() {
        ListPageHelper listPageHelper = new ListPageHelper();
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage("Хлебопечи");
        Assert.assertTrue(listPageHelper.setAndVerifyBrandFilter(setup, "Kenwood"), "Verify brand filter.");
    }

    @Test
    public void verifyWeightRegulatorFilter() {
        ListPageHelper listPageHelper = new ListPageHelper();
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage("Хлебопечи");
        Assert.assertTrue(listPageHelper.setAndVerifyWeightRegulatorFilter(setup), "Verify that bread machines only with \"Weight Regulator\" displaying.");
    }

    @Test
    public void verifyEqualsOfInformation() {
        ListPageHelper listPageHelper = new ListPageHelper();
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage("Кондиционеры");
        Assert.assertTrue(listPageHelper.informationEquals(setup), "Verify that description of product on product details page and catalogue is equal.");
        Assert.assertTrue(listPageHelper.informationEqualsWithPrice(setup), "Verify that description of product on product details page and price is equal.");
    }
}
