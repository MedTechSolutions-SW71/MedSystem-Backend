package org.medTechSolutions.platform.treatments_service.interfaces.rest;

import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateTreatmentCommand;
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
        // Convertir el recurso recibido a un comando
        CreateTreatmentCommand createTreatmentCommand = CreateTreatmentCommandFromResourceAssembler.toCommandFromResource(createTreatmentResource);

        // Manejar el comando para crear el tratamiento y obtener el ID del tratamiento creado
        Long treatmentId = treatmentCommandService.handle(createTreatmentCommand);

        // Convertir el tratamiento creado a un recurso de respuesta
        TreatmentResource treatmentResource = TreatmentResourceAssembler.toResource(treatmentId, createTreatmentResource);

        // Devolver la URI del nuevo tratamiento junto con el recurso
        URI location = URI.create(String.format("/treatments/%d", treatmentId));
        return ResponseEntity.created(location).body(treatmentResource);
    }

    @GetMapping
    public ResponseEntity<List<TreatmentResource>> getAllTreatments() {
        // Obtener todos los tratamientos y convertirlos a recursos
        List<TreatmentResource> treatments = treatmentQueryService.handle(new GetAllTreatmentsQuery())
                .stream()
                .map(TreatmentResourceAssembler::toResource)  // Convertir cada tratamiento a un recurso
                .toList();

        return ResponseEntity.ok(treatments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentResource> getTreatmentById(@PathVariable Long id) {
        // Buscar el tratamiento por ID y convertirlo a recurso
        Optional<TreatmentResource> treatmentResource = treatmentQueryService.handle(new GetTreatmentByIdQuery(id))
                .map(TreatmentResourceAssembler::toResource);  // Convertir a recurso si existe

        // Devolver la respuesta correspondiente dependiendo de si el tratamiento fue encontrado o no
        return treatmentResource.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}