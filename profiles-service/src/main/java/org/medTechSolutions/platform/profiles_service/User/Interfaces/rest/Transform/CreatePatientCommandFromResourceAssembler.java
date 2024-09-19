package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreatePatientCommand;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.CreatePatientResource;

public class CreatePatientCommandFromResourceAssembler {  public static CreatePatientCommand toCommandFromResource(CreatePatientResource resource) {
    return new CreatePatientCommand(
            resource.firstName(),
            resource.lastName(),
            resource.age(),
            resource.address(),
            resource.phone(),
            resource.email()
    );
}
}
