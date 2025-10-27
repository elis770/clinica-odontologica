package com.dh.dentalClinicMVCE.Reposittory;

import com.dh.dentalClinicMVCE.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDentistRepo extends JpaRepository<Dentist, Long> {

}
