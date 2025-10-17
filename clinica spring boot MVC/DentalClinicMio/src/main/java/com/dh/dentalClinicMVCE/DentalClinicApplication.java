package com.dh.dentalClinicMVCE;

import com.dh.dentalClinicMVCE.dao.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DentalClinicApplication {
	public static void main(String[] args) {
		DB.createTables();
		SpringApplication.run(DentalClinicApplication.class, args);
	}

}