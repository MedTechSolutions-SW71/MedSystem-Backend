package org.medTechSolutions.platform.appointments_service.Appointments.Application.CommandServices;

import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.SeedSpecialtiesCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Entities.Specialty;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.ValueObjects.Specialties;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Services.SpecialtyCommandService;
import org.medTechSolutions.platform.appointments_service.Appointments.Infrastructure.persistance.jpa.repositories.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SpecialtyCommandServiceImpl implements SpecialtyCommandService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyCommandServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public void handle(SeedSpecialtiesCommand command) {
        Arrays.stream(Specialties.values()).forEach(specialty -> {
            if (!specialtyRepository.existsByName(specialty)) {
                specialtyRepository.save(new Specialty(specialty));
            }
        });
    }
}
