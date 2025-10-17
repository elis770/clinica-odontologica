package com.Dh.ClinicaOdontologicaFinal.repository;

import com.Dh.ClinicaOdontologicaFinal.entities.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepo extends JpaRepository<Dentist, Integer> {
}
