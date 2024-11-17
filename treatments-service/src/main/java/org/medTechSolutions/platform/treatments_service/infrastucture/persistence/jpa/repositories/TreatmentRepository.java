package org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    boolean existsByTreatmentNameAndPatientId(String treatmentName, Long patientId);
    List<Treatment> findByPatientId(Long patientId);
    List<Treatment> findByDoctorId(Long doctorId);
    Optional<Treatment> findByTreatmentName(String treatmentName);
}