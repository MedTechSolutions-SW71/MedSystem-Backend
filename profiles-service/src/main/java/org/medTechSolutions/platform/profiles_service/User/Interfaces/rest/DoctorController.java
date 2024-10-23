package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeleteDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetAllDoctorQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetDoctorByIdQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.DoctorCommandService;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.DoctorQueryService;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.EmailClient;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.CreateDoctorResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.DoctorResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.UpdateDoctorResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.CreateDoctorCommandFromResourceAssembler;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.DoctorResourceFromEntityAssembler;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.UpdateDoctorCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/doctors", produces = "application/json")
@Tag(name = "Doctors", description = "Doctor Management Endpoints")
public class DoctorController {

    private final DoctorCommandService doctorCommandService;
    private final DoctorQueryService doctorQueryService;
    private final EmailClient emailClient;

    public DoctorController(DoctorCommandService doctorCommandService, DoctorQueryService doctorQueryService, EmailClient emailClient) {
        this.doctorCommandService = doctorCommandService;
        this.doctorQueryService = doctorQueryService;
        this.emailClient = emailClient;
    }

    @PostMapping
    public ResponseEntity<DoctorResource> createDoctor(@RequestBody CreateDoctorResource createDoctorResource) {
        var createDoctorCommand = CreateDoctorCommandFromResourceAssembler.toCommandFromResource(createDoctorResource);
        var doctorId = doctorCommandService.handle(createDoctorCommand);
        if (doctorId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getDoctorByIdQuery = new GetDoctorByIdQuery(doctorId);
        var doctor = doctorQueryService.handle(getDoctorByIdQuery);
        if (doctor.isEmpty()) return ResponseEntity.badRequest().build();
        var doctorResource = DoctorResourceFromEntityAssembler.toResourceFromEntity(doctor.get());

        String body = "Hola!!, Bienvenido a MedSystem, te registraste con este correo " + " " + doctor.get().getEmail() + ", te has registrado como doctor";
        emailClient.sendEmail(doctor.get().getEmail(), "Bienvenido a MedSystem", body);

        return new ResponseEntity<>(doctorResource, HttpStatus.CREATED);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorResource> getDoctorById(@PathVariable Long doctorId) {
        var getDoctorByIdQuery = new GetDoctorByIdQuery(doctorId);
        var doctor = doctorQueryService.handle(getDoctorByIdQuery);
        if (doctor.isEmpty()) return ResponseEntity.badRequest().build();
        var doctorResource = DoctorResourceFromEntityAssembler.toResourceFromEntity(doctor.get());
        return ResponseEntity.ok(doctorResource);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResource>> getAllDoctor() {
        var getAllDoctorQuery = new GetAllDoctorQuery();
        var doctors = doctorQueryService.handle(getAllDoctorQuery);
        var doctorResources = doctors.stream().map(DoctorResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(doctorResources);
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorResource> updateDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource updateDoctorResource) {
        var updateDoctorCommand = UpdateDoctorCommandFromResourceAssembler.toCommandFromResource(updateDoctorResource, doctorId);
        var updatedDoctor = doctorCommandService.handle(updateDoctorCommand);
        if (updatedDoctor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var doctorResource = DoctorResourceFromEntityAssembler.toResourceFromEntity(updatedDoctor.get());
        return ResponseEntity.ok(doctorResource);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long doctorId) {
        var deleteDoctorCommand = new DeleteDoctorCommand(doctorId);
        doctorCommandService.handle(deleteDoctorCommand);
        return ResponseEntity.ok("Doctor with given id successfully deleted");
    }
}
