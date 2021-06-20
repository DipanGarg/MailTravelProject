@MailTravel
Feature: Book a travel via mail travel

  Scenario: Verify user is able to do the booking via mail travel website
    Given I goto "Mail travel" website
    And I see mail travel home page
    When I search for "India" in search box
    And I click "more info" on the first search result
    Then I see days, price and telephone number
    When I click on "Book Online" button
    Then It selects first available date in calendar with default values
    When I select "Standard room"
    And I click on "Select your room and continue" button
    Then I see "Extras" section
    When I click on "Continue without extras" button
    Then I see "Passenger Details" section
    When I fill the passenger details:
      | Title | First Name | Last Name | Date of Birth    | I have my own insurance |
      | Mr    | Jim        | Conor     | 1-January-1990   | true                    |
      | Mrs   | Jean       | Smith     | 12-February-1989 | true                    |
    And I fill lead Contact details:
      | Your Name | Mobile Phone Number | Email Address      | Address Line 1 | Address Line 2 | City   | Postcode | Country        | Hear About Us |
      | Jim Conor | 7865432123          | jimconor@gmail.com | 4              | Union Street   | London | M34 4RE  | United Kingdom | Email         |
    Then I see payment amount as per the accommodation selected