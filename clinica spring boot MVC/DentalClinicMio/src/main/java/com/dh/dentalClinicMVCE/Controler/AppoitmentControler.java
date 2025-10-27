package com.dh.dentalClinicMVCE.Controler;

import com.dh.dentalClinicMVCE.model.Appoitment;
import com.dh.dentalClinicMVCE.service.IAppoitmentService;
import com.dh.dentalClinicMVCE.service.IDentistService;
import com.dh.dentalClinicMVCE.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class AppoitmentControler {

    private IAppoitmentService iAppoitmentService;
    private IDentistService iDentistService;
    private IPatientService iPatientService;

    @Autowired
    public AppoitmentControler(IAppoitmentService iAppoitmentService, IDentistService iDentistService, IPatientService iPatientService) {
        this.iAppoitmentService = iAppoitmentService;
        this.iDentistService = iDentistService;
        this.iPatientService = iPatientService;
    }

    @GetMapping
    public ResponseEntity<List<Appoitment>> findAll() {
        return ResponseEntity.ok(iAppoitmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Appoitment> save(@RequestBody Appoitment appoitment) {
        ResponseEntity<Appoitment> response;
        if (iDentistService.findById(appoitment.getDentist().getId()).isPresent() && iPatientService.findById(appoitment.getPatient().getId()).isPresent()) {
            return ResponseEntity.ok(iAppoitmentService.save(appoitment));
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Appoitment> findById(@PathVariable Long id) {
        Optional<Appoitment> appointmentToLookFor = iAppoitmentService.findById(id);

        if(appointmentToLookFor.isPresent()) {
            return ResponseEntity.ok(appointmentToLookFor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Appoitment appointment) {
        ResponseEntity<String> response;

        //chequeamos que existan el odontólogo y el paciente
        if (iDentistService.findById(appointment.getDentist().getId()).isPresent()
                && iPatientService.findById(appointment.getPatient().getId()).isPresent()) {
            //ambos existen en la DB
            //seteamos al ResponseEntity con el código 200 y le agregamos el turno como cuerpo de la respuesta
            iAppoitmentService.update(appointment);
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

        if (iAppoitmentService.findById(id).isPresent()) {
            iAppoitmentService.delete(id);
            response = ResponseEntity.ok("Se eliminó el turno con id: " + id);

        } else {
            response = ResponseEntity.ok().body("No se puede eliminar un turno que no existe en la base de datos");
        }
        return response;
    }
}