package com.tgroup.teste;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tgroup.teste.service.DBService;

@SpringBootApplication
public class TesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteApplication.class, args);
	}
	
	@Autowired
	private DBService dbService; 
	
	@PostConstruct
    private void init() {
		dbService.instantiateTestDatabase();
    }

}
