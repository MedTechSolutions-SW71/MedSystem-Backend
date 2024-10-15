package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.UpdateDoctorResource;

public class UpdateDoctorCommandFromResourceAssembler {
    public static UpdateDoctorCommand toCommandFromResource(UpdateDoctorResource resource, Long id) {
        return new UpdateDoctorCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.licenceNumber(),
                resource.specialities(),
                resource.phone()
                //resource.idLaboratory()
        );
    }
}
