package org.medTechSolutions.platform.treatments_service.domain.services;

import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Result;
import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateResultCommand;

import java.util.Optional;

public interface ResultCommandService {
    Optional<Result> handle(CreateResultCommand command);
}