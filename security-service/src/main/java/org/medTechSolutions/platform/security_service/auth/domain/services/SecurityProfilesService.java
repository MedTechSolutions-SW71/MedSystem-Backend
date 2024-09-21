package org.medTechSolutions.platform.security_service.auth.domain.services;

import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.createDoctorDTO;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.createLaboratoryDTO;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.createPatientDTO;

import java.util.Optional;

public interface SecurityProfilesService {

    Optional<createDoctorDTO> createDoctorProfile(createDoctorDTO createDoctorDTO, Long doctorId);
    Optional<createLaboratoryDTO> createLaboratoryProfile(createLaboratoryDTO createLaboratoryDTO, Long laboratoryId);
    Optional<createPatientDTO> createPatientProfile(createPatientDTO createPatientDTO, Long patientId);

    // delete doctor profile
    void deleteDoctorProfile(Long doctorId);

    // delete laboratory profile
    void deleteLaboratoryProfile(Long laboratoryId);

    // delete patient profile
    void deletePatientProfile(Long patientId);
}
