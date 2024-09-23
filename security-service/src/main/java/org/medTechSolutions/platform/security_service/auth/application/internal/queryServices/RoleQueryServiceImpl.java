package org.medTechSolutions.platform.security_service.auth.application.internal.queryServices;


import org.medTechSolutions.platform.security_service.auth.domain.model.entities.Role;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetAllRolesQuery;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetRoleByNameQuery;
import org.medTechSolutions.platform.security_service.auth.domain.services.RoleQueryService;
import org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleQueryServiceImpl implements RoleQueryService {

    private final RoleRepository roleRepository;

    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.roleName());
    }

}
