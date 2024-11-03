package org.medTechSolutions.platform.chat_service.Chat.Interfaces.rest.cucumber;

import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class ChatSteps {

    @Given("the chat details are provided")
    public void the_chat_details_are_provided() {
        // Setup code for providing chat details
    }

    @When("the user sends the message")
    public void the_user_sends_the_message() {
        // Code to send the message
    }

    @Then("the message is sent successfully")
    public void the_message_is_sent_successfully() {
        // Code to verify the message sending
        assertTrue(true); // Replace with actual verification
    }
}
