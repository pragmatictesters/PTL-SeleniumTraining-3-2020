Feature: Login to PTL HRM


  Scenario: Login to the system with valid credentials
    Given I have access the login page
    When I type a valid user name "Admin"
    And I type a valid password "Ptl@#321"
    And I click on the login button
    Then I should be logged into the system with welcome message "Welcome Admin"


  Scenario Outline: Login with invalid credentials
    Given I have access the login page
    When I type a valid user name "<username>"
    And I type a valid password "<password>"
    And I click on the login button
    Then I should see error message "<expected_error>"

    Examples:
      | username | password | expected_error           |
      |          |          | Username cannot be empty |
      |          | Ptl@#321 | Username cannot be empty |
      | Admin    |          | Password cannot be empty |
      |          | Ptl@#321 | Username cannot be empty |
      | Admin    | ptl@#321 | Invalid credentials      |
      | admin    | PTL@#321 | Invalid credentials      |


