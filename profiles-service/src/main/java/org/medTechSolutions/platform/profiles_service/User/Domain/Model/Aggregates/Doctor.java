package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateDoctorCommand;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.ValueObjects.Specialities;
import org.medTechSolutions.platform.profiles_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class Doctor extends AuditableAbstractAggregateRoot<Doctor> {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "licence_number")
    private Integer licenceNumber;

    @Enumerated(EnumType.STRING)
    @Setter
    private Specialities specialities;

    private String phone;

    @Setter
    private String email;

    /*
    @ManyToOne
    @JoinColumn(name="id_laboratory")
    private Laboratory idLaboratory;
     */

    public Doctor() {
        this.firstName = "default";
        this.lastName = "default";
        this.licenceNumber = null;
        this.specialities = Specialities.CARDIOLOGY;
        this.phone = "default";
        this.email = "default";
        // this.idLaboratory = null;
    }

    public Doctor(CreateDoctorCommand command) {
        this();
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.licenceNumber = command.licenceNumber();
        this.specialities = command.specialities();
        this.phone = command.phone();
        this.email = command.email();
        // this.idLaboratory = idLaboratory;
    }

    public Doctor update(String firstName, String lastName, Integer licenceNumber, Specialities specialities, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenceNumber = licenceNumber;
        this.specialities = specialities;
        this.phone = phone;
        //this.idLaboratory = idLaboratory;
        return this;
    }

}
