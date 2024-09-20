package org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}