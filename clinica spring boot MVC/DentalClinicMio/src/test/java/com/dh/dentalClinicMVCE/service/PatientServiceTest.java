package com.dh.dentalClinicMVCE.service;

import com.dh.dentalClinicMVCE.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceTest {

    @Autowired
    private PatientService patientService;
    @Test
    void findById() {
        Integer idP = 1;

        Patient patient = patientService.findById(idP);
        assertNotNull(patient);
    }
}