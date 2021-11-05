package com.tgroup.teste.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tgroup.teste.entity.Address;
import com.tgroup.teste.entity.Customer;
import com.tgroup.teste.entity.dto.AddressDTO;
import com.tgroup.teste.entity.dto.CustomerDTO;
import com.tgroup.teste.entity.enums.Profile;
import com.tgroup.teste.exception.ObjectNotFoundException;
import com.tgroup.teste.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Autowired
    private CustomerRepository repository;
    
    @Autowired
    private AddressService addressService;
    
    public Customer create(CustomerDTO customerDTO) {
    	Customer customer = new Customer(customerDTO.getName(), customerDTO.getEmail(), customerDTO.getDocument(), customerDTO.getBirthDate(), customerDTO.getPhone(), new ArrayList<>(), bCryptPasswordEncoder.encode(customerDTO.getPassword()));
    	
    	Customer savedCustomer = repository.save(customer);
    	
    	List<Address> addresses = new ArrayList<>();
    	customerDTO.getAddresses().stream().forEach(addressWithoutCustomerDTO -> {
    		AddressDTO addressDTO = addressWithoutCustomerDTO.toAddressDTO(savedCustomer.getId());
    		addresses.add(addressService.create(addressDTO));
    	});
    	
    	return savedCustomer;
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
    	
    	List<Address> addresses = customerDTO.getAddresses().stream().map(addressWithoutCustomerDTO -> addressWithoutCustomerDTO.toAddress(customer)).collect(Collectors.toList());
    	
    	customer.setAddresses(addresses);
    	customer.setBirthDate(customerDTO.getBirthDate());
    	customer.setDocument(customerDTO.getDocument());
    	
    	return repository.save(customer);
    }
    
    public void deleteById(Integer id) {
    	repository.deleteById(id);
    }

	public Customer createAdmin(CustomerDTO customerDTO) {
    	Customer customer = new Customer(customerDTO.getName(), customerDTO.getEmail(), customerDTO.getDocument(), customerDTO.getBirthDate(), customerDTO.getPhone(), new ArrayList<>(), bCryptPasswordEncoder.encode(customerDTO.getPassword()));
    	customer.addProfile(Profile.ADMIN);
    	Customer savedCustomer = repository.save(customer);
    	
    	customerDTO.getAddresses().stream().forEach(addressWithoutCustomerDTO -> {
    		AddressDTO addressDTO = addressWithoutCustomerDTO.toAddressDTO(savedCustomer.getId());
    		addressService.create(addressDTO);
    	});
    	
    	return savedCustomer;
	}
}
