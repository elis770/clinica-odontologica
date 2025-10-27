package com.dh.dentalClinicMVCE.service.impl;

import com.dh.dentalClinicMVCE.Reposittory.IDentistRepo;
import com.dh.dentalClinicMVCE.model.Dentist;
import com.dh.dentalClinicMVCE.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistServiceImpl implements IDentistService {

    private IDentistRepo dentistRepo;

    @Autowired
    public DentistServiceImpl(IDentistRepo dentistRepo){
        this.dentistRepo = dentistRepo;
    }

    @Override
    public Dentist save(Dentist dentist) {
        return dentistRepo.save(dentist);
    }

    @Override
    public Optional<Dentist> findById(Long id) {
        return dentistRepo.findById(id);
    }

    @Override
    public void update(Dentist dentist) {
        dentistRepo.save(dentist);
    }

    @Override
    public void delete(Long id) {
        dentistRepo.deleteById(id);
    }

    @Override
    public List<Dentist> findAll() {
        return dentistRepo.findAll();
    }
}