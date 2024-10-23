package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.DeleteLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetAllLaboratoriesQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetLaboratoryByIdQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.LaboratoryCommandService;
import org.medTechSolutions.platform.profiles_service.User.Domain.Services.LaboratoryQueryService;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.CreateLaboratoryResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.LaboratoryResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.UpdateLaboratoryResource;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.CreateLaboratoryCommandFromResourceAssembler;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.LaboratoryResourceFromEntityAssembler;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform.UpdateLaboratoryCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/laboratories", produces = "application/json")
@Tag(name = "Laboratories", description = "Laboratory Management Endpoints")
@CrossOrigin(origins = "*")
public class LaboratoryController {

    private final LaboratoryCommandService laboratoryCommandService;
    private final LaboratoryQueryService laboratoryQueryService;

    public LaboratoryController(LaboratoryCommandService laboratoryCommandService, LaboratoryQueryService laboratoryQueryService) {
        this.laboratoryCommandService = laboratoryCommandService;
        this.laboratoryQueryService = laboratoryQueryService;
    }

    @PostMapping
    public ResponseEntity<LaboratoryResource> createLaboratory(@RequestBody CreateLaboratoryResource createLaboratoryResource) {
        var createLaboratoryCommand = CreateLaboratoryCommandFromResourceAssembler.toCommandFromResource(createLaboratoryResource);
        var laboratoryId = laboratoryCommandService.handle(createLaboratoryCommand);
        if (laboratoryId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getLaboratoryByIdQuery = new GetLaboratoryByIdQuery(laboratoryId);
        var laboratory = laboratoryQueryService.handle(getLaboratoryByIdQuery);
        if (laboratory.isEmpty()) return ResponseEntity.badRequest().build();
        var laboratoryResource = LaboratoryResourceFromEntityAssembler.toResourceFromEntity(laboratory.get());
        return new ResponseEntity<>(laboratoryResource, HttpStatus.CREATED);
    }

    @GetMapping("/{laboratoryId}")
    public ResponseEntity<LaboratoryResource> getLaboratoryById(@PathVariable Long laboratoryId) {
        var getLaboratoryByIdQuery = new GetLaboratoryByIdQuery(laboratoryId);
        var laboratory = laboratoryQueryService.handle(getLaboratoryByIdQuery);
        if (laboratory.isEmpty()) return ResponseEntity.badRequest().build();
        var notificationResource = LaboratoryResourceFromEntityAssembler.toResourceFromEntity(laboratory.get());
        return ResponseEntity.ok(notificationResource);
    }

    @GetMapping
    public ResponseEntity<List<LaboratoryResource>> getAllLaboratories() {
        var getAllLaboratoriesQuery = new GetAllLaboratoriesQuery();
        var laboratories = laboratoryQueryService.handle(getAllLaboratoriesQuery);
        var laboratoryResources = laboratories.stream().map(LaboratoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(laboratoryResources);
    }

    @PutMapping("/{laboratoryId}")
    public ResponseEntity<LaboratoryResource> updateLaboratory(@PathVariable Long laboratoryId, @RequestBody UpdateLaboratoryResource updateLaboratoryResource) {
        var updateLaboratoryCommand = UpdateLaboratoryCommandFromResourceAssembler.toCommandFromResource(updateLaboratoryResource, laboratoryId);
        var updateLaboratory = laboratoryCommandService.handle(updateLaboratoryCommand);
        if (updateLaboratory.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var notificationResource = LaboratoryResourceFromEntityAssembler.toResourceFromEntity(updateLaboratory.get());
        return ResponseEntity.ok(notificationResource);
    }

    @DeleteMapping("/{laboratoryId}")
    public ResponseEntity<?> deleteLaboratory(@PathVariable Long laboratoryId) {
        var deleteLaboratoryCommand = new DeleteLaboratoryCommand(laboratoryId);
        laboratoryCommandService.handle(deleteLaboratoryCommand);
        return ResponseEntity.ok("Laboratory with given id successfully deleted");
    }
}