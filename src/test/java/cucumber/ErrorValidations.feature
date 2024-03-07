
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Validating Correct error message is displayed
    Given I landed on Ecommerce Page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username  				 | password    |
      | xhrisgio@gmail.com | Test123     |
