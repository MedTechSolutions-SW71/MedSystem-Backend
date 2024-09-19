package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreatePatientCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.medTechSolutions.platform.profiles_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class Patient extends AuditableAbstractAggregateRoot<Patient> {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Integer age;

    private String address;

    private String phone;

    private String email;

    public Patient() {
        this.firstName = "default";
        this.lastName = "default";
        this.age = null;
        this.address = "default";
        this.phone = "default";
        this.email = "default";
    }

    public Patient(CreatePatientCommand command) {
        this();
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.age = command.age();
        this.address = command.address();
        this.phone = command.phone();
        this.email = command.email();
    }

    public Patient update(String firstName, String lastName, Integer age, String address, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.email = email;
        return this;
    }
}