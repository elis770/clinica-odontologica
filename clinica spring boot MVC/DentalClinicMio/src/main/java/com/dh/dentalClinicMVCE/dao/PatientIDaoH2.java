package com.dh.dentalClinicMVCE.dao;

import com.dh.dentalClinicMVCE.model.Address;
import com.dh.dentalClinicMVCE.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class PatientIDaoH2 implements IDao<Patient> {
    private static final String SQL_INSERT = "INSERT INTO PATIENTS (NAME, LAST_NAME, EMAIL, CARD_IDENTITY, ADMISSION_OF_DATE, ADDRESS_ID) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_ID = "SELECT * FROM PATIENTS WHERE ID =?";
    private static final String SQL_UPDATE = "UPDATE PATIENTS SET NAME=?, LAST_NAME=?, EMAIL=?, CARD_IDENTITY=?, ADMISSION_OF_DATE=?, ADDRESS_ID=? WHERE ID=?";
    private static final String SQL_DELETE = "DELETE FROM PATIENTS WHERE ID =?";
    private static final String SQL_FIND_ALL = "SELECT * FROM PATIENTS";
    private static final String SQL_FIND_EMAIL = "SELECT * FROM PATIENTS WHERE EMAIL=?";

    @Override
    public Patient save(Patient patient) {
        Connection connection = null;
        try {
            AddressDaoH2 addressDaoH2 = new AddressDaoH2();
            Address savedAddress = addressDaoH2.save(patient.getAddress());
            patient.setAddress(savedAddress);

            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{"ID"});
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getLastName());
            ps.setString(3, patient.getEmail());
            ps.setInt(4, patient.getCardIdentity());
            ps.setDate(5,Date.valueOf( patient.getAdmissionOfDate()));
            ps.setInt(6, patient.getAddress().getId());
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                patient.setId(rs.getInt(1));
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patient;
    }

    @Override
    public Patient findById(Integer id) {
        Connection connection = null;
        Patient patient = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            AddressDaoH2 addressDaoH2 = new AddressDaoH2();
            while (rs.next()){
                Address address = addressDaoH2.findById(rs.getInt(7));
                patient = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6).toLocalDate(), address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patient;
    }

    @Override
    public Patient update(Patient patient) {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getLastName());
            ps.setString(3, patient.getEmail());
            ps.setInt(4, patient.getCardIdentity());
            ps.setDate(5,Date.valueOf( patient.getAdmissionOfDate()));
            ps.setInt(6, patient.getAddress().getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patient;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Patient> findAll() {
        Connection connection = null;
        Address address;
        List<Patient> patients = new ArrayList<>();
        try {
            AddressDaoH2 addressDaoH2 = new AddressDaoH2();
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                address = addressDaoH2.findById(rs.getInt(7));
                patients.add( new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6).toLocalDate(), address));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patients;
    }

    @Override
    public Patient findByString(String value) {
        Connection connection = null;
       Patient patient = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_EMAIL);
            ps.setString(1,value);
            ResultSet rs = ps.executeQuery();
            AddressDaoH2 addressDaoH2 = new AddressDaoH2();
            while (rs.next()){
            Address address = addressDaoH2.findById(rs.getInt(7));
            patient = new Patient(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getInt(5),rs.getDate(6).toLocalDate(), address );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patient;
    }
}