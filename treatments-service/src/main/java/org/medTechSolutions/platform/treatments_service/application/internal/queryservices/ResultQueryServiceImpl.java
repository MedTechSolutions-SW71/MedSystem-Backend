package org.medTechSolutions.platform.treatments_service.application.internal.queryservices;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Result;
import org.medTechSolutions.platform.treatments_service.domain.services.ResultQueryService;
import org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultQueryServiceImpl implements ResultQueryService {
    private final ResultRepository resultRepository;

    public ResultQueryServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public List<Result> getByDoctorId(Long doctorId) {
        return resultRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Result> getByPatientId(Long patientId) {
        return resultRepository.findByPatientId(patientId);
    }
}