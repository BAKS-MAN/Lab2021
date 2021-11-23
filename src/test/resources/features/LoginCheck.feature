@Test_001

Feature: User is able to login

  @CloseBrowser
  Scenario Outline: login check
    Given open reportportal main page
    When login as <userType>
    Then user is logged in
    When logout

    Examples:
      | userType     |
      | regular user |
      | admin        |