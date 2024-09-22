package org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources;

public record SignInResource(
    String email,
    String password
) {

}
