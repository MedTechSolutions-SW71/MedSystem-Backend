package org.medTechSolutions.platform.security_service.auth.domain.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.medTechSolutions.platform.security_service.auth.domain.model.valueobjects.Roles;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Roles name;


    public Role(Roles name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static Role getDefaultRole() {
        return new Role(Roles.Patient);
    }

    // este metodo es para convertir un string a un objeto Role
    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(getDefaultRole());
        }

        return roles;
    }



}
