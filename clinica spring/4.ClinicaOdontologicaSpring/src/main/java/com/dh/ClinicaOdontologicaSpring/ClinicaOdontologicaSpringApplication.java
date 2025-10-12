package com.dh.ClinicaOdontologicaSpring;

import com.dh.ClinicaOdontologicaSpring.dao.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaOdontologicaSpringApplication {

	public static void main(String[] args) {
		BD.createTables();
		SpringApplication.run(ClinicaOdontologicaSpringApplication.class, args);
	}

}
