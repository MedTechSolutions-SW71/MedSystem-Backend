package org.medTechSolutions.platform.profiles_service.User.Application.internal.commandServices;


import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Doctor;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeleteDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.DoctorCommandService;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.DoctorRepository;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DoctorCommandServiceImpl implements DoctorCommandService {

    private final DoctorRepository doctorRepository;
    private final LaboratoryRepository laboratoryRepository;

    @Autowired
    public DoctorCommandServiceImpl(DoctorRepository doctorRepository, LaboratoryRepository laboratoryRepository) {
        this.doctorRepository = doctorRepository;
        this.laboratoryRepository = laboratoryRepository;
    }

    @Override
    public Long handle(CreateDoctorCommand command) {

        //var laboratoryResult = laboratoryRepository.findById(command.idLaboratory());
        //if (laboratoryResult.isEmpty()) throw new UserNotFoundException(command.idLaboratory());
        //var laboratory = laboratoryResult.get();

        var licenceNumber = command.licenceNumber();

        Doctor doctor = new Doctor(command);

        if (doctorRepository.existsDoctorByLicenceNumber(licenceNumber)) {
            throw new IllegalArgumentException("Doctor with licence number " + licenceNumber + " already exists");
        }

        try {
            doctorRepository.save(doctor);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving doctor: " + e.getMessage());
        }
        return doctor.getId();
    }

    @Override
    public Optional<Doctor> handle(UpdateDoctorCommand command) {
        var result = doctorRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Doctor does not exist");
        var doctorToUpdate = result.get();

        //var laboratoryResult = laboratoryRepository.findById(command.idLaboratory());
        //if (laboratoryResult.isEmpty()) throw new UserNotFoundException(command.idLaboratory());
        //var laboratory = laboratoryResult.get();

        try {
            var updatedDoctor = doctorRepository.save(doctorToUpdate.update(
                    command.firstName(),
                    command.lastName(),
                    command.licenceNumber(),
                    command.specialities(),
                    command.phone(),
                    command.email()
                    //laboratory
            ));


            return Optional.of(updatedDoctor);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating doctor: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteDoctorCommand command) {
        if (!doctorRepository.existsById(command.doctorId())) {
            throw new IllegalArgumentException("Doctor does not exist");
        }
        try {
            doctorRepository.deleteById(command.doctorId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting doctor: " + e.getMessage());
        }
    }
}
