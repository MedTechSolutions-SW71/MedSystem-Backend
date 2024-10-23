package org.medTechSolutions.platform.security_service.auth.application.internal.commandServices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.medTechSolutions.platform.security_service.auth.application.internal.outboundservices.hashing.HashingService;
import org.medTechSolutions.platform.security_service.auth.application.internal.outboundservices.tokens.TokenService;
import org.medTechSolutions.platform.security_service.auth.domain.model.aggregates.User;
import org.medTechSolutions.platform.security_service.auth.domain.model.commands.SignInCommand;
import org.medTechSolutions.platform.security_service.auth.domain.model.commands.SignUpCommand;
import org.medTechSolutions.platform.security_service.auth.domain.services.SecurityProfilesService;
import org.medTechSolutions.platform.security_service.auth.domain.services.UserCommandService;
import org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {

        // verify if user already exists with the same email
        if (userRepository.existsByEmail(command.email())) {
            throw new RuntimeException("Username already exists");
        }

        // verify if role exists
        if (!roleRepository.existsByName(command.role().getName())) {
            throw new RuntimeException("Role not found");
        }

        // create user
        var user = new User(command.email(), hashingService.encode(command.password()), roleRepository.findByName(command.role().getName()).get());
        userRepository.save(user);

        // verify if user was saved
        if (user.getId() == null) {
            throw new RuntimeException("User ID must not be null after saving");
        }

        return userRepository.findByEmail(command.email());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!hashingService.matches(command.password(), user.getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.getEmail());
        return Optional.of(new ImmutablePair<>(user, token));
    }
}
