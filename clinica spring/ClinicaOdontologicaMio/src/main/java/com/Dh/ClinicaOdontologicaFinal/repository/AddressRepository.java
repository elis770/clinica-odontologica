package com.Dh.ClinicaOdontologicaFinal.repository;

import com.Dh.ClinicaOdontologicaFinal.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}