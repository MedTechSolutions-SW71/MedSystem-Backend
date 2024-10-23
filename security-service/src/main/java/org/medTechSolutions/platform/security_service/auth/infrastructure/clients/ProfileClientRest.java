package org.medTechSolutions.platform.security_service.auth.infrastructure.clients;

import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.*;
import org.medTechSolutions.platform.security_service.auth.interfaces.rest.resources.UserResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "profiles-service", url = "${profiles-service.url}")
public interface ProfileClientRest {

    // para crear un perfil con rol Doctor
    @PostMapping("/doctors")
    public UserResource createDoctor(@RequestBody UserResource userResource);

    // para crear un perfil con rol Patient
    @PostMapping("/patients")
    public UserResource createPatient(@RequestBody UserResource userResource);


}
