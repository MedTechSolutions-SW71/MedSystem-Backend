package org.medTechSolutions.platform.profiles_service.User.Application.internal.commandServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Doctor;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeleteDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.ValueObjects.Specialities;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.DoctorRepository;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.LaboratoryRepository;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorCommandServiceImplTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private LaboratoryRepository laboratoryRepository;

    @InjectMocks
    private DoctorCommandServiceImpl doctorCommandService;

    /*
    @Test
    void testCreateDoctorSuccess() {
        // Setup
        CreateDoctorCommand command = new CreateDoctorCommand(
                "Juan",
                "John",
                "Cardiology",
                1123123,
                "12345",
                "juan@email.com"
        );

        when(doctorRepository.save(any(Doctor.class)))
                .thenAnswer(invocation -> {
            Doctor doctor = invocation.getArgument(0);
            doctor.setId(1L); // Simulate ID assignment
            return doctor;
        });

        // Execute
        Long doctorId = doctorCommandService.handle(command);

        // Verify
        assertNotNull(doctorId);
        assertEquals(1L, doctorId);
        verify(doctorRepository).save(any(Doctor.class));
    }

     */

    @Test
    void testCreateDoctorFailure() {
        // Setup
        CreateDoctorCommand command = new CreateDoctorCommand(
                "Juan",
                "John",
                1234566,
                Specialities.CARDIOLOGY,
                "12345",
                "juan@email.com"
        );

        when(doctorRepository.save(any(Doctor.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> doctorCommandService.handle(command));
        assertEquals("Error while saving doctor: Database error",
                exception.getMessage());
    }

    @Test
    void testUpdateDoctorSuccess() {
        // Setup
        UpdateDoctorCommand command = new UpdateDoctorCommand(
                1L,
                "John",
                "Doe",
                123243,
                Specialities.NEUROLOGY,
                "123-456-7890"
        );
        Doctor existingDoctor = new Doctor();
        when(doctorRepository.findById(1L))
                .thenReturn(Optional.of(existingDoctor));
        when(doctorRepository.save(any(Doctor.class)))
                .thenReturn(existingDoctor);

        // Execute
        Optional<Doctor> updatedDoctor = doctorCommandService
                .handle(command);

        // Verify
        assertTrue(updatedDoctor.isPresent());
        verify(doctorRepository).save(any(Doctor.class));
    }

    @Test
    void testCreateDoctorFailure_DuplicateLicenceNumber() {
        // Setup
        CreateDoctorCommand command = new CreateDoctorCommand(
                "Juan",
                "John",
                1234566,
                Specialities.CARDIOLOGY,
                "12345",
                "juan@email.com"
        );

        when(doctorRepository.existsDoctorByLicenceNumber(command.licenceNumber())).thenReturn(true);

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> doctorCommandService.handle(command));
        assertEquals("Doctor with licence number 12345 already exists", exception.getMessage());
    }

    @Test
    void testUpdateDoctorFailure_DoctorNotFound() {
        // Setup
        UpdateDoctorCommand command = new UpdateDoctorCommand(
                1L,
                "John",
                "Doe",
                1232435,
                Specialities.NEUROLOGY,
                "123-456-7890"
        );
        when(doctorRepository.findById(1L)).thenReturn(Optional.empty());

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> doctorCommandService.handle(command));
        assertEquals("Doctor does not exist", exception.getMessage());
        verify(doctorRepository, never()).save(any(Doctor.class));
    }

    @Test
    void testDeleteDoctorSuccess() {
        // Setup
        DeleteDoctorCommand command = new DeleteDoctorCommand(1L);
        when(doctorRepository.existsById(1L)).thenReturn(true);

        // Execute
        doctorCommandService.handle(command);

        // Verify
        verify(doctorRepository).deleteById(1L);
    }

    @Test
    void tesDeleteDoctorFailure_DoctorNotFound() {
        // Setup
        DeleteDoctorCommand command = new DeleteDoctorCommand(1L);
        when(doctorRepository.existsById(1L)).thenReturn(false);

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> doctorCommandService.handle(command));
        assertEquals("Doctor does not exist", exception.getMessage());
        verify(doctorRepository, never()).deleteById(anyLong());
    }
}