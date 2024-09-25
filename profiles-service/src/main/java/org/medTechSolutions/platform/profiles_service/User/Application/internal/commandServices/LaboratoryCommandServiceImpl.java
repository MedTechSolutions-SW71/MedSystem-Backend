package org.medTechSolutions.platform.profiles_service.User.Application.internal.commandServices;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Laboratory;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeleteLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdateLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.LaboratoryCommandService;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaboratoryCommandServiceImpl implements LaboratoryCommandService {

    private final LaboratoryRepository laboratoryRepository;

    @Autowired
    public LaboratoryCommandServiceImpl(LaboratoryRepository laboratoryRepository) {
        this.laboratoryRepository = laboratoryRepository;
    }

    @Override
    public Long handle(CreateLaboratoryCommand command) {

        Laboratory laboratory = new Laboratory(command);
        try {
            laboratoryRepository.save(laboratory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving laboratory: " + e.getMessage());
        }
        return laboratory.getId();
    }

    @Override
    public Optional<Laboratory> handle(UpdateLaboratoryCommand command) {
        var result = laboratoryRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Laboratory does not exist");
        var laboratoryToUpdate = result.get();

        try {
            var updatedLaboratory = laboratoryRepository.save(laboratoryToUpdate.update(
                    command.name(),
                    command.address(),
                    command.phone(),
                    command.email()
            ));
            return Optional.of(updatedLaboratory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating laboratory: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteLaboratoryCommand command) {
        if (!laboratoryRepository.existsById(command.laboratoryId())) {
            throw new IllegalArgumentException("Laboratory does not exist");
        }
        try {
            laboratoryRepository.deleteById(command.laboratoryId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting laboratory: " + e.getMessage());
        }
    }
}