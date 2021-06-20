package com.stepDefinitions;

import com.drivers.DriverManager;
import com.pages.BookingPage;
import com.pages.HomePage;
import com.pages.IncredibleIndiaPage;
import com.pages.TourSearchPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class MyStepdefs {

    private DriverManager driverManager = new DriverManager();
    private HomePage homePage = new HomePage(driverManager.getDriver());
    private TourSearchPage tourSearchPage = new TourSearchPage(driverManager.getDriver());
    private IncredibleIndiaPage incredibleIndiaPage = new IncredibleIndiaPage(driverManager.getDriver());
    private BookingPage bookingPage = new BookingPage(driverManager.getDriver());

    @Given("I goto {string} website")
    public void iGotoWebsite(String websiteName) {
        driverManager.launchApplication();
    }

    @Then("I see mail travel home page")
    public void iSeeMailTravelHomePage() {
        homePage.assertPageIsDisplayed();
    }

    @When("I search for {string} in search box")
    public void iSearchForInSearchBox(String value) {
        homePage.iSearchFor(value);
    }

    @When("I click {string} on the first search result")
    public void iClickOnTheFirstSearchResult(String button) {
        tourSearchPage.iClickOnMoreInfo();
    }

    @Then("I see days, price and telephone number")
    public void iSeeDaysPriceAndTelephoneNumber() {
        incredibleIndiaPage.iSeeDays();
        incredibleIndiaPage.iSeePrice();
        incredibleIndiaPage.iSeeTelephoneNumber();
    }

    @When("I click on \"Book Online\" button")
    public void iClickOnButton() {
        incredibleIndiaPage.iClickBookOnline();
    }

    @Then("It selects first available date in calendar with default values")
    public void itSelectsFirstAvailableDateInCalendarWithDefaultValues() {
        bookingPage.iSeeFirstAvailableDateSelected();
    }

    @When("I select {string}")
    public void iSelect(String value) {
        bookingPage.iSelectStandardRoom();
    }

    @Then("I see {string} section")
    public void iSeeSection(String title) {
        bookingPage.iSeeSection(title);
    }

    @When("I fill the passenger details:")
    public void iFillThePassengerDetails(List<Map<String, String>> passengerDetails) {
        for (int i = 0, passengerNumber = 2; i < passengerDetails.size() && passengerNumber <= 2; i++, passengerNumber++) {
            bookingPage.iSelectTitle(passengerDetails.get(i).get("Title"), passengerNumber);
            bookingPage.iSetFirstName(passengerDetails.get(i).get("First Name"), passengerNumber);
            bookingPage.iSetLastName(passengerDetails.get(i).get("Last Name"), passengerNumber);
            bookingPage.iSetDateOfBirth(passengerDetails.get(i).get("Date of Birth"), passengerNumber);
            if (Boolean.parseBoolean(passengerDetails.get(i).get(" I have my own insurance"))) {
                bookingPage.iSelectIHaveMyOwnInsuranceRadioButton(passengerNumber);
            }
        }
    }

    @When("I fill lead Contact details:")
    public void iFillLeadContactDetails(List<Map<String, String>> leadContactDetails) {
        bookingPage.iSetLeadContactFirstName(leadContactDetails.get(0).get("Your Name"));
        bookingPage.iSetLeadContactMobilePhoneNumber(leadContactDetails.get(0).get("Mobile Phone Number"));
        bookingPage.iSetLeadContactEmail(leadContactDetails.get(0).get("Email Address"));
        bookingPage.iSetLeadContactAddressLine1(leadContactDetails.get(0).get("Address Line 1"));
        bookingPage.iSetLeadContactAddressLine2(leadContactDetails.get(0).get("Address Line 2"));
        bookingPage.iSetLeadContactCity(leadContactDetails.get(0).get("City"));
        bookingPage.iSetLeadContactPostcode(leadContactDetails.get(0).get("Postcode"));
        bookingPage.iSelectLeadContactCountry(leadContactDetails.get(0).get("Country"));
        bookingPage.iSelectHearAboutUs(leadContactDetails.get(0).get("Hear About Us"));
        bookingPage.iClickContinueForLeadContactDetails();
    }

    @Then("I see payment amount as per the accommodation selected")
    public void iSeePaymentAmountAsPerTheAccommodationSelected() {
    }

    @When("I click on \"Select your room and continue\" button")
    public void iClickOnContinueButton() {
        bookingPage.iClickSelectYourRoomAndContinueButton();
    }

    @When("I click on \"Continue without extras\" button")
    public void iClickOnContinueWithoutExtrasButton() {
        bookingPage.iClickContinueWithoutExtraButton();
    }

    @After
    public void cleanup() {
        if (driverManager.getDriver() != null)
            driverManager.getDriver().close();
    }

}
