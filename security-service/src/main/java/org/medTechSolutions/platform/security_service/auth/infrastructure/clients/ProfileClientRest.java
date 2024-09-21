package org.medTechSolutions.platform.security_service.auth.infrastructure.clients;

import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profiles-service", url = "${profiles-service.url}")
public interface ProfileClientRest {

    // para crear un perfil con rol Doctor
    @PostMapping("/doctors")
    public doctorDTO createDoctor(@RequestBody createDoctorDTO createDoctorDTO);

    // para crear un perfil con rol Laboratorio
    @PostMapping("/laboratories")
    public laboratoryDTO createLaboratory(@RequestBody createLaboratoryDTO createLaboratoryDTO);

    // para obtener un perfil con rol Patient
    @PostMapping("/patients")
    public patientDTO createPatient(@RequestBody createPatientDTO createPatientDTO);
}
