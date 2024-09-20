package org.medTechSolutions.platform.profiles_service.User.Application.internal.queryServices;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Laboratory;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetAllLaboratoriesQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetLaboratoryByIdQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.LaboratoryQueryService;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.LaboratoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratoryQueryServiceImpl implements LaboratoryQueryService {

    private final LaboratoryRepository laboratoryRepository;

    public LaboratoryQueryServiceImpl(LaboratoryRepository laboratoryRepository) {
        this.laboratoryRepository = laboratoryRepository;
    }

    @Override
    public Optional<Laboratory> handle(GetLaboratoryByIdQuery query) {
        return laboratoryRepository.findById(query.laboratoryId());
    }

    @Override
    public List<Laboratory> handle(GetAllLaboratoriesQuery query) {
        return laboratoryRepository.findAll();
    }
}