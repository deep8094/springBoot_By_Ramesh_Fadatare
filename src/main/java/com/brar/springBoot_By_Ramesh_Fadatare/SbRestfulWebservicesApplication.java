package com.brar.springBoot_By_Ramesh_Fadatare;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SbRestfulWebservicesApplication {
	//this way it will be configured as spring bean and will be registered in Application context container.
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SbRestfulWebservicesApplication.class, args);
	}

}
