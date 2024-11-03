package org.medTechSolutions.platform.profiles_service.User.Domain.Services;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Patient;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreatePatientCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeletePatientCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdatePatientCommand;

import java.util.Optional;

public interface PatientCommandService {
    Long handle(CreatePatientCommand command);
    Optional<Patient> handle(UpdatePatientCommand command);
    void handle(DeletePatientCommand command);
}