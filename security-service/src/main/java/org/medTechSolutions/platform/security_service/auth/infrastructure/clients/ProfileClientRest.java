package org.medTechSolutions.platform.security_service.auth.infrastructure.clients;

import org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profiles-service", url = "${profiles-service.url}")
public interface ProfileClientRest {

    // para crear un perfil con rol Doctor
    @PostMapping("/doctors")
    public DoctorDTO createDoctor(@RequestBody CreateDoctorDTO createDoctorDTO);

    // para crear un perfil con rol Laboratorio
    @PostMapping("/laboratories")
    public LaboratoryDTO createLaboratory(@RequestBody CreateLaboratoryDTO createLaboratoryDTO);

    // para crear un perfil con rol Patient
    @PostMapping("/patients")
    public PatientDTO createPatient(@RequestBody CreatePatientDTO createPatientDTO);

    // para eliminar un perfil con rol Doctor
    @DeleteMapping("doctors/{doctorId}")
    public void deleteDoctor(@PathVariable Long doctorId);

    // para eliminar un perfil con rol Laboratorio
    @DeleteMapping("laboratories/{laboratoryId}")
    public void deleteLaboratory(@PathVariable Long laboratoryId);

    // para eliminar un perfil con rol Patient
    @DeleteMapping("patients/{patientId}")
    public void deletePatient(@PathVariable Long patientId);
}
