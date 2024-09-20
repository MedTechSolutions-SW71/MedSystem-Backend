package org.medTechSolutions.platform.security_service.auth.application.internal.queryServices;


import org.medTechSolutions.platform.security_service.auth.domain.model.aggregates.User;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetAllUsersQuery;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetUserByIdQuery;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetUserByUsernameQuery;
import org.medTechSolutions.platform.security_service.auth.domain.services.UserQueryService;
import org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }

}
