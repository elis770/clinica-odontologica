package com.dh.dentalClinicMVCE.model;

public class Address {

    private Integer id;
    private String street;
    private Integer number;
    private String location;
    private String state;

    public Address(Integer id, String street, Integer number, String location, String state) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.location = location;
        this.state = state;
    }
    public Address(String street, Integer number, String location, String state) {
        this.street = street;
        this.number = number;
        this.location = location;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
