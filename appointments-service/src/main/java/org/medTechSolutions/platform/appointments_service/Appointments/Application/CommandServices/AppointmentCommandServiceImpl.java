package org.medTechSolutions.platform.appointments_service.Appointments.Application.CommandServices;

import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Aggregates.Appointment;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.CreateAppointmentCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.DeleteAppointmentCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.UpdateAppointmentDateCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.UpdateAppointmentReasonCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.ValueObjects.Specialties;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Services.AppointmentCommandService;
import org.medTechSolutions.platform.appointments_service.Appointments.Infrastructure.persistance.jpa.repositories.AppointmentRepository;
import org.medTechSolutions.platform.appointments_service.Appointments.Infrastructure.persistance.jpa.repositories.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {
    private final AppointmentRepository appointmentRepository;
    private final SpecialtyRepository specialtyRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository, SpecialtyRepository specialtyRepository) {
        this.appointmentRepository = appointmentRepository;
        this.specialtyRepository = specialtyRepository;
    }

    //Create appointment
    @Override
    public Optional<Appointment> handle(CreateAppointmentCommand command) {
        var specialty = Specialties.valueOf(command.specialty());
        var appointment = new Appointment(command.doctorId(), command.patientId(), command.date(), command.reason(), specialtyRepository.findByName(specialty).get());
        appointmentRepository.save(appointment);
        return Optional.of(appointment);
    }

    //Update appointment reason
    public Optional<Appointment> handle(UpdateAppointmentReasonCommand command) {
        var result = appointmentRepository.findById(command.appointmentId());
        if(result.isEmpty()) throw new IllegalArgumentException("Appointment not found");
        var appointmentToUpdate = result.get();
        try {
            var updatedAppointment = appointmentRepository.save(appointmentToUpdate.updateReason(command.reason()));
            return Optional.of(updatedAppointment);
        }catch (Exception e) {
            throw new IllegalArgumentException("Failed to update appointment reason" + e.getMessage());
        }
    }

    //Update appointment reason
    public Optional<Appointment> handle(UpdateAppointmentDateCommand command) {
        var appointment = appointmentRepository.findById(command.appointmentId()).orElseThrow(() ->
                new IllegalArgumentException("Appointment with id " + command.appointmentId() + " not found"));

        var appointmentDate = command.date();


        appointment.setDate(appointmentDate); // update

        return Optional.of(appointmentRepository.save(appointment));


    }

    //Delete appointment
    @Override
    public void handle(DeleteAppointmentCommand command) {
        var appointment = appointmentRepository.findById(command.appointmentId());
        appointment.ifPresent(appointmentRepository::delete);
    }
}
