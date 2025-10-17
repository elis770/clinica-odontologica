package com.dh.dentalClinicMVCE.service;

import com.dh.dentalClinicMVCE.dao.DentistDaoH2;
import com.dh.dentalClinicMVCE.model.Dentist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DentistService {
    private final DentistDaoH2 dentistIDao;

    @Autowired
    public DentistService(DentistDaoH2 dentistIDao) {
        this.dentistIDao = dentistIDao;
    }

    public Dentist save(Dentist dentist) {
        return dentistIDao.save(dentist);
    }

    public Dentist findById(Integer id) {
        return dentistIDao.findById(id);
    }

    public void updateDentist(Dentist dentist) {
        dentistIDao.update(dentist);
    }

    public void deleteDentist(Integer id) {
        dentistIDao.delete(id);
    }

    public List<Dentist> findAll() {
        return dentistIDao.findAll();
    }
}
