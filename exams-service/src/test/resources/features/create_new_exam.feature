Feature: Manage Exams

  Scenario: Create a new exam
    Given the exam details are provided
    When the user submits the exam request
    Then the exam is created successfully