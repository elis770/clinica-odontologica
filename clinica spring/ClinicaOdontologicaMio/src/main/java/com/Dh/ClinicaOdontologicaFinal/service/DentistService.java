package com.Dh.ClinicaOdontologicaFinal.service;

import com.Dh.ClinicaOdontologicaFinal.entities.Dentist;
import com.Dh.ClinicaOdontologicaFinal.repository.DentistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService {

    private final DentistRepo repo;

    @Autowired
    public DentistService(DentistRepo repo) {
        this.repo = repo;
    }

    public Dentist save(Dentist dentist){
        return repo.save(dentist);
    }

    public Optional<Dentist> findById(Integer id){
        return repo.findById(id);
    }

    public void update(Dentist dentist){
        repo.save(dentist);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public List<Dentist> findAll(){
        return repo.findAll();
    }
}
