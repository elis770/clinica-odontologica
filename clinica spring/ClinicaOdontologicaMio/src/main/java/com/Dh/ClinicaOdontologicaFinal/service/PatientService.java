package com.Dh.ClinicaOdontologicaFinal.service;

import com.Dh.ClinicaOdontologicaFinal.entities.Patient;
import com.Dh.ClinicaOdontologicaFinal.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepo repo;

    @Autowired
    public PatientService(PatientRepo repo) {
        this.repo = repo;
    }

    public Patient save(Patient patient){
        return repo.save(patient);
    }

    public Optional<Patient> findById(Integer id){
        return repo.findById(id);
    }

    public void update(Patient patient){
        repo.save(patient);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public List<Patient> findAll(){
        return repo.findAll();
    }
    public List<Patient> findByLastname(String lastname){
        return repo.findByLastname(lastname);
    }
    public Optional<Patient> findByName(String name){
        return repo.findByName(name);
    }
}