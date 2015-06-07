package com.epam.pashkov;

import com.epam.pashkov.pageobject.ComparePage;
import com.epam.pashkov.pageobject.HomePage;
import com.epam.pashkov.pageobject.ListPage;
import com.epam.pashkov.pageobject.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by Yaroslav on 24.05.2015.
 */
public class PNTest extends BaseTest {
    @Test
    public void verifySortByNameAndByPrice() {
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage(HomePage.REFRIGERATOR_CATEGORY);
        listPage.clickOnSortByName();
        Assert.assertTrue(listPage.verifySortByProductName(), "Verify sort of products by title.");
        listPage.clickOnSortByPrice();
        Assert.assertTrue(listPage.verifySortByPrice(), "Verify sort of products by price.");
    }

    @Test
    public void verifyCompare() {
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage(HomePage.MICROVAWE_CATEGORY);
        ProductDetailsPage productPage = listPage.openProduct(0);
        Map<String, String> firstProductDescription = productPage.descriptionOfProduct();
        productPage.goToListPage();
        productPage = listPage.openProduct(1);
        Map<String, String> secondProductDescription = productPage.descriptionOfProduct();
        productPage.goToListPage();
        ComparePage comparePage = listPage.addtoCompare();
        Assert.assertTrue(comparePage.verifyCompareOfProducts(firstProductDescription, secondProductDescription), "Verify that all properties of products is displayed on Compare Page.");
        Assert.assertTrue(comparePage.verifyColorOfDifferentValues(), "Verify that different properties are mark by color.");
    }

    @Test
    public void verifyPriceFilter() {
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage(HomePage.WASHING_MACHINE_CATEGORY);
        Assert.assertTrue(listPage.setAndVerifyPriceFilter(), "Verify price filter.");
    }

    @Test
    public void verifyBrandFilter() {
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage(HomePage.BREAD_MACHINE_CATEGORY);
        Assert.assertTrue(listPage.setAndVerifyBrandFilter(), "Verify brand filter.");
    }

    @Test
    public void verifyWeightRegulatorFilter() {
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage(HomePage.BREAD_MACHINE_CATEGORY);
        Assert.assertTrue(listPage.setAndVerifyWeightRegulatorFilter(), "Verify that bread machines only with \"Weight Regulator\" displaying.");
    }

    @Test
    public void verifyEqualsOfInformation() {
        HomePage homePage = new HomePage(setup);
        ListPage listPage = homePage.goToPage(HomePage.CONDITIONER_CATEGORY);
        Assert.assertTrue(listPage.informationEquals(), "Verify that description of product on product details page and catalogue is equal.");
        Assert.assertTrue(listPage.informationEqualsWithPrice(), "Verify that description of product on product details page and price is equal.");
    }
}
