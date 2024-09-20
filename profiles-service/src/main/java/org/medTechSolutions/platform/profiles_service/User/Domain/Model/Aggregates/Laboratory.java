package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class Laboratory extends AuditableAbstractAggregateRoot<Laboratory> {

    private String name;

    private String address;

    private String phone;

    private String email;

    public Laboratory() {
        this.name = null;
        this.address = "default";
        this.phone = "default";
        this.email = "default";;
    }

    public Laboratory(CreateLaboratoryCommand command) {
        this();
        this.name = command.name();
        this.address = command.address();
        this.phone = command.phone();
        this.email = command.email();
    }

    public Laboratory update(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        return this;
    }

}