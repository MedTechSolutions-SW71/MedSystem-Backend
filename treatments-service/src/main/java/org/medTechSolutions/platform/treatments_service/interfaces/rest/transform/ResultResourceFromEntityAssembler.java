package org.medTechSolutions.platform.treatments_service.interfaces.rest.transform;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Result;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.ResultResource;

public class ResultResourceFromEntityAssembler {
    public static ResultResource toResourceFromEntity(Result result) {
        return new ResultResource(
                result.getId(),
                result.getDoctorId(),
                result.getPatientId(),
                result.getTypeOfExam(),
                result.getResultDateTime(),
                result.isResult()
        );
    }
}