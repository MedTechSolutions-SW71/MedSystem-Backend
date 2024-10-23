package org.medTechSolutions.platform.profiles_service.User.Application.internal.commandServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Patient;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreatePatientCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeletePatientCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdatePatientCommand;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.PatientRepository;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientCommandServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientCommandServiceImpl patientCommandService;

    /*
    @Test
    void testCreatePatientSuccess() {
        // Setup
        CreatePatientCommand command = new CreatePatientCommand(
                "John",
                "Doe",
                30,
                "123 Main St",
                "123-456-7890",
                "john.doe@example.com");
        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> {
            Patient patient = invocation.getArgument(0);
            patient.setId(1L); // Simulate ID assignment
            return patient;
        });

        // Execute
        Long patientId = patientCommandService.handle(command);

        // Verify
        assertNotNull(patientId);
        assertEquals(1L, patientId);
        verify(patientRepository).save(any(Patient.class));
    }

     */

    @Test
    void testCreatePatientFailure() {
        // Setup
        CreatePatientCommand command = new CreatePatientCommand(
                1L,
                "Doe",
                "Patient");
        when(patientRepository.save(any(Patient.class))).thenThrow(
                new RuntimeException("Database error"));

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> patientCommandService.handle(command));
        assertEquals("Error while saving patient: Database error",
                exception.getMessage());
    }

    @Test
    void testUpdatePatientSuccess() {
        // Setup
        UpdatePatientCommand command = new UpdatePatientCommand(
                1L,
                "John",
                "Doe",
                31,
                "456 Elm St",
                "987-654-3210",
                "john.doe@example.com");
        Patient existingPatient = new Patient();
        when(patientRepository.findById(1L)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(existingPatient);

        // Execute
        Optional<Patient> updatedPatient = patientCommandService.handle(command);

        // Verify
        assertTrue(updatedPatient.isPresent());
        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    void testUpdatePatientFailure_PatientNotFound() {
        // Setup
        UpdatePatientCommand command = new UpdatePatientCommand(
                1L,
                "John",
                "Doe",
                31,
                "456 Elm St",
                "987-654-3210",
                "john.doe@example.com");
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> patientCommandService.handle(command));
        assertEquals("Patient does not exist", exception.getMessage());
        verify(patientRepository, never()).save(any(Patient.class));
    }

    @Test
    void testDeletePatientSuccess() {
        // Setup
        DeletePatientCommand command = new DeletePatientCommand(1L);
        when(patientRepository.existsById(1L)).thenReturn(true);

        // Execute
        patientCommandService.handle(command);

        // Verify
        verify(patientRepository).deleteById(1L);
    }

    @Test
    void testDeletePatientFailure_PatientNotFound() {
        // Setup
        DeletePatientCommand command = new DeletePatientCommand(1L);
        when(patientRepository.existsById(1L)).thenReturn(false);

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> patientCommandService.handle(command));
        assertEquals("Patient does not exist", exception.getMessage());
        verify(patientRepository, never()).deleteById(anyLong());
    }
}