package org.medTechSolutions.platform.profiles_service.User.Application.internal.commandServices;


import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Doctor;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeleteDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.DoctorCommandService;
import org.medTechSolutions.platform.profiles_service.User.Infrastructure.persistence.jpa.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DoctorCommandServiceImpl implements DoctorCommandService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorCommandServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Long handle(CreateDoctorCommand command) {
        // Crear un doctor con los datos básicos usando el constructor
        Doctor doctor = new Doctor(command);  // Usamos el constructor de Doctor para asignar los valores

        // Guardar el perfil en la base de datos
        doctorRepository.save(doctor);

        return doctor.getUserId();
    }

    @Override
    public Optional<Doctor> handle(UpdateDoctorCommand command) {
        // Buscar el doctor por su ID
        var doctorOpt = doctorRepository.findById(command.id());
        if (doctorOpt.isEmpty()) {
            return Optional.empty();
        }

        var doctor = doctorOpt.get();

        // Actualizar los datos del perfil
        doctor.setFirstName(command.firstName());
        doctor.setLastName(command.lastName());
        doctor.setLicenceNumber(command.licenceNumber());
        doctor.setSpecialities(command.specialities());
        doctor.setPhone(command.phone());

        // Guardar los cambios en la base de datos
        doctorRepository.save(doctor);
        return Optional.of(doctor);
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
