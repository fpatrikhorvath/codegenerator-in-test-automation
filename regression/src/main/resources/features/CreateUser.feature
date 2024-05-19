Feature: Create user

  Scenario: Create user
    When create a new user of status A and store it as test -> CREATED
    Then verify that test user exists