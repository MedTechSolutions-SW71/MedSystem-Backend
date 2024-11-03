package org.medTechSolutions.platform.treatments_service.application.internal.commandservices;

import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Result;
import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateResultCommand;
import org.medTechSolutions.platform.treatments_service.domain.services.ResultCommandService;
import org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultCommandServiceImpl implements ResultCommandService {
    private final ResultRepository resultRepository;

    public ResultCommandServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public Optional<Result> handle(CreateResultCommand command) {
        var result = new Result(command);
        resultRepository.save(result);
        return Optional.of(result);
    }
}