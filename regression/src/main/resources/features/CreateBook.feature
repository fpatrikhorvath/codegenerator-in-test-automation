Feature: Create book

  Scenario: Create book for user
    Given a new user of status A and store it as user_A -> CREATED
    When create a new book for user user_A and store it as book_A -> CREATED
    Then verify that book book_A exist