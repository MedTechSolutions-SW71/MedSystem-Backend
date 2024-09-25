package org.medTechSolutions.platform.security_service.auth.domain.model.commands;



import org.medTechSolutions.platform.security_service.auth.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String email, String password, List<Role> roles) {

}
