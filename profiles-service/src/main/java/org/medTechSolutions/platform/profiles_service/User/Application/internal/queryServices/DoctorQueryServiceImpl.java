package org.medTechSolutions.platform.profiles_service.User.Application.internal.queryServices;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Doctor;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetAllDoctorQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetDoctorByIdQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.DoctorQueryService;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorQueryServiceImpl implements DoctorQueryService {

    private final DoctorRepository doctorRepository;

    public DoctorQueryServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Optional<Doctor> handle(GetDoctorByIdQuery query) {
        return doctorRepository.findById(query.doctorId());
    }

    @Override
    public List<Doctor> handle(GetAllDoctorQuery query) {
        return doctorRepository.findAll();
    }
}
