package com.tgroup.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tgroup.teste.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>  {

}
