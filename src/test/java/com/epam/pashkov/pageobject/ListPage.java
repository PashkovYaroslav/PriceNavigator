package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import com.epam.pashkov.helpers.HelperUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Yaroslav on 01.06.2015.
 */
public class ListPage extends AbstractPage {
    public static final String SORT_BY_PRICE = "//div[@class='order']/a[1]";
    public static final String SORT_BY_NAME = "//div[@class='order']/a[2]";
    public static final String LIST_OF_PRODUCTS = "//div[@class='name']/a";
    public static final String LIST_OF_PRICES = "//div[@class='price']/strong";
    public static final String LIST_OF_ADD_TO_COMPARE_LINKS = "//span[@class='compare_add_link comparep cs']";
    public static final String LIST_OF_COMPARE_LINKS = "//a[@class='head-compare-link']";
    public static final String MIN_PRICE_FILTER = "//div[@class='panel corner criteria']/div[@class='group'][1]/div[@class='is_empty_items']/a[2]";
    public static final String MAX_PRICE_FILTER = "//div[@class='panel corner criteria']/div[@class='group'][2]/div[@class='is_empty_items']/a[2]";
    public static final String BRAND_FILTER = "//a[text()='Kenwood']";
    public static final String WEIGHT_REGULATOR_FILTER = "//a[text()='Регулировка веса']";
    public static final String LIST_OF_DESCRIPTIONS = "//div[@class='item']//div[@class='description']";
    public static final String PRICES_LINK = "//div[@class='link']/a[text()='прайсы']";

    @FindBy(xpath = SORT_BY_PRICE)
    private WebElement sortByPrice;

    @FindBy(xpath = SORT_BY_NAME)
    private WebElement sortByName;

    @FindBy(xpath = LIST_OF_PRODUCTS)
    private List<WebElement> listOfProducts;

    @FindBy(xpath = LIST_OF_PRICES)
    private List<WebElement> listOfPrices;

    @FindBy(xpath = LIST_OF_ADD_TO_COMPARE_LINKS)
    private List<WebElement> listOfAddToCompareLinks;

    @FindBy(xpath = LIST_OF_COMPARE_LINKS)
    private List<WebElement> listOfCompareLinks;

	@FindBy(xpath = MIN_PRICE_FILTER)
	private WebElement minPriceFilter;

    @FindBy(xpath = MAX_PRICE_FILTER)
    private WebElement maxPriceFilter;

	@FindBy(xpath = BRAND_FILTER)
	private WebElement brandFilter;

    @FindBy(xpath = WEIGHT_REGULATOR_FILTER)
    private WebElement weightRegulatorFilter;

    @FindBy(xpath = LIST_OF_DESCRIPTIONS)
    private List<WebElement> listOfDescriptions;

	@FindBy(xpath = PRICES_LINK)
	private WebElement pricesLink;


    public ListPage(Setup setup) {
        super(setup);
    }

    public ListPage clickOnSortByPrice(){
        sortByPrice.click();
        return this;
    }

    public ListPage clickOnSortByName(){
        sortByName.click();
        return this;
    }

    public boolean verifySortByProductName(){
        for(int i = 0; i<listOfProducts.size()-1; i++){
            String currentProdName = listOfProducts.get(i).getText();
            String nextProdName = listOfProducts.get(i+1).getText();
            if(currentProdName.compareTo(nextProdName)>0){
                return false;
            }
        }
        return true;
    }

    public boolean verifySortByPrice(){
        for(int i = 0; i<listOfPrices.size()-1; i++){
            double currentProdPrice = HelperUtils.selectPriceOnly(listOfPrices.get(i).getText());
            double nextProdPrice = HelperUtils.selectPriceOnly(listOfPrices.get(i + 1).getText());
            if(Double.valueOf(currentProdPrice).compareTo(nextProdPrice)>0){
                return false;
            }
        }
        return true;
    }

    public ComparePage addtoCompare(){
        wait.waitVisibilityOfAll(setup, listOfAddToCompareLinks);
        listOfAddToCompareLinks.get(0).click();
        listOfAddToCompareLinks.get(1).click();
        wait.waitVisibilityOf(setup, listOfCompareLinks.get(0));
        listOfCompareLinks.get(0).click();
        return new ComparePage(setup);
    }

    public ProductDetailsPage openProduct(int index){
        listOfProducts.get(index).click();
        return new ProductDetailsPage(setup);
    }

    public PricePage openPrice(){
        pricesLink.click();
        return new PricePage(setup);
    }

    public ProductDetailsPage openProductInPrice(int index){
        String productName = HelperUtils.nameOfProduct(listOfProducts.get(index).getText());
        PricePage pricePage = openPrice();
        pricePage.performSerachFor(productName);
        return pricePage.openDescriptionOfFirstProduct();
    }

    public boolean setAndVerifyPriceFilter(){
        double min = Double.valueOf(minPriceFilter.getText());
        minPriceFilter.click();
        wait.waitVisibilityOf(setup, maxPriceFilter);
        double max = Double.valueOf(maxPriceFilter.getText());
        maxPriceFilter.click();
        for(int i = 0; i<listOfPrices.size(); i++){
            if(HelperUtils.selectPriceOnly(listOfPrices.get(i).getText())>=min && HelperUtils.selectPriceOnly(listOfPrices.get(i).getText())<=max){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean setAndVerifyBrandFilter(){
        brandFilter.click();
        for(int i = 0; i<listOfProducts.size(); i++){
            if(listOfProducts.get(i).getAttribute("innerHTML").contains(brandFilter.getAttribute("innerHTML"))){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean setAndVerifyWeightRegulatorFilter(){
        weightRegulatorFilter.click();
        for(int i = 0; i<listOfDescriptions.size(); i++){
            String s1 = listOfDescriptions.get(i).getAttribute("innerHTML");
            String s2 = weightRegulatorFilter.getAttribute("innerHTML");
            if(listOfDescriptions.get(i).getAttribute("innerHTML").contains(weightRegulatorFilter.getAttribute("innerHTML"))){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean informationEquals(){
        wait.waitVisibilityOfAll(setup, listOfDescriptions);
        for(int i = 0; i<5; i++){
            String descriptionOnListPage = listOfDescriptions.get(i).getAttribute("innerHTML");
            String productName = listOfProducts.get(i).getAttribute("innerHTML");
            ProductDetailsPage pdp = openProduct(i);
            String descriptionPDP = pdp.configureDescriptionStringForConditioner();
            if(!descriptionOnListPage.contains(descriptionPDP)){
                return false;
            }
            pdp.goToListPage();
        }
        return true;
    }

    public boolean informationEqualsWithPrice(){
        for(int i = 0; i<5; i++){
            String descriptionOnListPage = listOfDescriptions.get(i).getText();
            ProductDetailsPage pdp = openProductInPrice(i);
            String descriptionPDP = pdp.configureDescriptionStringForConditioner();
            if(!descriptionOnListPage.contains(descriptionPDP)){
                return false;
            }
            pdp.goToListPage();
        }
        return true;
    }
}
