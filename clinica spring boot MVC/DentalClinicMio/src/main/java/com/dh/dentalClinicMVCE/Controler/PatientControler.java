package com.dh.dentalClinicMVCE.Controler;

import com.dh.dentalClinicMVCE.model.Patient;
import com.dh.dentalClinicMVCE.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PatientControler {

    private PatientService patientService;

    public PatientControler(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable Integer id) {
        return patientService.findById(id);
    }

    @PostMapping
    public Patient save(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    @PutMapping
    public void update(@RequestBody Patient patient) {
        patientService.updatePatient(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        patientService.deletePatient(id);
    }

    @GetMapping
    public List<Patient> findAll() {
        return patientService.findAll();
    }
}
