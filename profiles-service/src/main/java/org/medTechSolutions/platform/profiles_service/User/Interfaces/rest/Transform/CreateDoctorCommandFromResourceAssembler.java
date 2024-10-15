package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.CreateDoctorResource;

public class CreateDoctorCommandFromResourceAssembler {public static CreateDoctorCommand toCommandFromResource(CreateDoctorResource resource) {
    return new CreateDoctorCommand(
            resource.firstName(),
            resource.lastName(),
            resource.licenceNumber(),
            resource.specialities(),
            resource.phone(),
            resource.email()
            //resource.idLaboratory()
    );
}
}
