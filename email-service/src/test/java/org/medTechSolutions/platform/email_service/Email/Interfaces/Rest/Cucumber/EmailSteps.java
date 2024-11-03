package org.medTechSolutions.platform.email_service.Email.Interfaces.Rest.Cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EmailSteps {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult result;

    @Given("el usuario está autenticado")
    public void el_usuario_está_autenticado() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @When("el usuario envía una solicitud para enviar un email con los siguientes detalles:")
    public void el_usuario_envía_una_solicitud_para_enviar_un_email_con_los_siguientes_detalles(
            DataTable dataTable) throws Exception {

        var data = dataTable.asMaps(String.class, String.class).get(0);
        String emailJson = "{ \"to\": \"" + data.get("to") + "\"," +
                " \"subject\": \"" + data.get("subject") + "\"," +
                " \"body\": \"" + data.get("body") + "\" }";

        result = mockMvc.perform(post("/api/email/send-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emailJson))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Then("el email debe ser enviado exitosamente")
    public void el_email_debe_ser_enviado_exitosamente() {
        assertEquals(200, result.getResponse().getStatus());
    }

    @Then("la respuesta debe contener el mensaje {string}")
    public void la_respuesta_debe_contener_el_mensaje(String expectedMessage) throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertNotNull(responseContent);
        assertEquals(expectedMessage, responseContent);
    }
}