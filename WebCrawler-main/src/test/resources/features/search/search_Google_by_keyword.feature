Feature: Search Google by keyword

  Scenario: Searching in Google
    Given I am on the Google search page
    And I Decline Cookies
    When I search for "Best Places to Live"
    And View images search
    And Open 3rd image in new tab
    Then Result should be visible in new tab