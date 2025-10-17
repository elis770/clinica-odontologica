package com.dh.dentalClinicMVCE.dao;

import com.dh.dentalClinicMVCE.model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    T save(T t) throws SQLException;

    T findById(Integer id);

    T update(T t);

    void delete(Integer id);

    List<T> findAll();
    T findByString(String string);
}
