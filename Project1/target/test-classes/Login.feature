Feature: Employee Login

  Background:
    Given a Employee is on the LoginPage

  Scenario Outline: A Employee can Login using their credentials

    When the Employee types in their "<username>" and "<password>" and clicks the LoginButton
    Then the Employee should be on the HomePage

    Examples:
      | username | password |
      | tyler    | snappy   |
      | manager  | pass     |
