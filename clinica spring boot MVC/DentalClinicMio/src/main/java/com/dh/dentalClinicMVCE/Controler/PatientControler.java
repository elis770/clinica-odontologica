package com.dh.dentalClinicMVCE.Controler;

import com.dh.dentalClinicMVCE.model.Patient;
import com.dh.dentalClinicMVCE.service.PatientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PatientControler {
    private PatientService patientService;
    public PatientControler(PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping
    public Patient save(@RequestBody Patient patient){
        return patientService.save(patient);
    }

    @PutMapping
    public void update(@RequestBody Patient patient){
        patientService.update(patient);
    }
}
