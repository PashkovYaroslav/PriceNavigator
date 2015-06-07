package com.epam.pashkov;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.ResourceBundle;

/**
 * Created by Yaroslav_Pashkov on 6/2/2015.
 */
public class BaseTest {
    protected Setup setup;
    protected WebDriver driver;
    protected ResourceBundle config;

    @BeforeTest
    public void preconditions(){
        config = ResourceBundle.getBundle("config");
        setup = new Setup(config.getString("driver"), Integer.valueOf(config.getString("driver.time_out")));
    }

    @AfterTest
    public void postconditions(){
        setup.getDriver().quit();
    }
}
