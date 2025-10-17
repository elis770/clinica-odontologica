package com.dh.dentalClinicMVCE.model;

public class Dentist {

    private Integer id;
    private Integer registration;
    private String name;
    private String lastName;


    public Dentist(){
    }
    public Dentist(Integer id,int registration, String lastName, String name) {
        this.id = id;
        this.registration = registration;
        this.lastName = lastName;
        this.name = name;
    }

    public Dentist(String lastName, String name, Integer registration) {
        this.registration = registration;
        this.lastName = lastName;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }
}
