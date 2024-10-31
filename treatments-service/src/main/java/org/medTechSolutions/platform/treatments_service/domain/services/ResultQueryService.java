package org.medTechSolutions.platform.treatments_service.domain.services;

import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Result;

import java.util.List;

public interface ResultQueryService {
    List<Result> getByDoctorId(Long doctorId);
    List<Result> getByPatientId(Long patientId);
}