Feature: Manage Chat
  Scenario: Send a new message
    Given the chat details are provided
    When the user sends the message
    Then the message is sent successfully