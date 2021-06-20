package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public abstract class BasePage {

    private WebDriver driver;
    private static WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (wait == null)
            wait = new WebDriverWait(driver, 20);
    }


    @FindBy(xpath = "//button[@title='Accept Cookies']")
    private List<WebElement> acceptCookies;

    public abstract void assertPageIsDisplayed();

    protected void acceptCookies() {
        if (waitForAllElements(acceptCookies).size() > 0) {
            waitForElementToBeClickable(acceptCookies.get(0)).click();
        }
    }

    protected void waitFor(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeVisible(By by) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    protected List<WebElement> waitForAllElements(List<WebElement> elementList) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
    }

    protected WebElement getElement(String xpath) {
        return waitForElementToBeVisible(driver.findElement(By.xpath(xpath)));
    }

    protected WebElement getElement(By by) {
        return waitForElementToBeVisible(by);
    }

}
