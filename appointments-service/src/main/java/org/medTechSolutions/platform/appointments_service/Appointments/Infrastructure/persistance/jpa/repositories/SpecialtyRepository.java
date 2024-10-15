package org.medTechSolutions.platform.appointments_service.Appointments.Infrastructure.persistance.jpa.repositories;

import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Entities.Specialty;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.ValueObjects.Specialties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    boolean existsByName(Specialties name);
    Optional<Specialty> findByName(Specialties name);
}
