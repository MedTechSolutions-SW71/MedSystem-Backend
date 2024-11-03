package org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByDoctorId(Long doctorId);
    List<Result> findByPatientId(Long patientId);
}