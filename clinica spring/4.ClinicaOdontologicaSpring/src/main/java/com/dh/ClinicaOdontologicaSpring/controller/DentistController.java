package com.dh.ClinicaOdontologicaSpring.controller;

import com.dh.ClinicaOdontologicaSpring.model.Dentist;
import com.dh.ClinicaOdontologicaSpring.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    private DentistService dentistService;

    @Autowired
    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @GetMapping("/all")
    public List<Dentist> findAll(){
        return dentistService.findAll();
    }
}
