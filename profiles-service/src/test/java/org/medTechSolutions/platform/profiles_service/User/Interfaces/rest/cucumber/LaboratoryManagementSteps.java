package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LaboratoryManagementSteps {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Given("tengo los detalles del laboratorio")
    public void tengo_los_detalles_del_laboratorio() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @When("envío una solicitud para crear un laboratorio")
    public void envio_una_solicitud_para_crear_un_laboratorio() throws Exception {
        String laboratoryJson = "{ \"name\": \"LabCorp\"," +
                " \"address\": \"123 Lab St\"," +
                " \"phone\": \"987654321\"," +
                " \"email\": \"labcorp@example.com\" }";
        mockMvc.perform(post("/api/v1/laboratories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(laboratoryJson))
                .andExpect(status().isCreated());
    }

    @Then("el laboratorio debe ser creado exitosamente")
    public void el_laboratorio_debe_ser_creado_exitosamente() {

    }
}