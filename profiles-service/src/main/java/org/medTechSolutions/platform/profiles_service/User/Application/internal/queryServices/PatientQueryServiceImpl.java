package org.medTechSolutions.platform.profiles_service.User.Application.internal.queryServices;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Patient;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetAllPatientQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetPatientByIdQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.PatientQueryService;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientQueryServiceImpl implements PatientQueryService {

    private final PatientRepository patientRepository;

    public PatientQueryServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> handle(GetPatientByIdQuery query) {
        return patientRepository.findById(query.patientId());
    }

    @Override
    public List<Patient> handle(GetAllPatientQuery query) {
        return patientRepository.findAll();
    }
}
