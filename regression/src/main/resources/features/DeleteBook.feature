Feature: Delete book

  Scenario: Delete book
    Given a new user of status A and store it as Antoine -> CREATED
    Given a new book for user Antoine and store it as the_little_prince -> CREATED
    When delete book the_little_prince for user Antoine -> NO_CONTENT
    Then verify that book the_little_prince exist

  Scenario: Delete user with book
    Given a new user of status A and store it as Antoine -> CREATED
    Given a new book for user Antoine and store it as the_little_prince -> CREATED
    When delete user Antoine -> NO_CONTENT
    Then verify that book the_little_prince exist
