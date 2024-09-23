package org.medTechSolutions.platform.security_service.auth.domain.services;


import org.medTechSolutions.platform.security_service.auth.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
