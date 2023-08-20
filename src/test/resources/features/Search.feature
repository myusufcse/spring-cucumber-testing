Feature: Google search functionality

  Scenario: Google search for valid char
    Given I open the google page
    When  I enter the valid char
    Then  I should receive the result for it