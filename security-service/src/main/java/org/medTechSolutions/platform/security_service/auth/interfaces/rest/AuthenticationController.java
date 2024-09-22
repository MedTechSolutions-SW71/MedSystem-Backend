package org.medTechSolutions.platform.security_service.auth.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.medTechSolutions.platform.security_service.auth.domain.model.aggregates.User;
import org.medTechSolutions.platform.security_service.auth.domain.services.RoleQueryService;
import org.medTechSolutions.platform.security_service.auth.domain.services.SecurityProfilesService;
import org.medTechSolutions.platform.security_service.auth.domain.services.UserCommandService;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.CreateDoctorDTO;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.CreateLaboratoryDTO;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.CreatePatientDTO;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.AuthenticatedUserResource;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.SignInResource;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.SignUpResource;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.UserResource;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {
    private final UserCommandService userCommandService;
    private final SecurityProfilesService securityProfilesService;
    private final RoleQueryService roleQueryService;

    public AuthenticationController(UserCommandService userCommandService, SecurityProfilesService securityProfilesService, RoleQueryService roleQueryService) {
        this.userCommandService = userCommandService;
        this.securityProfilesService = securityProfilesService;
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
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var userEntity = user.get();
        var userResource = UserResourceFromEntityAssembler.toUserResourceFromEntity(userEntity);
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

}