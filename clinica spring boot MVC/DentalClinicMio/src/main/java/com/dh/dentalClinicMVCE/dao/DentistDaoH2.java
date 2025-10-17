package com.dh.dentalClinicMVCE.dao;

import com.dh.dentalClinicMVCE.model.Dentist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class DentistDaoH2 implements IDao<Dentist> {
    private static final String SQL_INSERT = "INSERT INTO DENTISTS (REGISTRATION, NAME, LAST_NAME) VALUES(?,?,?)";
    private static final String SQL_SELECT_ID = "SELECT * FROM DENTISTS WHERE ID =?";
    private static final String SQL_UPDATE = "UPDATE DENTISTS SET REGISTRATION=?, NAME=?, LAST_NAME=? WHERE ID=?";
    private static final String SQL_DELETE = "DELETE FROM DENTISTS WHERE ID =?";
    private static final String SQL_FIND_ALL = "SELECT * FROM DENTISTS";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM DENTISTS WHERE NAME =?";

    @Override
    public Dentist save(Dentist dentist) {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, dentist.getRegistration());
            ps.setString(2, dentist.getName());
            ps.setString(3, dentist.getLastName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                dentist.setId(rs.getInt(1));
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
        return dentist;
    }

    @Override
    public Dentist findById(Integer id) {
        Connection connection = null;
        Dentist dentist = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dentist = new Dentist(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
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
        return dentist;
    }

    @Override
    public Dentist update(Dentist dentist) {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
            ps.setInt(1, dentist.getRegistration());
            ps.setString(2, dentist.getName());
            ps.setString(3, dentist.getLastName());
            ps.setInt(4, dentist.getId());
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
        return dentist;
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
    public List<Dentist> findAll() {
        Connection connection = null;
        List<Dentist> dentists = new ArrayList<>();
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dentists.add(new Dentist(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
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
        return dentists;
    }

    @Override
    public Dentist findByString(String value) {
        Connection connection = null;
        Dentist dentist = null;
        try {
            connection = DB.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_NAME);
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dentist = new Dentist(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
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
        return dentist;
    }
}