Feature: Create user

  Scenario: Create user
    When create a new user of status A and store it as Patrik -> OK
    Then verify that user Patrik exists