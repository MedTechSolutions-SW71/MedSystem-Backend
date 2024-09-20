package org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories;


import org.medTechSolutions.platform.security_service.auth.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // busca en la base de datos un usuario por su nombre de usuario
    Optional<User> findByUsername(String username);
    
    // verifica si existe un usuario con el nombre de usuario
    boolean existsByUsername(String username);
}
