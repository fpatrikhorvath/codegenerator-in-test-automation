Feature: Delete user

  Scenario: Delete user
    Given a new user of status A and store it as test -> CREATED
    When delete test user -> NO_CONTENT
    Then verify that test user does not exist