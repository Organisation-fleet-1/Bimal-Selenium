Feature: To check search for Employee Education Content

  @smoketest
  Scenario Outline: Search for Employee Education Content
    Given Open browser with url and navigate to the BH home page
    When search the provided input item "<InputSearch>"
    Then verify expected first search result should match "<InputSearch>"

    Examples: 
      | InputSearch                                     |
      | Employee Education in 2018: Strategies to Watch |
