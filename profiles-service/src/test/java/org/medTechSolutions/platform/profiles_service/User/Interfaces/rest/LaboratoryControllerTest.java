package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.CreateLaboratoryResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.UpdateLaboratoryResource;
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
class LaboratoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingLaboratoryId;

    @BeforeEach
    void setUp() throws Exception {
        CreateLaboratoryResource createLaboratoryResource = new CreateLaboratoryResource(
                "LabCorp",
                "123 Lab St",
                "987654321",
                "labcorp@example.com");

        String response = mockMvc.perform(post("/api/v1/laboratories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createLaboratoryResource)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        existingLaboratoryId = objectMapper.readTree(response).get("id").asLong();
    }

    @Test
    void testCreateLaboratory() throws Exception {
        CreateLaboratoryResource createLaboratoryResource = new CreateLaboratoryResource(
                "BioLab",
                "456 Bio St",
                "987654321",
                "biolab@example.com");

        mockMvc.perform(post("/api/v1/laboratories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createLaboratoryResource)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("BioLab"))
                .andExpect(jsonPath("$.address").value("456 Bio St"));
    }

    @Test
    void testGetLaboratoryById() throws Exception {
        mockMvc.perform(get("/api/v1/laboratories/{laboratoryId}", existingLaboratoryId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingLaboratoryId));
    }

    @Test
    void testGetAllLaboratories() throws Exception {
        mockMvc.perform(get("/api/v1/laboratories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testUpdateLaboratory() throws Exception {
        UpdateLaboratoryResource updateLaboratoryResource = new UpdateLaboratoryResource(
                "GenLab",
                "789 Gen St",
                "123456789",
                "genlab@example.com");

        mockMvc.perform(put("/api/v1/laboratories/{laboratoryId}", existingLaboratoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateLaboratoryResource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("GenLab"))
                .andExpect(jsonPath("$.address").value("789 Gen St"));
    }

    @Test
    void testDeleteLaboratory() throws Exception {
        mockMvc.perform(delete("/api/v1/laboratories/{laboratoryId}", existingLaboratoryId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Laboratory with given id successfully deleted"));
    }
}