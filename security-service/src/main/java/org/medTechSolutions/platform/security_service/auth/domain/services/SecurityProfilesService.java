package org.medTechSolutions.platform.security_service.auth.domain.services;

import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.CreateDoctorDTO;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.CreatePatientDTO;

import java.util.Optional;

public interface SecurityProfilesService {

    Optional<CreateDoctorDTO> createDoctorProfile(CreateDoctorDTO createDoctorDTO);
    Optional<CreatePatientDTO> createPatientProfile(CreatePatientDTO createPatientDTO);
}
