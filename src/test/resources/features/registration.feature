Feature: Patient arrival registration

  Scenario: Successful registration
    Given that the user is on the registration page
    When entering valid arrival data
    Then should see a success message in the reception log

  Scenario: Failed registration
    Given that the user is on the registration page
    When entering incomplete or invalid arrival data
    Then should see validation error messages
