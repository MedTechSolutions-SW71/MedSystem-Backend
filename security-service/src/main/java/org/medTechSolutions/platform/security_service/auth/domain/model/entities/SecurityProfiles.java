package org.medTechSolutions.platform.security_service.auth.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.medTechSolutions.platform.security_service.auth.domain.model.aggregates.User;

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

    @Column(name = "doctor_id", unique = true, nullable = true)
    private Long doctorId;

    @Column(name = "laboratory_id", unique = true, nullable = true)
    private Long laboratoryId;

    @Column(name = "patient_id", unique = true, nullable = true)
    private Long patientId;

    // Relación bidireccional con User
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityProfiles that = (SecurityProfiles) o;
        return Objects.equals(doctorId, that.doctorId) &&
                Objects.equals(laboratoryId, that.laboratoryId) &&
                Objects.equals(patientId, that.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, laboratoryId, patientId);
    }
}
