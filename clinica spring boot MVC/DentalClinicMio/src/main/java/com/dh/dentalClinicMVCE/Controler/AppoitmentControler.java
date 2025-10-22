package com.dh.dentalClinicMVCE.Controler;

import com.dh.dentalClinicMVCE.model.Appoitment;
import com.dh.dentalClinicMVCE.service.AppoitmentService;
import com.dh.dentalClinicMVCE.service.DentistService;
import com.dh.dentalClinicMVCE.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class AppoitmentControler {

    private AppoitmentService appoitmentService;
    private DentistService dentistService;
    private PatientService patientService;

    @Autowired
    public AppoitmentControler(AppoitmentService appoitmentService, DentistService dentistService, PatientService patientService) {
        this.appoitmentService = appoitmentService;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Appoitment>> findAll() {
        return ResponseEntity.ok(appoitmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Appoitment> save(@RequestBody Appoitment appoitment) {
        try {
            if (dentistService.findById(appoitment.getDentist().getId()) != null) {
                return ResponseEntity.ok(appoitmentService.save(appoitment));
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
