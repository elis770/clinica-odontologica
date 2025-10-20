package com.dh.dentalClinicMVCE.Controler;

import com.dh.dentalClinicMVCE.model.Dentist;
import com.dh.dentalClinicMVCE.model.Patient;
import com.dh.dentalClinicMVCE.service.DentistService;
import com.dh.dentalClinicMVCE.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/inicio")
public class InicioControler {

    private final PatientService patientService;
    private final DentistService dentistService;

    @Autowired
    public InicioControler(PatientService patientService, DentistService dentistService) {
        this.patientService = patientService;
        this.dentistService = dentistService;
    }

    @GetMapping
    public String findData(Model model, @RequestParam(required = false) String email, @RequestParam(required = false) Integer id) {
        Patient patient = null;
        if (email != null && !email.isEmpty()) {
            patient = patientService.findByEmail(email);
        }
        model.addAttribute("patient", patient);

        Dentist dentist = null;
        if (id != null) {
            dentist = dentistService.findById(id);
        }
        model.addAttribute("dentist", dentist);

        return "inicio";
    }
}