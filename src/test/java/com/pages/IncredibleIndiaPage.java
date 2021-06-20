package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;

public class IncredibleIndiaPage extends BasePage {

    private final WebDriver driver;

    public IncredibleIndiaPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@id='price-pin_days-num-01']")
    private WebElement days;

    @FindBy(xpath = "//div[@id='price-pin_cc_newmarket']//span[@class='ibecurr']")
    private WebElement price;

    @FindBy(xpath = "//div[@id='supplier-phone-cont']//a[@id='supplier-phone']")
    private WebElement phoneNumber;

    @FindBy(xpath = "//button[@name='enterbookingflow']")
    private WebElement bookOnline;

    @Override
    public void assertPageIsDisplayed() {
        assertThat(driver.getTitle(), is("Incredible India | Mail Travel"));
    }

    public void iSeeDays() {
        assertPageIsDisplayed();
        assertThat(days.getText().trim(), matchesPattern("\\d{1,2}"));
    }

    public void iSeePrice() {
        assertThat(price.getText().trim(), matchesPattern("^£\\d+(,\\d{1,3})?$"));
    }

    public void iSeeTelephoneNumber() {
        waitFor(1);
        assertThat(waitForElementToBeVisible(phoneNumber).getText().trim(), matchesPattern("0808 239 \\d{4}"));
    }

    public void iClickBookOnline() {
        bookOnline.click();
    }

}

