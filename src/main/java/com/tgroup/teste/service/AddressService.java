package com.tgroup.teste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgroup.teste.entity.Address;
import com.tgroup.teste.entity.Customer;
import com.tgroup.teste.entity.dto.AddressDTO;
import com.tgroup.teste.exception.ObjectNotFoundException;
import com.tgroup.teste.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    
    @Autowired
    private CustomerService customerService;
    
    
    public Address create(AddressDTO addressDTO) {
    	Customer customer = customerService.findById(addressDTO.getCustomerId());
    	
    	Address address = new Address(addressDTO.getZipCode(), addressDTO.getStreet(), addressDTO.getNumber(), addressDTO.getComplement(), addressDTO.getDistrict(), addressDTO.getCity(), addressDTO.getState(), addressDTO.getCountry(), customer);
    	
    	return repository.save(address);
    }
    
    public Address findById(Integer id) {
    	return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Address not found. Id: " + id));
    }
    
    public List<Address> findAll() {
    	return repository.findAll();
    }
    
    public Address update(Integer id, AddressDTO addressDTO) {
    	Address address = this.findById(id);
    	Customer customer = customerService.findById(addressDTO.getCustomerId());
    	
    	address.setZipCode(addressDTO.getZipCode());
    	address.setStreet(addressDTO.getStreet());
    	address.setNumber(addressDTO.getNumber());
    	address.setComplement(addressDTO.getComplement());
    	
    	address.setDistrict(addressDTO.getDistrict());
    	address.setCity(addressDTO.getCity());
    	address.setState(addressDTO.getState());
    	address.setCountry(addressDTO.getCountry());
    	address.setCustomer(customer);
    	
    	return repository.save(address);
    }
    
    public void deleteById(Integer id) {
    	repository.deleteById(id);
    }


}
