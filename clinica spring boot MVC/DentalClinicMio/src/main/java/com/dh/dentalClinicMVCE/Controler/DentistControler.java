package com.dh.dentalClinicMVCE.Controler;

import com.dh.dentalClinicMVCE.model.Dentist;
import com.dh.dentalClinicMVCE.model.Dentist;
import com.dh.dentalClinicMVCE.service.DentistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class DentistControler {
    private DentistService dentistService;
    public DentistControler(DentistService dentistService){
        this.dentistService = dentistService;
    }

    @GetMapping("/{id}")
    public Dentist findById(@PathVariable Integer id){
        return dentistService.findById(id);
    }

    @PostMapping
    public Dentist save(@RequestBody Dentist dentist){
        return dentistService.save(dentist);
    }

    @PutMapping
    public void update(@RequestBody Dentist dentist){
        dentistService.updateDentist(dentist);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        dentistService.deleteDentist(id);
    }

    @GetMapping
    public List<Dentist> findAll(){
        return dentistService.findAll();
    }
}