package org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources;

import java.util.List;

public record UserResource(
    Long id,
    String email,
    String role
) {

}
