package org.medTechSolutions.platform.security_service.auth.domain.services;


import org.medTechSolutions.platform.security_service.auth.domain.model.aggregates.User;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetAllUsersQuery;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetUserByIdQuery;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetUserByEmailQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query); // retorna todos los usuarios
    Optional<User> handle(GetUserByIdQuery query); // retorna un usuario por id
    Optional<User> handle(GetUserByEmailQuery query); // retorna un usuario por username


}
