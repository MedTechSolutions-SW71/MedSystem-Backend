package org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Doctor;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.ValueObjects.Specialities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsDoctorByLicenceNumber(Integer licenceNumber);
    List<Doctor> findBySpecialities(Specialities speciality);
}