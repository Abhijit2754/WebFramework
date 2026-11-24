@GoogleTestScenarios
Feature: Google Pages Automation Scenarios

  @GoogleDemoTest
  Scenario Outline: open the browser
    Given User open browser and enter url "googleurl"
    Then user check the Text "About" on google home page
    Then user check the Text "Store" on google home page
    And user enter text "News today" in search box on google home page
    Then user click on "<SuggestionText>" text from the suggetion on google home page

    Examples:
      | SuggestionText |
      | News Today     |