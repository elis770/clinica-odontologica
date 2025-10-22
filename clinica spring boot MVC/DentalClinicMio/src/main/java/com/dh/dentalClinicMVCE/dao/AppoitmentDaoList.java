package com.dh.dentalClinicMVCE.dao;

import com.dh.dentalClinicMVCE.model.Appoitment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppoitmentDaoList implements IDao<Appoitment> {

    private List<Appoitment> appoitments;
    public AppoitmentDaoList(){
        appoitments = new ArrayList<>();
    }
    @Override
    public Appoitment save(Appoitment appoitment) {
        appoitments.add(appoitment);
        return appoitment;
    }

    @Override
    public Appoitment findById(Integer id) {
        for (Appoitment a : appoitments) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public Appoitment update(Appoitment appoitment) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Appoitment> findAll() {
        return appoitments;
    }

    @Override
    public Appoitment findByString(String string) {
        return null;
    }
}
