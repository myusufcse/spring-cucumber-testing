@Login
  Feature: Login feature

    Scenario: Launch B2B portal
      Given  I am on the B2B portal
      When   I enter username and password
      Then   I should be able to login