package org.medTechSolutions.platform.treatments_service.interfaces.rest.transform;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Treatment;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.CreateTreatmentResource;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.TreatmentResource;

public class TreatmentResourceAssembler {

    public static TreatmentResource toResource(Treatment treatment) {
        return new TreatmentResource(
                treatment.getId(),
                treatment.getPatientId(),
                treatment.getDoctorId(),
                treatment.getDescription(),
                treatment.getExamResultIds(),
                treatment.isCompleted()
        );
    }

    public static TreatmentResource toResource(Long treatmentId, CreateTreatmentResource createTreatmentResource) {
        return new TreatmentResource(
                treatmentId,
                createTreatmentResource.patientId(), // Usa 'patientId()' sin 'get'
                createTreatmentResource.doctorId(),  // Usa 'doctorId()' sin 'get'
                createTreatmentResource.description(), // Usa 'description()' sin 'get'
                createTreatmentResource.examResultIds(), // Usa 'examResultIds()' sin 'get'
                false  // Asumimos que un tratamiento nuevo no está completado
        );
    }
}