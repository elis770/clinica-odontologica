package com.Dh.ClinicaOdontologicaFinal.repository;

import com.Dh.ClinicaOdontologicaFinal.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

    List<Patient> findByLastname(String lastname);

    @Query("SELECT p from Patient p WHERE p.name=?1")
    Optional<Patient> findByName(String name);
}