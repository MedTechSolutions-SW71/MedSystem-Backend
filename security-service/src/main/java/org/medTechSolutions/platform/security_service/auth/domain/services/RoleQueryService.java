package org.medTechSolutions.platform.security_service.auth.domain.services;



import org.medTechSolutions.platform.security_service.auth.domain.model.entities.Role;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetAllRolesQuery;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query); // retorna todos los roles
    Optional<Role> handle(GetRoleByNameQuery query); // retorna un rol por nombre

}
