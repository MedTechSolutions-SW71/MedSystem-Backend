package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.CreatePatientResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.UpdatePatientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingPatientId;

    @BeforeEach
    void setUp() throws Exception {
        CreatePatientResource createPatientResource = new CreatePatientResource(
                "John",
                "Doe",
                45,
                "123 Main St",
                "987654321",
                "john@example.com");

        String response = mockMvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createPatientResource)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        existingPatientId = objectMapper.readTree(response).get("id").asLong();
    }

    @Test
    void testCreatePatient() throws Exception {
        CreatePatientResource createPatientResource = new CreatePatientResource(
                "Alison",
                "Doe",
                50,
                "456 Elm St",
                "987654321",
                "alison@example.com");

        mockMvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createPatientResource)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Alison"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.address").value("456 Elm St"));
    }

    @Test
    void testGetPatientById() throws Exception {
        mockMvc.perform(get("/api/v1/patients/{patientId}", existingPatientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingPatientId));
    }

    @Test
    void testGetAllPatients() throws Exception {
        mockMvc.perform(get("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testUpdatePatient() throws Exception {
        UpdatePatientResource updatePatientResource = new UpdatePatientResource(
                "Emily",
                "Doe",
                30,
                "789 Pine St",
                "123456789",
                "emily@example.com");

        mockMvc.perform(put("/api/v1/patients/{patientId}", existingPatientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatePatientResource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Emily"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.address").value("789 Pine St"));
    }

    @Test
    void testDeletePatient() throws Exception {
        mockMvc.perform(delete("/api/v1/patients/{patientId}", existingPatientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Patient with given id successfully deleted"));
    }
}