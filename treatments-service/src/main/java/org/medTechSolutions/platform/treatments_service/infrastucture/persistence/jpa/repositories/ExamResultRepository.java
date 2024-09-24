package org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
}