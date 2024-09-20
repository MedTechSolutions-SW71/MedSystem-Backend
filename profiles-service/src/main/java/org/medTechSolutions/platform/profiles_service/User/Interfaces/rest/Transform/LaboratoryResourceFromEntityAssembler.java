package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Laboratory;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.LaboratoryResource;

public class LaboratoryResourceFromEntityAssembler {
    public static LaboratoryResource toResourceFromEntity(Laboratory entity) {
        return new LaboratoryResource(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getEmail()
        );
    }
}