package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Doctor;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.DoctorResource;

public class DoctorResourceFromEntityAssembler {
    public static DoctorResource toResourceFromEntity(Doctor entity) {
        return new DoctorResource(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getSpecialization(),
                entity.getLicenceNumber(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getIdLaboratory().getId()
        );
    }
}
