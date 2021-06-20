package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HomePage extends BasePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@id='freetext_search_form']//input[@class='nbf_tpl_quicksearch_searchtext']")
    private WebElement searchBox;

    @FindBy(xpath = "//div[@id='freetext_search_form']//div[@class='nbf_button']")
    private WebElement searchButton;

    @Override
    public void assertPageIsDisplayed() {
        assertThat(driver.getTitle(), is("Home Page | Mail Travel"));
        acceptCookies();
    }

    public void iSearchFor(String value) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(value);
        searchButton.click();
    }

}