package org.medTechSolutions.platform.appointments_service.Appointment.Interfaces.rest.cucumber;

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
public class AppointmentManagementSteps {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult result;

    @Given("el usuario está autenticado")
    public void el_usuario_está_autenticado() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @When("el usuario envía una solicitud para crear una cita con los siguientes detalles:")
    public void el_usuario_envía_una_solicitud_para_crear_una_cita_con_los_siguientes_detalles(
            DataTable dataTable) throws Exception {
        var data = dataTable.asMaps(String.class, String.class).get(0);
        String appointmentJson = "{ \"doctorId\": " + data.get("doctor_id") + "," +
                " \"patientId\": " + data.get("paciente_id") + "," +
                " \"date\": \"" + data.get("fecha") + "\"," +
                " \"reason\": \"" + data.get("razon") + "\"," +
                " \"speciality\": \"CARDIOLOGY\" }";
        result = mockMvc.perform(post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentJson))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Then("la cita debe ser creada exitosamente")
    public void la_cita_debe_ser_creada_exitosamente() {
        assertEquals(201, result.getResponse().getStatus());
    }

    @Then("la respuesta debe contener los detalles de la cita creada")
    public void la_respuesta_debe_contener_los_detalles_de_la_cita_creada() throws Exception {
        String responseContent = result.getResponse().getContentAsString();
        assertNotNull(responseContent);

    }
}