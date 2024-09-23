package org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories;


import org.medTechSolutions.platform.security_service.auth.domain.model.entities.Role;
import org.medTechSolutions.platform.security_service.auth.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // busca en la base de datos un rol por su nombre
    Optional<Role> findByName(Roles name);

    // verifica si existe un rol con el nombre
    boolean existsByName(Roles name);

}
