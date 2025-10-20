package com.dh.dentalClinicMVCE.model;

import java.time.LocalDate;

public class Appoitment {
    private Integer id;
    private Dentist dentist;
    private LocalDate date;

    public Appoitment() {

    }
    public Appoitment(LocalDate date, Dentist dentist, Integer id) {
        this.date = date;
        this.dentist = dentist;
        this.id = id;
    }

    public Appoitment(LocalDate date, Dentist dentist) {
        this.date = date;
        this.dentist = dentist;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
