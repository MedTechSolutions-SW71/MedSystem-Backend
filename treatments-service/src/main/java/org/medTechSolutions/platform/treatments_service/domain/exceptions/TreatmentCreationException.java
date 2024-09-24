package org.medTechSolutions.platform.treatments_service.domain.exceptions;

public class TreatmentCreationException extends RuntimeException {
    public TreatmentCreationException(String message) {
        super(message);
    }
}
