package com.dh.dentalClinicMVCE.service;

import com.dh.dentalClinicMVCE.model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Patient save(Patient patient);

    Optional<Patient> findById(Long id);

    void update(Patient patient);

    void delete(Long id);

    List<Patient> findAll();
}