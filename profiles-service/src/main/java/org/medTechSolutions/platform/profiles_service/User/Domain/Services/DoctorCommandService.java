package org.medTechSolutions.platform.profiles_service.User.Domain.Services;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Doctor;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeleteDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdateDoctorCommand;

import java.util.Optional;

public interface DoctorCommandService {
    Long handle(CreateDoctorCommand command);
    Optional<Doctor> handle(UpdateDoctorCommand command);
    void handle(DeleteDoctorCommand command);
}
