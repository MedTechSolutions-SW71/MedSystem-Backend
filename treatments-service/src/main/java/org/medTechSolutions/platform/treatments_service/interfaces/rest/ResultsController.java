package org.medTechSolutions.platform.treatments_service.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.medTechSolutions.platform.treatments_service.domain.services.ResultCommandService;
import org.medTechSolutions.platform.treatments_service.domain.services.ResultQueryService;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.CreateResultResource;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.ResultResource;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.transform.CreateResultCommandFromResourceAssembler;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.transform.ResultResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/results", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Results", description = "Results Management Endpoints")
@CrossOrigin(origins = "*")
public class ResultsController {
    private final ResultQueryService resultQueryService;
    private final ResultCommandService resultCommandService;
    public ResultsController(ResultQueryService resultQueryService, ResultCommandService resultCommandService) {
        this.resultQueryService = resultQueryService;
        this.resultCommandService = resultCommandService;
    }
    @PostMapping
    public ResponseEntity<ResultResource> createResult(@RequestBody CreateResultResource resource){
        var createResultCommand = CreateResultCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = resultCommandService.handle(createResultCommand);
        if (result.isEmpty())
            return ResponseEntity.badRequest().build();
        var resultResource = ResultResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return new ResponseEntity<>(resultResource, HttpStatus.CREATED);
    }

    @GetMapping("/doctorId/{doctorId}")
    public ResponseEntity<List<ResultResource>> getResultsByDoctorId(@PathVariable Long doctorId) {
        var results = resultQueryService.getByDoctorId(doctorId);
        var resources = results.stream()
                .map(ResultResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/patientId/{patientId}")
    public ResponseEntity<List<ResultResource>> getResultsByPatientId(@PathVariable Long patientId) {
        var results = resultQueryService.getByPatientId(patientId);
        var resources = results.stream()
                .map(ResultResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}