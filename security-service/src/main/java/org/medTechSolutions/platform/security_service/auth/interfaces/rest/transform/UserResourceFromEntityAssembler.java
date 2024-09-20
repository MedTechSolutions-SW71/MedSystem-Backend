package org.medTechSolutions.platform.security_service.auth.interfaces.rest.transform;


import org.medTechSolutions.platform.security_service.auth.domain.model.aggregates.User;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toUserResourceFromEntity(User entity) {
        var roles = entity.getRoles().stream().map(role -> role.getStringName()).toList();

        return new UserResource(
            entity.getId(),
            entity.getUsername(),
            roles
        );
    }

}
