package org.medTechSolutions.platform.security_service.auth.domain.model.queries;


import org.medTechSolutions.platform.security_service.auth.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles roleName) {

}
