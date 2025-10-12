package com.Dh.ClinicaOdontologicaFinal.controller;

import com.Dh.ClinicaOdontologicaFinal.entities.Patient;
import com.Dh.ClinicaOdontologicaFinal.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public Patient save(@RequestBody Patient patient){
        return patientService.save(patient);
    }

    @GetMapping("/all")
    public List<Patient> getAll(){
        return patientService.findAll();
    }

    @GetMapping("/id")
    public ResponseEntity<Patient> findById(@RequestParam Integer id) {
        Optional<Patient> patientWanted = patientService.findById(id);
        if (!patientWanted.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(patientWanted.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Patient> update(@RequestBody Patient patient){
        Optional<Patient> opcionalPatient = patientService.findById(patient.getId());
        if (!opcionalPatient.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        patientService.update(patient);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Optional<Patient> opcionalPatient = patientService.findById(id);
        if (!opcionalPatient.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found with id: " + id);
        }
        patientService.delete(id);
        return ResponseEntity.ok("Patient deleted with id: " + id);
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<List<Patient>> findByLastname(@PathVariable String lastname){
        List<Patient> patients = patientService.findByLastname(lastname);
        if (patients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(patients);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Patient> findByName(@PathVariable String name){
        Optional<Patient> patient = patientService.findByName(name);
        if (patient.isPresent()) {
            return ResponseEntity.ok(patient.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
