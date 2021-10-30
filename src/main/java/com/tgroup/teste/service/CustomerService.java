package com.tgroup.teste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgroup.teste.entity.Customer;
import com.tgroup.teste.entity.dto.AddressDTO;
import com.tgroup.teste.entity.dto.CustomerDTO;
import com.tgroup.teste.exception.ObjectNotFoundException;
import com.tgroup.teste.repository.CustomerRepository;

@Service
public class CustomerService {
	
    @Autowired
    private CustomerRepository repository;
    
    @Autowired
    private AddressService addressService;
    
    public Customer create(CustomerDTO customerDTO) {
    	Customer customer = new Customer(customerDTO.getName(), customerDTO.getEmail(), customerDTO.getDocument(), customerDTO.getBirthDate(), customerDTO.getPhone(), customerDTO.getAddresses());
    	
    	repository.save(customer);
    	
    	customerDTO.getAddresses().stream().forEach(address -> {
    		AddressDTO addressDTO = addressService.addressDtoToAddress(address);
    		addressService.create(addressDTO);
    	});
    	
    	return customer;
    }
    
    public Customer findById(Integer id) {
    	return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Customer not found. Id: " + id));
    }
    
    public List<Customer> findAll() {
    	return repository.findAll();
    }
    
    public Customer update(Integer id, CustomerDTO customerDTO) {
    	Customer customer = this.findById(id);
    	
    	customer.setName(customerDTO.getName());
    	customer.setEmail(customerDTO.getEmail());
    	customer.setPhone(customerDTO.getPhone());
    	customer.setAddresses(customerDTO.getAddresses());
    	customer.setBirthDate(customerDTO.getBirthDate());
    	customer.setDocument(customerDTO.getDocument());
    	
    	return repository.save(customer);
    }
    
    public void deleteById(Integer id) {
    	repository.deleteById(id);
    }
}