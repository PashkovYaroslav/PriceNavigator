package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import com.epam.pashkov.helpers.HelperUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Yaroslav on 01.06.2015.
 */
public class ListPage extends AbstractPage {
    private final String SORT_BY_PRICE = "//div[@class='order']/a[1]";
    private final String SORT_BY_NAME = "//div[@class='order']/a[2]";
    private final String LIST_OF_PRODUCTS = "//div[@class='name']/a";
    private final String LIST_OF_PRICES = "//div[@class='price']/strong";
    private final String LIST_OF_ADD_TO_COMPARE_LINKS = "//span[@class='compare_add_link comparep cs']";
    private final String LIST_OF_COMPARE_LINKS = "//a[@class='head-compare-link']";
    private final String MIN_PRICE_FILTER = "//div[@class='panel corner criteria']/div[@class='group'][1]/div[@class='is_empty_items']/a[2]";
    private final String MAX_PRICE_FILTER = "//div[@class='panel corner criteria']/div[@class='group'][2]/div[@class='is_empty_items']/a[2]";
    private final String BRAND_FILTER = "//a[text()='%s']";
    private final String WEIGHT_REGULATOR_FILTER = "//a[text()='Регулировка веса']";
    private final String LIST_OF_DESCRIPTIONS = "//div[@class='item']//div[@class='description']";
    private final String PRICES_LINK = "//div[@class='link']/a[text()='прайсы']";

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

    @FindBy(xpath = WEIGHT_REGULATOR_FILTER)
    private WebElement weightRegulatorFilter;

    @FindBy(xpath = LIST_OF_DESCRIPTIONS)
    private List<WebElement> listOfDescriptions;

	@FindBy(xpath = PRICES_LINK)
	private WebElement pricesLink;

    public WebElement getSortByPrice() {
        return sortByPrice;
    }

    public WebElement getSortByName() {
        return sortByName;
    }

    public List<WebElement> getListOfProducts() {
        return listOfProducts;
    }

    public List<WebElement> getListOfPrices() {
        return listOfPrices;
    }

    public List<WebElement> getListOfAddToCompareLinks() {
        return listOfAddToCompareLinks;
    }

    public List<WebElement> getListOfCompareLinks() {
        return listOfCompareLinks;
    }

    public WebElement getMinPriceFilter() {
        return minPriceFilter;
    }

    public WebElement getMaxPriceFilter() {
        return maxPriceFilter;
    }

    public WebElement getBrandFilter(String brandName) {
        return setup.getDriver().findElement(By.xpath(String.format(BRAND_FILTER,brandName)));
    }

    public WebElement getWeightRegulatorFilter() {
        return weightRegulatorFilter;
    }

    public List<WebElement> getListOfDescriptions() {
        return listOfDescriptions;
    }

    public WebElement getPricesLink() {
        return pricesLink;
    }

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

    public ComparePage compareProducts(){
        wait.waitVisibilityOf(setup, listOfCompareLinks.get(0));
        listOfCompareLinks.get(0).click();
        return new ComparePage(setup);
    }

    public void addToCompare(int numberOfProduct){
        wait.waitVisibilityOfAll(setup, listOfAddToCompareLinks);
        listOfAddToCompareLinks.get(numberOfProduct-1).click();
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
}
