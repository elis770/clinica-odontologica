package com.dh.ClinicaOdontologicaSpring.service;

import com.dh.ClinicaOdontologicaSpring.dao.IDao;
import com.dh.ClinicaOdontologicaSpring.model.Dentist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService {

    private IDao<Dentist> dentistIDao;

    @Autowired
    public DentistService(IDao<Dentist> dentistIDao) {
        this.dentistIDao = dentistIDao;
    }

    public Dentist save(Dentist dentist){
        return dentistIDao.save(dentist);
    }

    public Dentist findById(Integer id) {
        return dentistIDao.findById(id);
    }

    public void update(Dentist dentist) {
        dentistIDao.update(dentist);
    }

    public void delete(Integer id) {
        dentistIDao.delete(id);
    }

    public List<Dentist> findAll() {
        return dentistIDao.findAll();
    }


}
