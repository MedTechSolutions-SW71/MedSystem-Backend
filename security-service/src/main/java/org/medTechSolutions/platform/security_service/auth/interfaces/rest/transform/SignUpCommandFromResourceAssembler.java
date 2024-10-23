package org.medTechSolutions.platform.security_service.auth.interfaces.rest.transform;



import org.medTechSolutions.platform.security_service.auth.domain.model.commands.SignUpCommand;
import org.medTechSolutions.platform.security_service.auth.domain.model.entities.Role;
import org.medTechSolutions.platform.security_service.auth.domain.model.valueobjects.Roles;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {

        var erole = new Role(Roles.valueOf(resource.role()));

        return new SignUpCommand(resource.email(), resource.password(), erole);
    }

}