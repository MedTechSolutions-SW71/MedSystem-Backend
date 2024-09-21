package org.medTechSolutions.platform.security_service.auth.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecurityProfiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_id", unique = true)
    private Long doctorId;

    @Column(name = "laboratory_id", unique = true)
    private Long laboratoryId;

    @Column(name = "patient_id", unique = true)
    private Long patientId;

    // verificamos si el usuario es el mismo que el perfil
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecurityProfiles that = (SecurityProfiles) o;

        if (!Objects.equals(doctorId, that.doctorId)) return false;
        if (!Objects.equals(laboratoryId, that.laboratoryId)) return false;
        return Objects.equals(patientId, that.patientId);
    }

}
