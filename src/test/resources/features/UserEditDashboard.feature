@Test_003

Feature: User is able to edit dashboard

  Scenario: Edit dashboard
    Given open reportportal main page
    When login as admin
    Then dashboard page is open
    And add new dashboard
    And delete added dashboard