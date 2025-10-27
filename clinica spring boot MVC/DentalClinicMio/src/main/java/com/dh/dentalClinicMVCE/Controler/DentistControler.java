package com.dh.dentalClinicMVCE.Controler;

import com.dh.dentalClinicMVCE.model.Dentist;
import com.dh.dentalClinicMVCE.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
@CrossOrigin(origins = "*")
public class DentistControler {
    private IDentistService iDentistService;

    @Autowired
    public DentistControler(IDentistService iDentistService) {
        this.iDentistService = iDentistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findById(@PathVariable Long id){
        Optional<Dentist> dentist =  iDentistService.findById(id);
        if (!dentist.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dentist.get());
    }

    @PostMapping
    public ResponseEntity<Dentist> save(@RequestBody Dentist dentist){
        return ResponseEntity.ok(iDentistService.save(dentist));
    }

    @PutMapping
    public ResponseEntity<Dentist> update(@RequestBody Dentist dentist){
        Optional<Dentist> dentistToLookFor = iDentistService.findById(dentist.getId());

        if (dentistToLookFor.isPresent()){
            return ResponseEntity.ok(iDentistService.save(dentist));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Dentist> dentist = iDentistService.findById(id);

        if (!dentist.isPresent()){
            return ResponseEntity.notFound().build();
        }
        iDentistService.delete(id);
        return ResponseEntity.ok("si se logro borrar, mala persona");
    }

    @GetMapping
    public ResponseEntity<List<Dentist>> findAll(){
        return ResponseEntity.ok(iDentistService.findAll());
    }
}