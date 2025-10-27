package com.dh.dentalClinicMVCE.service.impl;

import com.dh.dentalClinicMVCE.model.Appoitment;
import com.dh.dentalClinicMVCE.Reposittory.IAppoitmentRepo;
import com.dh.dentalClinicMVCE.service.IAppoitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppoitmentServiceImpl implements IAppoitmentService {
    private IAppoitmentRepo iAppoitmentRepo;

    @Autowired
    public AppoitmentServiceImpl(IAppoitmentRepo iAppoitmentRepo) {
        this.iAppoitmentRepo = iAppoitmentRepo;
    }

    @Override
    public Appoitment save(Appoitment appoitment) {
        return iAppoitmentRepo.save(appoitment);
    }

    @Override
    public Optional<Appoitment> findById(Long id) {
        return iAppoitmentRepo.findById(id);
    }

    @Override
    public void update(Appoitment appoitment) {
        iAppoitmentRepo.save(appoitment);
    }

    @Override
    public void delete(Long id) {
        iAppoitmentRepo.deleteById(id);
    }

    @Override
    public List<Appoitment> findAll() {
        return iAppoitmentRepo.findAll();
    }
}