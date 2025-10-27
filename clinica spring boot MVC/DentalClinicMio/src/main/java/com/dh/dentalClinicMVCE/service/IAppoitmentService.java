package com.dh.dentalClinicMVCE.service;

import com.dh.dentalClinicMVCE.model.Appoitment;

import java.util.List;
import java.util.Optional;

public interface IAppoitmentService {
    Appoitment save(Appoitment appoitment);

    Optional<Appoitment> findById(Long id);

    void update(Appoitment appoitment);

    void delete(Long id);

    List<Appoitment> findAll();
}