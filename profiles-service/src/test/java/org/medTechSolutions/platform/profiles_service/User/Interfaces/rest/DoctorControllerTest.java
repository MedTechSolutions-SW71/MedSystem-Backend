package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.ValueObjects.Specialities;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.CreateDoctorResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.UpdateDoctorResource;
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
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingDoctorId;

    @BeforeEach
    void setUp() throws Exception {
        CreateDoctorResource createDoctorResource = new CreateDoctorResource(
                "John",
                "Doe",
                1325434,
                Specialities.NEUROLOGY,
                "987654321",
                "john@example.com");

        String response = mockMvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDoctorResource)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        existingDoctorId = objectMapper.readTree(response).get("id").asLong();
    }

    @Test
    void testCreateDoctor() throws Exception {
        CreateDoctorResource createDoctorResource = new CreateDoctorResource(
                "Alison",
                "Doe",
                1213432,
                Specialities.CARDIOLOGY,
                "987654321",
                "alison@example.com");

        mockMvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDoctorResource)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Alison"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.specialization").value("Cardiology"));
    }

    @Test
    void testGetDoctorById() throws Exception {
        mockMvc.perform(get("/api/v1/doctors/{doctorId}", existingDoctorId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingDoctorId));
    }

    @Test
    void testGetAllDoctors() throws Exception {
        mockMvc.perform(get("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testUpdateDoctor() throws Exception {
        UpdateDoctorResource updateDoctorResource = new UpdateDoctorResource(
                "Emily",
                "Doe",
                132,
                Specialities.NEUROLOGY,
                "123456789",
                "emily@example.com");

        mockMvc.perform(put("/api/v1/doctors/{doctorId}", existingDoctorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDoctorResource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Emily"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.specialization").value("Neurology"));
    }

    @Test
    void testDeleteDoctor() throws Exception {
        mockMvc.perform(delete("/api/v1/doctors/{doctorId}", existingDoctorId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Doctor with given id successfully deleted"));
    }
}