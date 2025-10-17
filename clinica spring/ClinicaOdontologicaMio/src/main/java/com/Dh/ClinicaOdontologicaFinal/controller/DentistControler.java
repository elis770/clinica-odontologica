package com.Dh.ClinicaOdontologicaFinal.controller;

import com.Dh.ClinicaOdontologicaFinal.entities.Dentist;
import com.Dh.ClinicaOdontologicaFinal.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentist")
public class DentistControler {

    private final DentistService dentistService;

    @Autowired
    public DentistControler(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping("/save")
    public Dentist save(@RequestBody Dentist dentist) {
        return dentistService.save(dentist);
    }

    @GetMapping("/all")
    public List<Dentist> getAll() {
        return dentistService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Dentist> findById(@PathVariable Integer id) {
        Optional<Dentist> dentistWanted = dentistService.findById(id);
        if (!dentistWanted.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(dentistWanted.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Dentist> update(@RequestBody Dentist dentist) {
        Optional<Dentist> opcionalDentist = dentistService.findById(dentist.getId());
        if (!opcionalDentist.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        dentistService.update(dentist);
        return ResponseEntity.ok(dentist);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Optional<Dentist> opcionalDentist = dentistService.findById(id);
        if (!opcionalDentist.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentist not found with id: " + id);
        }
        dentistService.delete(id);
        return ResponseEntity.ok("Dentist deleted with id: " + id);
    }
}
