package org.medTechSolutions.platform.security_service.auth.application.internal.communicationServiceImpl;
/*
import jakarta.transaction.Transactional;
import org.medTechSolutions.platform.security_service.auth.domain.model.aggregates.User;
import org.medTechSolutions.platform.security_service.auth.domain.model.entities.SecurityProfiles;
import org.medTechSolutions.platform.security_service.auth.domain.services.SecurityProfilesService;
import org.medTechSolutions.platform.security_service.auth.infrastructure.clients.ProfileClientRest;
import org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories.UserRepository;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.*;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.UserResource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityProfileServiceImpl implements SecurityProfilesService {

    private final ProfileClientRest profileClientRest;
    private final UserRepository userRepository;

    public SecurityProfileServiceImpl(ProfileClientRest profileClientRest, UserRepository userRepository) {
        this.profileClientRest = profileClientRest;
        this.userRepository = userRepository;
    }

    private SecurityProfiles ensureSecurityProfileExists(User user) {
        // Si no hay un perfil de seguridad, lo crea
        SecurityProfiles securityProfiles = user.getSecurityProfiles();
        if (securityProfiles == null) {
            securityProfiles = new SecurityProfiles();
            user.setSecurityProfiles(securityProfiles);
        }
        return securityProfiles;
    }

    @Override
    @Transactional
    public Optional<CreateDoctorDTO> createDoctorProfile(CreateDoctorDTO createDoctorDTO) {
        return userRepository.findById(createDoctorDTO.getUserId())
                .map(user -> {
                    try {
                        UserResource userResource = new UserResource(createDoctorDTO.getUserId(), user.getEmail(), "DOCTOR");
                        DoctorDTO doctorDTO = profileClientRest.createDoctor(userResource);
                        SecurityProfiles securityProfiles = ensureSecurityProfileExists(user);
                        securityProfiles.setDoctorId(doctorDTO.getDoctorId());
                        userRepository.save(user);
                        return createDoctorDTO;
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Este usuario ya tiene un perfil de doctor.");
                    }
                });
    }

    @Override
    @Transactional
    public Optional<CreatePatientDTO> createPatientProfile(CreatePatientDTO createPatientDTO) {
        return userRepository.findById(createPatientDTO.getUserId())
                .map(user -> {
                    try {
                        UserResource userResource = new UserResource(createPatientDTO.getUserId(), user.getEmail(), "PATIENT");
                        PatientDTO patientDTO = profileClientRest.createPatient(userResource);
                        SecurityProfiles securityProfiles = ensureSecurityProfileExists(user);
                        securityProfiles.setPatientId(patientDTO.getPatientId());
                        userRepository.save(user);
                        return createPatientDTO;
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Este usuario ya tiene un perfil de paciente.");
                    }
                });
    }
}*/