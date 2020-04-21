Feature: test app

  Scenario: write and print some text
    Given prepare text
    When write text
    Then get printed text