package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.model.Dentist;
import com.dh.DentalClinicMVC.service.DentistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class DentistController {

    private DentistService dentistService;

    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }


    //localhost:8080/dentist/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findById(@PathVariable Integer id) {
        Dentist dentist = dentistService.findById(id);

        if(dentist != null) {
            return ResponseEntity.ok(dentist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //vamos a guardar un nuevo odontólogo en la BD
    @PostMapping
    public ResponseEntity<Dentist> save(@RequestBody Dentist dentist) {
        return ResponseEntity.ok(dentistService.save(dentist));
    }

    //vamos a actualizar los datos de un odontologo
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Dentist dentist) {
        ResponseEntity<String> response;
        Dentist dentistToLookFor = dentistService.findById(dentist.getId());

        if (dentistToLookFor != null) {
            dentistService.updateDentist(dentist);
            response = ResponseEntity.ok("Se actualizó el odontólogo con nombre: " + dentist.getName());

        } else {
            response = ResponseEntity.ok().body("No se puede eliminar un odontólogo que no existe en la base de datos");
        }
        return response;
    }

    //vamos a borrar un odontólogo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        ResponseEntity<String> response;

        if (dentistService.findById(id) != null) {
            dentistService.deleteDentist(id);
            response = ResponseEntity.ok("Se eliminó el odontólogo con id: " + id);

        } else {
            response = ResponseEntity.ok().body("No se puede eliminar un odontólogo que no existe en la base de datos");
        }
        return response;
    }

    //vamos a listar todos los odontólogos
    @GetMapping
    public ResponseEntity<List<Dentist>> findAll() {
        return ResponseEntity.ok(dentistService.findAll());
    }
}
