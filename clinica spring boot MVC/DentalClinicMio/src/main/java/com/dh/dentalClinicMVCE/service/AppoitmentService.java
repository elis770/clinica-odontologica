package com.dh.dentalClinicMVCE.service;

import com.dh.dentalClinicMVCE.dao.IDao;
import com.dh.dentalClinicMVCE.model.Appoitment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppoitmentService {

    private IDao<Appoitment> appoitmentIDao;

    @Autowired
    public AppoitmentService(IDao<Appoitment> appoitmentIDao){
        this.appoitmentIDao = appoitmentIDao;
    }
    public Appoitment save(Appoitment appoitment){
        return appoitmentIDao.save(appoitment);
    }
    public Appoitment findById(Integer id){
        return appoitmentIDao.findById(id);
    }
    public List<Appoitment> findAll(){
        return appoitmentIDao.findAll();
    }
}
