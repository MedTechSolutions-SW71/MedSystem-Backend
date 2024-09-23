package org.medTechSolutions.platform.appointments_service.Appointments.Application.QueryServices;

import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Aggregates.Appointment;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Queries.*;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Services.AppointmentQueryService;
import org.medTechSolutions.platform.appointments_service.Appointments.Infrastructure.persistance.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentsByIdQuery query) {
        return appointmentRepository.findById(query.id());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByDoctorIdQuery query) {
        return appointmentRepository.findAllByDoctorId(query.doctorId());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByPatientIdQuery query) {
        return appointmentRepository.findAllByPatientId(query.patientId());
    }

    @Override
    public List<Appointment> handle(GetAppointmentsByDateQuery query) {
        return appointmentRepository.findAllByDate(query.date());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query) {
        return appointmentRepository.findAll();
    }
}
