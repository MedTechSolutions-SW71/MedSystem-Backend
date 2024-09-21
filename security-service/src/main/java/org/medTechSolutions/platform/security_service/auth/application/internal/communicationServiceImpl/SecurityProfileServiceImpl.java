package org.medTechSolutions.platform.security_service.auth.application.internal.communicationServiceImpl;

import jakarta.transaction.Transactional;
import org.medTechSolutions.platform.security_service.auth.domain.model.aggregates.User;
import org.medTechSolutions.platform.security_service.auth.domain.model.entities.SecurityProfiles;
import org.medTechSolutions.platform.security_service.auth.domain.services.SecurityProfilesService;
import org.medTechSolutions.platform.security_service.auth.infrastructure.clients.ProfileClientRest;
import org.medTechSolutions.platform.security_service.auth.infrastructure.persistence.jpa.repositories.UserRepository;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.*;
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
    public Optional<CreateDoctorDTO> createDoctorProfile(CreateDoctorDTO createDoctorDTO, Long doctorId) {
        var userProfile = userRepository.findById(doctorId);

        if (userProfile.isPresent() && createDoctorDTO != null) {
            try {
                DoctorDTO doctorDTO = profileClientRest.createDoctor(createDoctorDTO);

                if (doctorDTO == null || doctorDTO.doctorId() == null) {
                    throw new IllegalStateException("El servicio de creación de doctor no devolvió un ID.");
                }

                User user = userProfile.get();
                SecurityProfiles securityProfiles = ensureSecurityProfileExists(user);
                securityProfiles.setDoctorId(doctorDTO.doctorId());
                userRepository.save(user);

                return Optional.of(createDoctorDTO);
            } catch (Exception e) {
                throw new RuntimeException("Error al crear el perfil de doctor: " + e.getMessage(), e);
            }
        }
        return Optional.empty();
    }


    @Override
    @Transactional
    public Optional<CreateLaboratoryDTO> createLaboratoryProfile(CreateLaboratoryDTO createLaboratoryDTO, Long laboratoryId) {
        return userRepository.findById(laboratoryId)
                .map(user -> {
                    LaboratoryDTO laboratoryDTO = profileClientRest.createLaboratory(createLaboratoryDTO);
                    SecurityProfiles securityProfiles = ensureSecurityProfileExists(user);
                    if (securityProfiles.getLaboratoryId() != null) {
                        throw new IllegalArgumentException("Este usuario ya tiene un perfil de laboratorio.");
                    }
                    securityProfiles.setLaboratoryId(laboratoryDTO.laboratoryId());
                    userRepository.save(user);
                    return createLaboratoryDTO;
                });
    }

    @Override
    @Transactional
    public Optional<CreatePatientDTO> createPatientProfile(CreatePatientDTO createPatientDTO, Long patientId) {
        return userRepository.findById(patientId)
                .map(user -> {
                    PatientDTO patientDTO = profileClientRest.createPatient(createPatientDTO);
                    SecurityProfiles securityProfiles = ensureSecurityProfileExists(user);
                    if (securityProfiles.getPatientId() != null) {
                        throw new IllegalArgumentException("Este usuario ya tiene un perfil de paciente.");
                    }
                    securityProfiles.setPatientId(patientDTO.patientId());
                    userRepository.save(user);
                    return createPatientDTO;
                });
    }

    @Override
    @Transactional
    public void deleteDoctorProfile(Long doctorId) {
        userRepository.findById(doctorId).ifPresent(user -> {
            SecurityProfiles securityProfiles = user.getSecurityProfiles();
            if (securityProfiles != null) {
                securityProfiles.setDoctorId(null);
                profileClientRest.deleteDoctor(doctorId);
                userRepository.save(user);
            }
        });
    }

    @Override
    @Transactional
    public void deleteLaboratoryProfile(Long laboratoryId) {
        userRepository.findById(laboratoryId).ifPresent(user -> {
            SecurityProfiles securityProfiles = user.getSecurityProfiles();
            if (securityProfiles != null) {
                securityProfiles.setLaboratoryId(null);
                profileClientRest.deleteLaboratory(laboratoryId);
                userRepository.save(user);
            }
        });
    }

    @Override
    @Transactional
    public void deletePatientProfile(Long patientId) {
        userRepository.findById(patientId).ifPresent(user -> {
            SecurityProfiles securityProfiles = user.getSecurityProfiles();
            if (securityProfiles != null) {
                securityProfiles.setPatientId(null);
                profileClientRest.deletePatient(patientId);
                userRepository.save(user);
            }
        });
    }
}
