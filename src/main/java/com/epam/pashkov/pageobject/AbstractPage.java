package com.epam.pashkov.pageobject;

import com.epam.pashkov.Setup;
import com.epam.pashkov.helpers.WaiterHelper;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Yaroslav on 31.05.2015.
 */
public abstract class AbstractPage {
    protected Setup setup;
    protected WaiterHelper wait = new WaiterHelper();

    public AbstractPage(Setup setup) {
        this.setup = setup;
        PageFactory.initElements(setup.getDriver(), this);
    }
}
