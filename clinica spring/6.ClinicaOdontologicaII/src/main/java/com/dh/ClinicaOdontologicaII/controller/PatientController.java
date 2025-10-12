package com.dh.ClinicaOdontologicaII.controller;

import com.dh.ClinicaOdontologicaII.entities.Patient;
import com.dh.ClinicaOdontologicaII.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Crea un paciente
    @PostMapping
    public Patient save(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    //Lista los pacientes
    //localhost:8080/patient/all
    @GetMapping("/all")
    public List<Patient> getAll() {
        return patientService.findAll();
    }

    //Actualizar un paciente
    @PutMapping("/update")
    public Patient update(@RequestBody Patient patient) {
        Optional<Patient> optionalPatient = patientService.findById(patient.getId());
        if (optionalPatient.isPresent()) {
            patientService.update(patient);
            return patient;
        } else {
            return patient;
        }
    }

    //Consultar paciente por id
    @GetMapping("/id")
    public Patient findById(@RequestParam Integer id) {
        Optional<Patient> optionalPatient = patientService.findById(id);
        if (optionalPatient.isPresent()) {
            return optionalPatient.get();
        }
        else {
            return null;
        }
    }

    //Elimina paciente por id
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Patient> patientWanted = patientService.findById(id);
        if (patientWanted.isPresent()) {
            patientService.delete(id);
            return "Se eliminó el paciente con id: " + id;
        } else {
            return "No se encontró el paciente con id: " + id;
        }
    }



}
