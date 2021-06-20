package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class BookingPage extends BasePage {

    private final WebDriver driver;

    public BookingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='nbf_tpl_pms_calendar']//div[@class='nbf_tpl_pms_calendar_days']//div[contains(@class,'calendar_day_available')][child::div[@class='nbf_tpl_pms_calendar_box_dom']]")
    private WebElement firstAvailableDateNode;

    @FindBy(xpath = "//div[@id='paxDepDateInfo']//button[@class='nbf_button nbf_tpl_pms_book_button']")
    private WebElement continueButton;

    @FindBy(xpath = "//div[@id='room-0']//select")
    private WebElement standardRoomDropdown;

    @FindBy(xpath = "//button[descendant::div[contains(text(),'Select your room')]]")
    private WebElement selectYourRoomAndContinueButton;

    @FindBy(xpath = "//button[descendant::div[contains(text(),'Continue without extras')]]")
    private WebElement continueWithoutExtrasButton;

    private String sectionTitle = "//h2[contains(text(), '%s')]";

    private String titleSelect = "//select[@id='pax-a-title-%s']";

    private String optionSelect = "//select[@id='pax-a-title-%s']/option[text()='%s']";

    private String firstNameField = "//td[preceding-sibling::th/label[text()='First Name(s)']]/input[@id='pax-a-first-%s']";

    private String lastNameField = "//td[preceding-sibling::th/label[text()='Last Name']]/input[@id='pax-a-last-%s']";

    private String dayField = "//td[preceding-sibling::th/label[text()='Date of Birth']]/select[@id='pax-a-dobd-%s']";

    private String monthField = "//td[preceding-sibling::th/label[text()='Date of Birth']]/select[@id='pax-a-dobm-%s']";

    private String yearField = "//td[preceding-sibling::th/label[text()='Date of Birth']]/select[@id='pax-a-doby-%s']";

    private String haveMyOwnInsuranceRadio = "//input[@id='pax-a-insurance-%s-own']";

    @FindBy(xpath = "//td[preceding-sibling::th/label[text()='Your name']]/input[@id='contact-name']")
    private WebElement leadContactName;

    @FindBy(xpath = "//td[preceding-sibling::th/label[text()='Mobile Phone Number']]/input[@id='contact-mobile']")
    private WebElement leadContactMobileNumber;

    @FindBy(xpath = "//td[preceding-sibling::th/label[text()='Email Address']]/input[@id='contact-email']")
    private WebElement leadContactEmail;

    @FindBy(xpath = "//td[preceding-sibling::th/label[text()='Address Line 1']]/input[@id='contact-address1']")
    private WebElement leadContactAddress1;

    @FindBy(xpath = "//td[preceding-sibling::th/label[text()='Address Line 2']]/input[@id='contact-address2']")
    private WebElement leadContactAddress2;

    @FindBy(xpath = "//td[preceding-sibling::th/label[text()='City']]/input[@id='contact-city']")
    private WebElement leadContactCity;

    @FindBy(xpath = "//td[preceding-sibling::th/label[text()='Postcode']]/input[@id='contact-postcode']")
    private WebElement leadContactPostcode;

    @FindBy(xpath = "//td[preceding-sibling::th/label[text()='Country']]/select[@id='contact-country']")
    private WebElement leadContactCountry;

    @FindBy(xpath = "//td[preceding-sibling::th/label[text()='Please select:']]/select[@id='contact-hearabout']")
    private WebElement leadContactHearAboutUs;

    @FindBy(xpath = "//div[@id='paxform-select']/button")
    private WebElement leadContactContinueButton;

    @FindBy(xpath = "//div[preceding-sibling::div[@id='totalprice-title']]//span[@data-amt]")
    private WebElement subtotal;


    @Override
    public void assertPageIsDisplayed() {
        assertThat(driver.getTitle(), is("Now Booking Incredible India | Mail Travel"));
    }

    /**
     * This method first looks for the parent node for the first available date in calendar
     * by traversing through html nodes where date node has 'available' keyword in the class name
     * Then in that node it looks for any child node if it has 'select' keyword in the class name
     * If it is true, it will return 1 and we are validating that count
     */
    public void iSeeFirstAvailableDateSelected() {
        waitFor(1);
        assertThat(firstAvailableDateNode.findElements(By.xpath(".//div")).stream().map(s -> s.getAttribute("class")).filter(s -> s.contains("select")).count(), is(1L));
        iContinue();
    }

    private void iContinue() {
        continueButton.click();
    }

    public void iSelectStandardRoom() {
        Select select = new Select(waitForElementToBeVisible(standardRoomDropdown));
        select.selectByValue("1");
    }

    public void iClickSelectYourRoomAndContinueButton() {
        selectYourRoomAndContinueButton.click();
    }

    public void iSeeSection(String section) {
        assertThat(getElement(String.format(sectionTitle, section)).getText(), containsString(section));
    }

    public void iClickContinueWithoutExtraButton() {
        waitForElementToBeVisible(continueWithoutExtrasButton).click();
    }

    public void iSelectTitle(String title, int passengerNumber) {
        String titleToBeSelected = String.format(titleSelect, passengerNumber);
    //    String optionToBeSelected = String.format(optionSelect, passengerNumber, title);

   //    Actions action = new Actions(driver);

   //     action.clickAndHold(getElement(titleToBeSelected)).build().perform();
       new Select(getElement(By.xpath(titleToBeSelected))).selectByValue(title);
       // getElement(By.xpath(titleToBeSelected)).click();
    //    waitFor(5);
    }

    public void iSetFirstName(String firstName, int passengerNumber) {
        getElement(By.xpath(String.format(firstNameField, passengerNumber))).sendKeys(firstName);
    }

    public void iSetLastName(String lastName, int passengerNumber) {
        getElement(By.xpath(String.format(lastNameField, passengerNumber))).sendKeys(lastName);
    }

    public void iSetDateOfBirth(String dob, int passengerNumber) {
        String[] dobArr = dob.split("-");
        iSelectDay(dobArr[0], passengerNumber);
        iSelectMonth(dobArr[1], passengerNumber);
        iSelectYear(dobArr[2], passengerNumber);
    }

    private void iSelectDay(String day, int passengerNumber) {
        String selectDropdown = String.format(dayField, passengerNumber);
        new Select(getElement(selectDropdown)).selectByVisibleText(day);
    }

    private void iSelectMonth(String month, int passengerNumber) {
        String selectDropdown = String.format(monthField, passengerNumber);
        new Select(getElement(selectDropdown)).selectByVisibleText(month);
    }

    private void iSelectYear(String year, int passengerNumber) {
        String selectDropdown = String.format(yearField, passengerNumber);
        new Select(getElement(selectDropdown)).selectByVisibleText(year);
    }

    public void iSelectIHaveMyOwnInsuranceRadioButton(int passengerNumber) {
        String radioButton = String.format(haveMyOwnInsuranceRadio, passengerNumber);
        if (!getElement(radioButton).isSelected()) {
            getElement(radioButton).click();
        }
    }

    public void iSetLeadContactFirstName(String firstName) {
        waitForElementToBeVisible(leadContactName).clear();
        waitForElementToBeVisible(leadContactName).sendKeys(firstName);
    }

    public void iSetLeadContactMobilePhoneNumber(String phoneNumber) {
        waitForElementToBeVisible(leadContactMobileNumber).sendKeys(phoneNumber);
    }

    public void iSetLeadContactEmail(String email) {
        waitForElementToBeVisible(leadContactEmail).sendKeys(email);
    }

    public void iSetLeadContactAddressLine1(String addressLine1) {
        waitForElementToBeVisible(leadContactAddress1).sendKeys(addressLine1);
    }

    public void iSetLeadContactAddressLine2(String addressLine2) {
        waitForElementToBeVisible(leadContactAddress2).sendKeys(addressLine2);
    }

    public void iSetLeadContactCity(String city) {
        waitForElementToBeVisible(leadContactCity).sendKeys(city);
    }

    public void iSetLeadContactPostcode(String postcode) {
        waitForElementToBeVisible(leadContactPostcode).sendKeys(postcode);
    }

    public void iSelectLeadContactCountry(String country) {
        new Select(waitForElementToBeVisible(leadContactCountry)).selectByVisibleText(country);
    }

    public void iSelectHearAboutUs(String hearAbout) {
        new Select(waitForElementToBeVisible(leadContactHearAboutUs)).selectByVisibleText(hearAbout);
    }

    public void iClickContinueForLeadContactDetails() {
        waitForElementToBeVisible(leadContactContinueButton).click();
    }

    public String getSubTotal(){
        return waitForElementToBeVisible(subtotal).getText();
    }


}
