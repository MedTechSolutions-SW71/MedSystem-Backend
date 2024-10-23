package org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources;

public record AuthenticatedUserResource(
    Long id,
    String username,
    String token,
    String role
) {

}
