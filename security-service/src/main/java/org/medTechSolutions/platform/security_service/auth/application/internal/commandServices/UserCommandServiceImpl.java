package org.medTechSolutions.platform.security_service.auth.application.internal.commandServices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.medTechSolutions.platform.security_service.auth.application.internal.outboundservices.hashing.HashingService;
import org.medTechSolutions.platform.security_service.auth.application.internal.outboundservices.tokens.TokenService;
import org.medTechSolutions.platform.security_service.auth.domain.model.aggregates.User;
import org.medTechSolutions.platform.security_service.auth.domain.model.commands.SignInCommand;
import org.medTechSolutions.platform.security_service.auth.domain.model.commands.SignUpCommand;
import org.medTechSolutions.platform.security_service.auth.domain.model.entities.Role;
import org.medTechSolutions.platform.security_service.auth.domain.model.valueobjects.Roles;
import org.medTechSolutions.platform.security_service.auth.domain.services.SecurityProfilesService;
import org.medTechSolutions.platform.security_service.auth.domain.services.UserCommandService;
import org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories.UserRepository;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.CreateDoctorDTO;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.CreateLaboratoryDTO;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.CreatePatientDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final SecurityProfilesService securityProfilesService;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService, SecurityProfilesService securityProfilesService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.securityProfilesService = securityProfilesService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new RuntimeException("Username already exists");
        }

        var roles = command.roles();
        if (roles.isEmpty()) {
            var role = roleRepository.findByName(Roles.Patient);
            if (role.isPresent()) roles.add(role.get());
        } else {
            roles = roles.stream().map(role -> roleRepository.findByName(role.getName())
                    .orElseThrow(() -> new RuntimeException("Role not found"))).toList();
        }

        var user = new User(command.email(), hashingService.encode(command.password()), roles);
        userRepository.save(user);

        if (user.getId() == null) {
            throw new RuntimeException("User ID must not be null after saving");
        }

        for (Role role : roles) {
            switch (role.getName()) {
                case Doctor:
                    var createDoctorDTO = new CreateDoctorDTO("defaultFirstName", "defaultLastName", "defaultSpecialization", 12345, "defaultPhone", user.getEmail());
                    securityProfilesService.createDoctorProfile(createDoctorDTO, user.getId());
                    break;
                case Laboratory:
                    var createLaboratoryDTO = new CreateLaboratoryDTO("defaultName", "defaultAddress", "defaultPhone", user.getEmail());
                    securityProfilesService.createLaboratoryProfile(createLaboratoryDTO, user.getId());
                    break;
                case Patient:
                    var createPatientDTO = new CreatePatientDTO("defaultFirstName", "defaultLastName", 18, "defaultAddress", "defaultPhone", user.getEmail());
                    securityProfilesService.createPatientProfile(createPatientDTO, user.getId());
                    break;

            }
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
