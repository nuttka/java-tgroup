package com.tgroup.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tgroup.teste.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Transactional(readOnly=true)
	Customer findByEmail(String email);
}
