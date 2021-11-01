package com.tgroup.teste.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tgroup.teste.entity.Address;
import com.tgroup.teste.entity.Customer;
import com.tgroup.teste.entity.enums.Profile;
import com.tgroup.teste.repository.CustomerRepository;

@Service
public class DBService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void instantiateTestDatabase() {
		LocalDate date = LocalDate.of(2000, 6, 3);
		List<Address> addresses = new ArrayList<>();
		
		Customer customer = new Customer("Lucas Augusto", "lucasaaaguiar0@gmail.com", "MG17406510", date, "31993665144", addresses, bCryptPasswordEncoder.encode("123"));
		customer.addProfile(Profile.ADMIN);
		
		customerRepository.save(customer);
	}

}
