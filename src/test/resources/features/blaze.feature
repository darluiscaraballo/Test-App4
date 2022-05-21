@BlazeProductList

Feature: Demo Blaze product list

  @MonitorAvailable
  Scenario Outline: Validate Monitors available
    Given DemoBlaze is displayed
    When User Logins into the app with demo "<user>", "<pass>"
    And  Request Monitor Categories
    Then Two monitors items are returned
    Examples:
      | user | pass |
      | demo | demo |

  @ExtractPhoneList
  Scenario Outline: Extract Phone list available
    Given DemoBlaze is displayed
    When User Logins into the app with demo "<user>", "<pass>"
    And  Request Phones Categories
    And  User extract the result list to a txt file
    Then Text file generated contains 7 results and not from "<Text>" phone type
    Examples:
      | user | pass |Text|
      | demo | demo |Xiaomi|