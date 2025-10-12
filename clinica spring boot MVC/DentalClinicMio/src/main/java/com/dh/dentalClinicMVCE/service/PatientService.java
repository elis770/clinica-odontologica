package com.dh.dentalClinicMVCE.service;

import com.dh.dentalClinicMVCE.dao.PatientIDaoH2;
import com.dh.dentalClinicMVCE.model.Patient;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientIDaoH2 patientIDao;

    @Autowired
    public PatientService(PatientIDaoH2 patientIDao) {
        this.patientIDao = patientIDao;
    }

    public Patient save(Patient patient) throws SQLException {
        return patientIDao.save(patient);
    }

    public Patient findById(Integer id) {
        return patientIDao.findById(id);
    }

    public Patient update(Patient patient) {
        return patientIDao.update(patient);
    }

    public void delete(Integer id) {
        patientIDao.delete(id);
    }

    public List<Patient> findAll() {
        return patientIDao.findAll();
    }

    public Patient findByEmail(String email){
        return patientIDao.findByString(email);
    }
}