package org.medTechSolutions.platform.security_service.auth.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.medTechSolutions.platform.security_service.auth.domain.model.queries.GetAllRolesQuery;
import org.medTechSolutions.platform.security_service.auth.domain.model.valueobjects.Roles;
import org.medTechSolutions.platform.security_service.auth.domain.services.RoleQueryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value="/api/v1/roles", produces= MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roles", description = "Roles API")
@CrossOrigin(origins = "*")
public class RolesController {
    private final RoleQueryService roleQueryService;

    public RolesController(RoleQueryService roleQueryService) {
        this.roleQueryService = roleQueryService;
    }

    @GetMapping
    public ResponseEntity<List<Roles>> getAllRoles() {
        var getAllRolesQuery = new GetAllRolesQuery();
        var roles = roleQueryService.handle(getAllRolesQuery);
        var rolesResource = roles.stream().map(role -> Roles.valueOf(role.getName().toString())).toList();
        return ResponseEntity.ok(rolesResource);
    }
    

}
