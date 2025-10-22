package com.dh.dentalClinicMVCE.dao;

import com.dh.dentalClinicMVCE.model.Address;
import com.dh.dentalClinicMVCE.model.Patient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PatientDaoH2 implements IDao<Patient> {

    private static final String SQL_INSERT = "INSERT INTO PATIENTS (NOMBRE, APELLIDO, EMAIL, DNI, FECHA_INGRESO, ADDRESS_ID) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_ID = "SELECT * FROM PATIENTS WHERE ID =?";
    private static final String SQL_UPDATE = "UPDATE PATIENTS SET NOMBRE=?, APELLIDO=?, EMAIL=?, DNI=?, FECHA_INGRESO=?, ADDRESS_ID=? WHERE ID=?";
    private static final String SQL_DELETE = "DELETE FROM PATIENTS WHERE ID =?";
    private static final String SQL_FIND_ALL = "SELECT * FROM PATIENTS";

    private AddressDaoH2 addressDaoH2;

    public PatientDaoH2(AddressDaoH2 addressDaoH2) {
        this.addressDaoH2 = addressDaoH2;
    }

    @Override
    public Patient save(Patient patient) {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            Address address = addressDaoH2.save(patient.getAddress());
            patient.setAddress(address);

            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, patient.getNombre());
            ps.setString(2, patient.getApellido());
            ps.setString(3, patient.getEmail());
            ps.setString(4, patient.getDni());
            ps.setDate(5, Date.valueOf(patient.getFechaIngreso()));
            ps.setInt(6, patient.getAddress().getId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                patient.setId(rs.getInt(1));
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
    public Patient findById(Integer id) {
        Connection connection = null;
        Patient patient = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Address address = addressDaoH2.findById(rs.getInt("ADDRESS_ID"));
                patient = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), address);
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
            addressDaoH2.update(patient.getAddress());
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, patient.getNombre());
            ps.setString(2, patient.getApellido());
            ps.setString(3, patient.getEmail());
            ps.setString(4, patient.getDni());
            ps.setDate(5, Date.valueOf(patient.getFechaIngreso()));
            ps.setInt(6, patient.getAddress().getId());
            ps.setInt(7, patient.getId());
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
        List<Patient> patients = new ArrayList<>();
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Address address = addressDaoH2.findById(rs.getInt("ADDRESS_ID"));
                patients.add(new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), address));
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

    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM PATIENTS WHERE EMAIL =?";

    @Override
    public Patient findByString(String email) {
        Connection connection = null;
        Patient patient = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_EMAIL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Address address = addressDaoH2.findById(rs.getInt("ADDRESS_ID"));
                patient = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), address);
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
