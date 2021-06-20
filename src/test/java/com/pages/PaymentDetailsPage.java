package com.pages;

import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PaymentDetailsPage extends BasePage {

    private final WebDriver driver;

    protected PaymentDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public void assertPageIsDisplayed() {
        assertThat(driver.getTitle(), is("Payment Details"));
    }

    public void iSeePaymentDetailsAsPerExpected(){

    }
}
