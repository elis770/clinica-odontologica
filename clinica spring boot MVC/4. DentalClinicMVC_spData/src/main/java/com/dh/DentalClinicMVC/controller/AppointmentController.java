package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.model.Appointment;
import com.dh.DentalClinicMVC.service.IAppointmentService;
import com.dh.DentalClinicMVC.service.IDentistService;
import com.dh.DentalClinicMVC.service.IPatientService;
import com.dh.DentalClinicMVC.service.impl.AppointmentService;
import com.dh.DentalClinicMVC.service.impl.DentistServiceImpl;
import com.dh.DentalClinicMVC.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class AppointmentController {

    private IAppointmentService appointmentService;

    private IDentistService dentistService;

    private IPatientService patientService;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService, IDentistService dentistService, IPatientService patientService) {
        this.appointmentService = appointmentService;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    //este endpoint consulta todos los turnos
    @GetMapping
    public ResponseEntity<List<Appointment>> findAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Appointment> save(@RequestBody Appointment appointment) {
        ResponseEntity<Appointment> response;

        //chequeamos que existan el odontólogo y el paciente
        if (dentistService.findById(appointment.getDentist().getId()).isPresent()
        && patientService.findById(appointment.getPatient().getId()).isPresent()) {
            //seteamos al ResponseEntity con el código 200 y le agregamos el turno como cuerpo de la respuesta
            response = ResponseEntity.ok(appointmentService.save(appointment));

        } else {
            //setear al ResponseEntity el código 400
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(Long id) {
        Optional<Appointment> appointmentToLookFor = appointmentService.findById(id);

        if(appointmentToLookFor.isPresent()) {
            return ResponseEntity.ok(appointmentToLookFor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Appointment appointment) {
        ResponseEntity<String> response;

        //chequeamos que existan el odontólogo y el paciente
        if (dentistService.findById(appointment.getDentist().getId()).isPresent()
                && patientService.findById(appointment.getPatient().getId()).isPresent()) {
            //ambos existen en la DB
            //seteamos al ResponseEntity con el código 200 y le agregamos el turno como cuerpo de la respuesta
            appointmentService.update(appointment);
            response = ResponseEntity.ok("Se actualizó el turno con id: " + appointment.getId());

        } else {
            //uno no existe, entonces bloqueamos la operación
            //setear al ResponseEntity el código 400
            response = ResponseEntity.badRequest().body("No se puede actualizar un turno que no existe en la base de datos");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response;

        if (appointmentService.findById(id).isPresent()) {
            appointmentService.delete(id);
            response = ResponseEntity.ok("Se eliminó el turno con id: " + id);

        } else {
            response = ResponseEntity.ok().body("No se puede eliminar un turno que no existe en la base de datos");
        }
        return response;
    }


}
