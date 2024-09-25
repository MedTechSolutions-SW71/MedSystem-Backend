package org.medTechSolutions.platform.security_service.auth.domain.services;

import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.CreateDoctorDTO;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.CreateLaboratoryDTO;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.CreatePatientDTO;

import java.util.Optional;

public interface SecurityProfilesService {

    Optional<CreateDoctorDTO> createDoctorProfile(CreateDoctorDTO createDoctorDTO, Long doctorId);
    Optional<CreateLaboratoryDTO> createLaboratoryProfile(CreateLaboratoryDTO createLaboratoryDTO, Long laboratoryId);
    Optional<CreatePatientDTO> createPatientProfile(CreatePatientDTO createPatientDTO, Long patientId);

    // delete doctor profile
    void deleteDoctorProfile(Long doctorId);

    // delete laboratory profile
    void deleteLaboratoryProfile(Long laboratoryId);

    // delete patient profile
    void deletePatientProfile(Long patientId);
}
