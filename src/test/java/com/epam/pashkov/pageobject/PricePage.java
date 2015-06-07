package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Yaroslav on 06.06.2015.
 */
public class PricePage extends AbstractPage {
    public static final String SEARCH_FIELD = "//input[@id='edit-name-1']";
    public static final String DESCRIPTION_OF_FIRST_PRODUCT = "//tr[contains(@class,'price_table_tr')][1]//a[@class='description-link']";

	@FindBy(xpath = SEARCH_FIELD)
	private WebElement searchField;

	@FindBy(xpath = DESCRIPTION_OF_FIRST_PRODUCT)
	private WebElement descriptionOfFirstProduct;

    public PricePage(Setup setup) {
        super(setup);
    }

    public void performSerachFor(String productName){
        searchField.sendKeys(productName);
        searchField.submit();
    }

    public ProductDetailsPage openDescriptionOfFirstProduct(){
        descriptionOfFirstProduct.click();
        return new ProductDetailsPage(setup);
    }
}
