@test_001

Feature: User is able to edit dashboard

  Scenario Outline: login check
    Given open reportportal main page
    When login as <userType>
    Then dashboard page is open

    Examples:
      | userType     |
      | regular user |
      | admin        |