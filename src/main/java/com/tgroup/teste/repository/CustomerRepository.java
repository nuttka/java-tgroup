package com.tgroup.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tgroup.teste.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
