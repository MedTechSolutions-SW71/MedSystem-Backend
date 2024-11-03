Feature: Manage Treatments

    Scenario: Create a new treatment
        Given the treatment details are provided
        When the user submits the treatment request
        Then the treatment is created successfully