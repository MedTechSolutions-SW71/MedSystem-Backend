package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class Doctor extends AuditableAbstractAggregateRoot<Doctor> {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String specialization;

    @Column(name = "licence_number")
    private Integer licenceNumber;

    private String phone;

    private String email;

    @ManyToOne
    @JoinColumn(name="id_laboratory")
    private Laboratory idLaboratory;


    public Doctor() {
        this.firstName = "default";
        this.lastName = "default";
        this.specialization = "default";
        this.licenceNumber = null;
        this.phone = "default";
        this.email = "default";
        this.idLaboratory = null;
    }

    public Doctor(CreateDoctorCommand command, Laboratory idLaboratory) {
        this();
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.specialization = command.specialization();
        this.licenceNumber = command.licenceNumber();
        this.phone = command.phone();
        this.email = command.email();
        this.idLaboratory = idLaboratory;
    }

    public Doctor update(String firstName, String lastName, String specialization, Integer licenceNumber, String phone, String email, Laboratory idLaboratory) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.licenceNumber = licenceNumber;
        this.phone = phone;
        this.email = email;
        this.idLaboratory = idLaboratory;
        return this;
    }

}
