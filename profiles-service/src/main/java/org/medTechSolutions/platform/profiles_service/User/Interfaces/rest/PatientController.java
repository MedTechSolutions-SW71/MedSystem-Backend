package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeletePatientCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetAllPatientQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetPatientByIdQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.EmailClient;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.PatientCommandService;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.PatientQueryService;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.CreatePatientResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.PatientResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.UpdatePatientResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.CreatePatientCommandFromResourceAssembler;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.PatientResourceFromEntityAssembler;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.UpdatePatientCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/patients", produces = "application/json")
@Tag(name = "Patient", description = "Patient Management Endpoints")
public class PatientController {

    private final PatientCommandService patientCommandService;
    private final PatientQueryService patientQueryService;
    private final EmailClient emailClient;

    public PatientController(PatientCommandService patientCommandService, PatientQueryService patientQueryService, EmailClient emailClient) {
        this.patientCommandService = patientCommandService;
        this.patientQueryService = patientQueryService;
        this.emailClient = emailClient;
    }

    @PostMapping
    public ResponseEntity<PatientResource> createPatient(@RequestBody CreatePatientResource createPatientResource) {
        var createPatientCommand = CreatePatientCommandFromResourceAssembler.toCommandFromResource(createPatientResource);
        var patientId = patientCommandService.handle(createPatientCommand);
        if (patientId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getPatientByIdQuery = new GetPatientByIdQuery(patientId);
        var patient = patientQueryService.handle(getPatientByIdQuery);
        if (patient.isEmpty()) return ResponseEntity.badRequest().build();
        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(patient.get());

        String body = "Hola!!, Bienvenido a MedSystem, te registraste con este correo " + " " + patient.get().getEmail() + ", te has registrado como paciente";
        emailClient.sendEmail(patient.get().getEmail(), "Bienvenido a MedSystem", body);

        return new ResponseEntity<>(patientResource, HttpStatus.CREATED);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResource> getPatientById(@PathVariable Long patientId) {
        var getPatientByIdQuery = new GetPatientByIdQuery(patientId);
        var patient = patientQueryService.handle(getPatientByIdQuery);
        if (patient.isEmpty()) return ResponseEntity.badRequest().build();
        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(patient.get());
        return ResponseEntity.ok(patientResource);
    }

    @GetMapping
    public ResponseEntity<List<PatientResource>> getAllPatients() {
        var getAllPatientsQuery = new GetAllPatientQuery();
        var patients = patientQueryService.handle(getAllPatientsQuery);
        var patientResources = patients.stream().map(PatientResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(patientResources);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResource> updatePatient(@PathVariable Long patientId, @RequestBody UpdatePatientResource updatePatientResource) {
        var updatePatientCommand = UpdatePatientCommandFromResourceAssembler.toCommandFromResource(updatePatientResource, patientId);
        var updatedPatient = patientCommandService.handle(updatePatientCommand);
        if (updatedPatient.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var customerResource = PatientResourceFromEntityAssembler.toResourceFromEntity(updatedPatient.get());
        return ResponseEntity.ok(customerResource);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable Long patientId) {
        var deletePatientCommand = new DeletePatientCommand(patientId);
        patientCommandService.handle(deletePatientCommand);
        return ResponseEntity.ok("Patient with given id successfully deleted");
    }
}
