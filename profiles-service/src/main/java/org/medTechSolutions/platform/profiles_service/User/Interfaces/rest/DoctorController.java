package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeleteDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetAllDoctorQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetDoctorByIdQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetDoctorBySpecialityQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.ValueObjects.Specialities;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.DoctorCommandService;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.DoctorQueryService;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.EmailClient;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.CreateDoctorResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.DoctorResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.UpdateDoctorResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.CreateDoctorCommandFromResourceAssembler;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.DoctorResourceFromEntityAssembler;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.UpdateDoctorCommandFromResourceAssembler;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.clientsDTO.UserResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/doctors", produces = "application/json")
@Tag(name = "Doctors", description = "Doctor Management Endpoints")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<UserResource> createDoctor(@RequestBody UserResource userResource) {
        // Crear el comando con los datos básicos
        CreateDoctorCommand createDoctorCommand = new CreateDoctorCommand(
                userResource.id(),
                userResource.email(),
                userResource.role()
        );

        // Llamar al servicio de comandos para manejar la creación del perfil
        Long doctorId = doctorCommandService.handle(createDoctorCommand);
        if (doctorId == null) {
            return ResponseEntity.badRequest().build();
        }

        // No es necesario hacer otra llamada a obtener el doctor
        // Aquí solo se envía el correo de bienvenida
        String body = "Hola!!, Bienvenido a MedSystem, te registraste con este correo " + userResource.email() + ", te has registrado como doctor";
        emailClient.sendEmail(userResource.email(), "Bienvenido a MedSystem", body);

        // Retornar el UserResource ya que el perfil básico se ha creado
        return ResponseEntity.status(HttpStatus.CREATED).body(userResource);
    }



    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorResource> getDoctorById(@PathVariable Long doctorId) {
        var getDoctorByIdQuery = new GetDoctorByIdQuery(doctorId);
        var doctor = doctorQueryService.handle(getDoctorByIdQuery);
        if (doctor.isEmpty()) return ResponseEntity.badRequest().build();
        var doctorResource = DoctorResourceFromEntityAssembler.toResourceFromEntity(doctor.get());
        return ResponseEntity.ok(doctorResource);
    }

    @GetMapping("/speciality/{speciality}")
    public ResponseEntity<List<DoctorResource>> getDoctorBySpeciality(@PathVariable String speciality) {
        try {
            // Convertir la especialidad a Enum y manejar posibles errores de conversión
            Specialities specialityEnum = Specialities.valueOf(speciality.toUpperCase());
            var getDoctorBySpecialityQuery = new GetDoctorBySpecialityQuery(specialityEnum);

            // Obtener los doctores y mapearlos a recursos
            var doctors = doctorQueryService.handle(getDoctorBySpecialityQuery);
            var doctorResources = doctors.stream()
                    .map(DoctorResourceFromEntityAssembler::toResourceFromEntity)
                    .toList();

            return ResponseEntity.ok(doctorResources);
        } catch (IllegalArgumentException e) {
            // Devolver un mensaje de error en caso de que la especialidad no sea válida
            return ResponseEntity.badRequest().build();
        }
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
        // Crear el comando para actualizar el perfil con los nuevos datos
        var updateDoctorCommand = new UpdateDoctorCommand(
                doctorId,
                updateDoctorResource.firstName(),
                updateDoctorResource.lastName(),
                updateDoctorResource.licenceNumber(),
                updateDoctorResource.specialities(),
                updateDoctorResource.phone()
        );

        // Ejecutar el comando para actualizar el perfil de Doctor
        var updatedDoctor = doctorCommandService.handle(updateDoctorCommand);
        if (updatedDoctor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Convertir la entidad actualizada a un recurso y retornarla
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
