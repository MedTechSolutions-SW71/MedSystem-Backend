package org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources;

public record SignInResource(
    String username,
    String password
) {

}
