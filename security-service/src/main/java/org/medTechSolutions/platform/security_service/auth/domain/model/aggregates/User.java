package org.medTechSolutions.platform.security_service.auth.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.medTechSolutions.platform.security_service.auth.domain.model.entities.Role;
import org.medTechSolutions.platform.security_service.auth.domain.model.entities.SecurityProfiles;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.DoctorDTO;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.PatientDTO;
import org.medTechSolutions.platform.security_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User extends AuditableAbstractAggregateRoot<User> {

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(min = 3, max = 50)
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role ;

    // Relación bidireccional con SecurityProfiles
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private SecurityProfiles securityProfiles;

    @Transient
    private List<DoctorDTO> doctorDTO = new ArrayList<>();

    @Transient
    private List<PatientDTO> patientDTO = new ArrayList<>();

    public User() {
        // Constructor por defecto
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, Role role) {
        this(email, password);
        this.role = role;
    }


    // Método para asociar un perfil de seguridad
    public void setSecurityProfiles(SecurityProfiles securityProfiles) {
        this.securityProfiles = securityProfiles;
        if (securityProfiles != null) {
            securityProfiles.setUser(this);  // Aseguramos la referencia inversa
        }
    }

    // Métodos para agregar roles de doctor, laboratorio o paciente
    public void addDoctor(DoctorDTO doctorDTO) {
        this.doctorDTO.add(doctorDTO);
    }

    public void addPatient(PatientDTO patientDTO) {
        this.patientDTO.add(patientDTO);
    }

    public void removeDoctor(DoctorDTO doctorDTO) {
        this.doctorDTO.remove(doctorDTO);
    }


    public void removePatient(PatientDTO patientDTO) {
        this.patientDTO.remove(patientDTO);
    }

}
