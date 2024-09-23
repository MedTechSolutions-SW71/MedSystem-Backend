package org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.DeleteAppointmentCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Queries.GetAllAppointmentsByDoctorIdQuery;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Queries.GetAllAppointmentsByPatientIdQuery;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Queries.GetAllAppointmentsQuery;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Queries.GetAppointmentByIdQuery;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Services.AppointmentCommandService;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Services.AppointmentQueryService;
import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Resources.AppointmentResource;
import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Resources.CreateAppointmentResource;
import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Resources.UpdateAppointmentDateResource;
import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Resources.UpdateAppointmentReasonResource;
import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Transform.AppointmentResourceFromEntityAssembler;
import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Transform.CreateAppointmentCommandFromResourceAssembler;
import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Transform.UpdateAppointmentDateCommandFromResourceAssembler;
import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Transform.UpdateAppointmentReasonCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Appointments", description = "Endpoints for managing appointments")
public class AppointmentsController {
    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;

    public AppointmentsController(AppointmentCommandService appointmentCommandService, AppointmentQueryService appointmentQueryService) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }

    //Get all appointments
    @GetMapping
    public ResponseEntity<List<AppointmentResource>> getAllAppointments(){
        var getAllAppointmentsQuery = new GetAllAppointmentsQuery();
        var appointments = appointmentQueryService.handle(getAllAppointmentsQuery);
        var appointmentResources = appointments.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(appointmentResources);
    }

    //Get appointment by id
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long id) {
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(id);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty())
            return ResponseEntity.notFound().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    //Get all appointments by patient id
    @GetMapping("/patientId/{patientId}")
    public ResponseEntity<List<AppointmentResource>> getAllAppointmentByPatientId(@PathVariable Long patientId) {
        var getAllAppointmentsByPatientIdQuery = new GetAllAppointmentsByPatientIdQuery(patientId);
        var appointments = appointmentQueryService.handle(getAllAppointmentsByPatientIdQuery);
        var appointmentsResources = appointments.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(appointmentsResources);
    }

    //Get all appointments by doctor id
    @GetMapping("/doctorId/{doctorId}")
    public ResponseEntity<List<AppointmentResource>> getAllAppointmentByDoctorId(@PathVariable Long doctorId) {
        var getAllAppointmentsByDoctorQuery = new GetAllAppointmentsByDoctorIdQuery(doctorId);
        var appointments = appointmentQueryService.handle(getAllAppointmentsByDoctorQuery);
        var appointmentsResources = appointments.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(appointmentsResources);
    }

    //Create appointment
    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        var createAppointmentCommand = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var appointment = appointmentCommandService.handle(createAppointmentCommand);
        if (appointment.isEmpty()) return ResponseEntity.badRequest().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    //Update appointment reason
    @PutMapping ("/{appointmentId}/reason")
    public ResponseEntity<AppointmentResource> updateAppointmentReason(@PathVariable Long appointmentId, @RequestBody UpdateAppointmentReasonResource updateAppointmentReasonResource) {
        var updateAppointmentReasonCommand = UpdateAppointmentReasonCommandFromResourceAssembler.toCommandFromResource(appointmentId, updateAppointmentReasonResource);
        var updatedAppointment = appointmentCommandService.handle(updateAppointmentReasonCommand);
        if (updatedAppointment.isEmpty()) return ResponseEntity.badRequest().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(updatedAppointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    //Update appointment date
    @PutMapping ("/{appointmentId}/date")
    public ResponseEntity<AppointmentResource> updateAppointmentDate(@PathVariable Long appointmentId, @RequestBody UpdateAppointmentDateResource updateAppointmentDateResource) {
        var updateAppointmentDateCommand = UpdateAppointmentDateCommandFromResourceAssembler.toCommandFromResource(appointmentId, updateAppointmentDateResource);
        var updatedAppointment = appointmentCommandService.handle(updateAppointmentDateCommand);
        if (updatedAppointment.isEmpty()) return ResponseEntity.badRequest().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(updatedAppointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    //Delete appointment
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId) {
        var deleteAppointmentCommand = new DeleteAppointmentCommand(appointmentId);
        appointmentCommandService.handle(deleteAppointmentCommand);
        return ResponseEntity.ok("Appointment with given id successfully deleted");
    }
}
