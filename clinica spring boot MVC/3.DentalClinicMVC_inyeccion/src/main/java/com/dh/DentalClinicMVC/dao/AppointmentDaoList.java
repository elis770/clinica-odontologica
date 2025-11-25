package com.dh.DentalClinicMVC.dao;

import com.dh.DentalClinicMVC.model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoList implements IDao<Appointment> {

    private List<Appointment> appointments;

    public AppointmentDaoList() {
        appointments = new ArrayList<>();
    }

    @Override
    public Appointment save(Appointment appointment) {
        //guardamos en la lista el turnos que recibimos por parámetro
        appointments.add(appointment);
        return appointment;
    }

    @Override
    public Appointment findById(Integer id) {
        Appointment appointmentToLookFor = null;

        //recorrer la lista
        for (Appointment a: appointments) {
            //ir chequeando que el id del turno matchee con el id que estamos buscando
            if (a.getId() == id) {
                appointmentToLookFor = a;
                return appointmentToLookFor;
            }
        }
        return appointmentToLookFor;
    }

    @Override
    public void update(Appointment appointment) {
        Appointment appointment2Update = findById(appointment.getId());
        appointment2Update.setDentist(appointment.getDentist());
        appointment2Update.setPatient(appointment.getPatient());
        appointment2Update.setDate(appointment.getDate());
        appointments.set(1, appointment2Update);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Appointment> findAll() {
        return appointments;
    }

    @Override
    public Appointment findByString(String value) {
        return null;
    }
}
