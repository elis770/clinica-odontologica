package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.model.Dentist;
import com.dh.DentalClinicMVC.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class DentistController {

    private IDentistService iDentistService;

    @Autowired
    public DentistController(IDentistService iDentistService) {
        this.iDentistService = iDentistService;
    }

    //localhost:8080/dentist/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findById(@PathVariable Long id) {
        Optional<Dentist> dentist = iDentistService.findById(id);

        if (dentist.isPresent()) {
            return ResponseEntity.ok(dentist.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //vamos a guardar un nuevo odontólogo en la BD
    @PostMapping
    public ResponseEntity<Dentist> save(@RequestBody Dentist dentist) {
        return ResponseEntity.ok(iDentistService.save(dentist));
    }

    //vamos a actualizar los datos de un odontologo
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Dentist dentist) {
        ResponseEntity<String> response;
        Optional<Dentist> dentistToLookFor = iDentistService.findById(dentist.getId());

        if (dentistToLookFor.isPresent()) {
            iDentistService.update(dentist);
            response = ResponseEntity.ok("Se actualizó el odontólogo con nombre: " + dentist.getName());

        } else {
            response = ResponseEntity.ok().body("No se puede actualizar un odontólogo que no existe en la base de datos");
        }
        return response;
    }

    //vamos a borrar un odontólogo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response;
        Optional<Dentist> dentist = iDentistService.findById(id);

        if (dentist.isPresent()) {
            iDentistService.delete(id);
            response = ResponseEntity.ok("Se eliminó el odontólogo con id: " + id);

        } else {
            response = ResponseEntity.ok().body("No se puede eliminar un odontólogo que no existe en la base de datos");
        }
        return response;
    }

    //vamos a listar todos los odontólogos
    @GetMapping
    public ResponseEntity<List<Dentist>> findAll() {
        return ResponseEntity.ok(iDentistService.findAll());
    }
}
