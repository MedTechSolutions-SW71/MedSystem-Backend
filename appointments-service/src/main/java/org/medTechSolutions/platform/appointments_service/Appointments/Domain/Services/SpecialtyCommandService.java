package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Services;

import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.SeedSpecialtiesCommand;

public interface SpecialtyCommandService {
    void handle(SeedSpecialtiesCommand command);
}
