package org.medTechSolutions.platform.treatments_service.interfaces.rest;

import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateTreatmentCommand;
import org.medTechSolutions.platform.treatments_service.domain.model.commands.DeleteTreatmentCommand;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetAllTreatmentsQuery;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetTreatmentByIdQuery;
import org.medTechSolutions.platform.treatments_service.domain.services.TreatmentCommandService;
import org.medTechSolutions.platform.treatments_service.domain.services.TreatmentQueryService;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.CreateTreatmentResource;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.TreatmentResource;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.transform.CreateTreatmentCommandFromResourceAssembler;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.transform.TreatmentResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;



import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/treatments")
@Tag(name = "Treatment Management", description = "Operations related to treatments")
public class TreatmentController {

    private final TreatmentCommandService treatmentCommandService;
    private final TreatmentQueryService treatmentQueryService;

    public TreatmentController(TreatmentCommandService treatmentCommandService, TreatmentQueryService treatmentQueryService) {
        this.treatmentCommandService = treatmentCommandService;
        this.treatmentQueryService = treatmentQueryService;
    }

    @PostMapping
    public ResponseEntity<TreatmentResource> createTreatment(@RequestBody CreateTreatmentResource createTreatmentResource) {
        CreateTreatmentCommand createTreatmentCommand = CreateTreatmentCommandFromResourceAssembler.toCommandFromResource(createTreatmentResource);

        Long treatmentId = treatmentCommandService.handle(createTreatmentCommand);

        TreatmentResource treatmentResource = TreatmentResourceAssembler.toResource(treatmentId, createTreatmentResource);

        URI location = URI.create(String.format("/treatments/%d", treatmentId));
        return ResponseEntity.created(location).body(treatmentResource);
    }

    @GetMapping
    public ResponseEntity<List<TreatmentResource>> getAllTreatments() {
        List<TreatmentResource> treatments = treatmentQueryService.handle(new GetAllTreatmentsQuery())
                .stream()
                .map(TreatmentResourceAssembler::toResource)
                .toList();

        return ResponseEntity.ok(treatments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentResource> getTreatmentById(@PathVariable Long id) {

        Optional<TreatmentResource> treatmentResource = treatmentQueryService.handle(new GetTreatmentByIdQuery(id))
                .map(TreatmentResourceAssembler::toResource);


        return treatmentResource.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long id) {
        try {
            DeleteTreatmentCommand deleteTreatmentCommand = new DeleteTreatmentCommand(id);
            treatmentCommandService.handle(deleteTreatmentCommand);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}