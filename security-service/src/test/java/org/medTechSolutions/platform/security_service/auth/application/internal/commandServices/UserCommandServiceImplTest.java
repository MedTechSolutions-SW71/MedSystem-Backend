package org.medTechSolutions.platform.security_service.auth.application.internal.commandServices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.medTechSolutions.platform.security_service.auth.application.internal.outboundservices.hashing.HashingService;
import org.medTechSolutions.platform.security_service.auth.application.internal.outboundservices.tokens.TokenService;
import org.medTechSolutions.platform.security_service.auth.domain.model.aggregates.User;
import org.medTechSolutions.platform.security_service.auth.domain.model.commands.SignInCommand;
import org.medTechSolutions.platform.security_service.auth.domain.model.commands.SignUpCommand;
import org.medTechSolutions.platform.security_service.auth.domain.model.entities.Role;
import org.medTechSolutions.platform.security_service.auth.domain.model.valueobjects.Roles;
import org.medTechSolutions.platform.security_service.auth.domain.services.SecurityProfilesService;
import org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories.UserRepository;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserCommandServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private HashingService hashingService;
    @Mock
    private SecurityProfilesService securityProfilesService;
    @Mock
    private TokenService tokenService;
    @InjectMocks
    private UserCommandServiceImpl userCommandService;

    @BeforeEach
    void setUp() {
        lenient().when(roleRepository.findByName(any())).thenReturn(Optional.of(new Role(Roles.Patient)));
        lenient().when(hashingService.encode(anyString())).thenReturn("encodedPassword");
    }

    @Test
    void testSignInSuccess() {
        // Setup
        User user = new User("user@example.com", "encodedPassword");
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(hashingService.matches("password", "encodedPassword")).thenReturn(true);
        when(tokenService.generateToken("user@example.com")).thenReturn("token");

        // Execute
        SignInCommand command = new SignInCommand("user@example.com", "password");
        Optional<ImmutablePair<User, String>> result = userCommandService.handle(command);

        // Verify
        assertTrue(result.isPresent());
        assertEquals("user@example.com", result.get().getLeft().getEmail());
        assertEquals("token", result.get().getRight());
    }

    @Test
    void testSignInFailure_UserNotFound() {
        // Setup
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.empty());

        // Execute
        SignInCommand command = new SignInCommand("user@example.com", "password");

        // Verify
        Exception exception = assertThrows(RuntimeException.class, () -> userCommandService.handle(command));
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testSignInFailure_InvalidPassword() {
        // Setup
        User user = new User("user@example.com", "encodedPassword");
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(hashingService.matches("password", "encodedPassword")).thenReturn(false);

        // Execute
        SignInCommand command = new SignInCommand("user@example.com", "password");

        // Verify
        Exception exception = assertThrows(RuntimeException.class, () -> userCommandService.handle(command));
        assertEquals("Invalid password", exception.getMessage());
    }


    @Test
    void testSignUpFailure_EmailExists() {
        // Setup specific to this test
        when(userRepository.existsByEmail("user@example.com")).thenReturn(true);

        // Execute
        SignUpCommand command = new SignUpCommand("user@example.com", "password", new ArrayList<>());

        // Verify
        Exception exception = assertThrows(RuntimeException.class, () -> userCommandService.handle(command));
        assertEquals("Username already exists", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}