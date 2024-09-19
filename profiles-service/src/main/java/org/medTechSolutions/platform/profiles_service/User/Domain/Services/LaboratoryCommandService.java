package org.medTechSolutions.platform.profiles_service.User.Domain.Services;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Laboratory;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeleteLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdateLaboratoryCommand;

import java.util.Optional;

public interface LaboratoryCommandService {
    Long handle(CreateLaboratoryCommand command);
    Optional<Laboratory> handle(UpdateLaboratoryCommand command);
    void handle(DeleteLaboratoryCommand command);
}