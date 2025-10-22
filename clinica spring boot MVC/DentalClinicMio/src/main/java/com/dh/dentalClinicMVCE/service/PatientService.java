package com.dh.dentalClinicMVCE.service;

import com.dh.dentalClinicMVCE.dao.IDao;
import com.dh.dentalClinicMVCE.dao.PatientDaoH2;
import com.dh.dentalClinicMVCE.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private IDao<Patient> patientIDao;

    public PatientService(IDao<Patient> patientIDao) {
        this.patientIDao = patientIDao;
    }

    public Patient save(Patient patient) {
        return patientIDao.save(patient);
    }

    public Patient findById(Integer id) {
        return patientIDao.findById(id);
    }

    public void updatePatient(Patient patient) {
        patientIDao.update(patient);
    }

    public void deletePatient(Integer id) {
        patientIDao.delete(id);
    }

    public List<Patient> findAll() {
        return patientIDao.findAll();
    }

    public Patient findByEmail(String email) {
        return patientIDao.findByString(email);
    }
}