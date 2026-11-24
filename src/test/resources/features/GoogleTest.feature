@TestDemo
Feature: Google Pages Automation Scenarios

  @GoogleDemoTest
  Scenario: open the browser
    Given User open browser and enter url "googleurl"
    Then user check the Text "About" on google home page
    Then user check the Text "Stodcdcdcdcdr1e" on google home page
#    Then user click on the "Gmail" link on google home page
#    Then user search the text "Today News" on google home page
#    Then user click on the "Google Search" button on google home page

