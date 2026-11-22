@TestDemo
Feature: User is able to

  @GoogleDemoTest
  Scenario: open the browser
    Given User open browser and enter url "url"
    Then user check the Text "About" on google home page
    Then user check the Text "Store" on google home page
    And user enter the text "Today News" on google home page
