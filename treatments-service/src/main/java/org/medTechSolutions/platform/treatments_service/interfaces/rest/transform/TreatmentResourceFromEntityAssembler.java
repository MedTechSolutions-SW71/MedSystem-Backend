package org.medTechSolutions.platform.treatments_service.interfaces.rest.transform;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Treatment;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.CreateTreatmentResource;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.TreatmentResource;


public class TreatmentResourceFromEntityAssembler {
    public static TreatmentResource toResourceFromEntity(Treatment entity) {
        return new TreatmentResource(
                entity.getId(),
                entity.getTreatmentName(),
                entity.getDescription(),
                entity.getPeriod(),
                entity.getPatientId(),
                entity.getDoctorId()
        );
    }
}