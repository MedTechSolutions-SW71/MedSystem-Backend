package org.medTechSolutions.platform.profiles_service.User.Application.internal.commandServices;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Patient;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreatePatientCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeletePatientCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdatePatientCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.PatientCommandService;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientCommandServiceImpl implements PatientCommandService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientCommandServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Long handle(CreatePatientCommand command) {

        Patient patient = new Patient(command);
        try {
            patientRepository.save(patient);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving patient: " + e.getMessage());
        }
        return patient.getUserId();
    }

    @Override
    public Optional<Patient> handle(UpdatePatientCommand command) {
        var result = patientRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Patient does not exist");
        var patientToUpdate = result.get();
        try {
            var updatedPatient = patientRepository.save(patientToUpdate.update(
                    command.firstName(),
                    command.lastName(),
                    command.age(),
                    command.address(),
                    command.phone(),
                    command.email()
            ));
            return Optional.of(updatedPatient);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating patient: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeletePatientCommand command) {
        if (!patientRepository.existsById(command.patientId())) {
            throw new IllegalArgumentException("Patient does not exist");
        }
        try {
            patientRepository.deleteById(command.patientId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting patient: " + e.getMessage());
        }
    }
}
