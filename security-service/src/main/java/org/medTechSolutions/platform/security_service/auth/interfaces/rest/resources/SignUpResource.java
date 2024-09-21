package org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(
    String username,
    String password,
    List<String> roles
) {

}