Feature: Create book

  Scenario: Create book for user
    Given a new user of status A and store it as Antoine -> CREATED
    When create a new book for user Antoine and store it as the_little_prince -> CREATED
    Then verify that book the_little_prince exist