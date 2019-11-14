package com.tfg.tiendadeelectronica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude=HibernateJpaAutoConfiguration.class)
public class ElectronicShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicShopApplication.class, args);
	}

}

