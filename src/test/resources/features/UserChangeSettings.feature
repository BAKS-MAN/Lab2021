@Test_002

Feature: User is able to change general settings

  Scenario: Change general settings
    Given open reportportal main page
    When login as regular user
    Then user is logged in
    When navigate to settings page
    Then settings page is open
    When update settings
    Then settings were updated
