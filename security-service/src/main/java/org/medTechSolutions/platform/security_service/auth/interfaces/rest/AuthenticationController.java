package org.medTechSolutions.platform.security_service.auth.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.medTechSolutions.platform.security_service.auth.domain.model.commands.SignUpCommand;
import org.medTechSolutions.platform.security_service.auth.domain.model.entities.Role;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetRoleByNameQuery;
import org.medTechSolutions.platform.security_service.auth.domain.model.valueobjects.Roles;
import org.medTechSolutions.platform.security_service.auth.domain.services.RoleQueryService;
import org.medTechSolutions.platform.security_service.auth.domain.services.SecurityProfilesService;
import org.medTechSolutions.platform.security_service.auth.domain.services.UserCommandService;
import org.medTechSolutions.platform.security_service.auth.infrastructure.clients.ProfileClientRest;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.AuthenticatedUserResource;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.SignInResource;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.SignUpResource;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.UserResource;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication API")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final UserCommandService userCommandService;
    private final RoleQueryService roleQueryService;

    @Autowired
    private ProfileClientRest profileClientRest;

    public AuthenticationController(UserCommandService userCommandService, RoleQueryService roleQueryService) {
        this.userCommandService = userCommandService;
        this.roleQueryService = roleQueryService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);

        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        // Transformar SignUpResource a SignUpCommand
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);

        // Crear usuario en el security-service
        var user = userCommandService.handle(signUpCommand);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var userEntity = user.get();
        Long userId = userEntity.getId();  // Obtener el ID generado

        // Crear el UserResource con ID, email y rol
        UserResource userResource = new UserResource(userId, userEntity.getEmail(), signUpCommand.role().getName().name());

        // Llamar al profiles-service dependiendo del rol
        if (signUpResource.role().equalsIgnoreCase("Doctor")) {
            profileClientRest.createDoctor(userResource);  // Llamada a /api/v1/doctors
        } else if (signUpResource.role().equalsIgnoreCase("Patient")) {
            profileClientRest.createPatient(userResource);  // Llamada a /api/v1/patients
        }

        // Retornar UserResource como respuesta al cliente
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

}