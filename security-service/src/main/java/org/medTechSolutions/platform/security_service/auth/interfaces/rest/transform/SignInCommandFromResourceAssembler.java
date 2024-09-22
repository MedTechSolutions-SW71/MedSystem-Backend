package org.medTechSolutions.platform.security_service.auth.interfaces.rest.transform;


import org.medTechSolutions.platform.security_service.auth.domain.model.commands.SignInCommand;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }

}
