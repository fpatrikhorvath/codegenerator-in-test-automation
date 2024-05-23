Feature: Delete user

  Scenario: Delete user
    Given a new user of status A and store it as Thomas -> CREATED
    When delete user Thomas -> NO_CONTENT
    Then verify that user Thomas does not exist