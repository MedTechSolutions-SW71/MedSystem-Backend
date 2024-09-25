package org.medTechSolutions.platform.profiles_service.User.Application.internal.commandServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Laboratory;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeleteLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdateLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.LaboratoryRepository;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LaboratoryCommandServiceImplTest {

    @Mock
    private LaboratoryRepository laboratoryRepository;

    @InjectMocks
    private LaboratoryCommandServiceImpl laboratoryCommandService;

    /*
    @Test
    void testCreateLaboratorySuccess() {
        // Setup
        CreateLaboratoryCommand command = new CreateLaboratoryCommand(
                "Lab Name",
                "123 Main St",
                "123-456-7890",
                "lab@example.com");
        when(laboratoryRepository.save(any(Laboratory.class)))
                .thenAnswer(invocation -> {
            Laboratory laboratory = invocation.getArgument(0);
            laboratory.setId(1L); // Simulate ID assignment
            return laboratory;
        });

        // Execute
        Long laboratoryId = laboratoryCommandService.handle(command);

        // Verify
        assertNotNull(laboratoryId);
        assertEquals(1L, laboratoryId);
        verify(laboratoryRepository).save(any(Laboratory.class));
    }
     */

    @Test
    void testCreateLaboratoryFailure() {
        // Setup
        CreateLaboratoryCommand command = new CreateLaboratoryCommand(
                "Lab Name",
                "123 Main St",
                "123-456-7890",
                "lab@example.com");
        when(laboratoryRepository.save(any(Laboratory.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> laboratoryCommandService.handle(command));
        assertEquals("Error while saving laboratory: Database error",
                exception.getMessage());
    }

    @Test
    void testUpdateLaboratorySuccess() {
        // Setup
        UpdateLaboratoryCommand command = new UpdateLaboratoryCommand(
                1L,
                "Updated Lab Name",
                "456 Elm St",
                "987-654-3210",
                "updated@example.com");
        Laboratory existingLaboratory = new Laboratory();
        when(laboratoryRepository.findById(1L)).thenReturn(Optional.of(existingLaboratory));
        when(laboratoryRepository.save(any(Laboratory.class))).thenReturn(existingLaboratory);

        // Execute
        Optional<Laboratory> updatedLaboratory = laboratoryCommandService.handle(command);

        // Verify
        assertTrue(updatedLaboratory.isPresent());
        verify(laboratoryRepository).save(any(Laboratory.class));
    }

    @Test
    void testUpdateLaboratoryFailure_LaboratoryNotFound() {
        // Setup
        UpdateLaboratoryCommand command = new UpdateLaboratoryCommand(
                1L,
                "Updated Lab Name",
                "456 Elm St",
                "987-654-3210",
                "updated@example.com");
        when(laboratoryRepository.findById(1L)).
                thenReturn(Optional.empty());

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> laboratoryCommandService.handle(command));
        assertEquals("Laboratory does not exist",
                exception.getMessage());
        verify(laboratoryRepository, never()).save(any(Laboratory.class));
    }

    @Test
    void testDeleteLaboratorySuccess() {
        // Setup
        DeleteLaboratoryCommand command = new DeleteLaboratoryCommand(1L);
        when(laboratoryRepository.existsById(1L)).thenReturn(true);

        // Execute
        laboratoryCommandService.handle(command);

        // Verify
        verify(laboratoryRepository).deleteById(1L);
    }

    @Test
    void testDeleteLaboratoryFailure_LaboratoryNotFound() {
        // Setup
        DeleteLaboratoryCommand command = new DeleteLaboratoryCommand(1L);
        when(laboratoryRepository.existsById(1L)).thenReturn(false);

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> laboratoryCommandService.handle(command));
        assertEquals("Laboratory does not exist", exception.getMessage());
        verify(laboratoryRepository, never()).deleteById(anyLong());
    }
}