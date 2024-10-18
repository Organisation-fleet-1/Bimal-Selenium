Feature: Child Care Center Locator

  @smoketest
  Scenario: Verify url after clicking find center option
    Given Open browser with url and navigate to the BH home page
    When click on the Find a Center option
    Then the url should contain "/child-care-locator"

  @smoketest
  Scenario: Verify number of centers based on search
    Given Open browser with url and navigate to the BH home page
    When click on the Find a Center option
    And type "New York" into the search box and press enter
    Then the number of found centers should match the number displayed in the list

  @smoketest
  Scenario: Verify number of centers based on search
    Given Open browser with url and navigate to the BH home page
    When click on the Find a Center option
    And type "New York" into the search box and press enter
    And click on the first center in the list
    Then the center name and address should match with the details on the popup
