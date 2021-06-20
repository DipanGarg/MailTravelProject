package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TourSearchPage extends BasePage {

    private final WebDriver driver;

    public TourSearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "iterator_1_product_custom_more-info-button")
    WebElement moreInfo;

    public void assertPageIsDisplayed() {
        assertThat(driver.getTitle(), is("Tour Search | Mail Travel"));
    }

    public void iClickOnMoreInfo() {
        assertPageIsDisplayed();
        waitForElementToBeClickable(moreInfo).click();
    }
}
