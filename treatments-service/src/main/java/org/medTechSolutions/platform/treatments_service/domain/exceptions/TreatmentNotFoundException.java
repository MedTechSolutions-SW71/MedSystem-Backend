package org.medTechSolutions.platform.treatments_service.domain.exceptions;

public class TreatmentNotFoundException extends RuntimeException {
    public TreatmentNotFoundException(Long treatmentId) {
        super("Treatment with ID " + treatmentId + " not found");
    }
}
