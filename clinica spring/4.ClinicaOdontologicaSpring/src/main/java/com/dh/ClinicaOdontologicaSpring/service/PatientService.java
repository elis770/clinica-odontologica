package com.dh.ClinicaOdontologicaSpring.service;

import com.dh.ClinicaOdontologicaSpring.dao.IDao;
import com.dh.ClinicaOdontologicaSpring.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private IDao<Patient> patientIDao;

    @Autowired
    public PatientService(IDao<Patient> patientIDao) {
        this.patientIDao = patientIDao;
    }

    public Patient save(Patient patient) {
        return patientIDao.save(patient);
    }

    public Patient findById(Integer id) {
        return patientIDao.findById(id);
    }


    public void update(Patient patient) {
        patientIDao.update(patient);
    }

    public void delete (Integer id) {
        patientIDao.delete(id);
    }

    public List<Patient> findAll(){
        return patientIDao.findAll();
    }

}
