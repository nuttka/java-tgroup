package com.tgroup.teste.service;


import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tgroup.teste.entity.Address;
import com.tgroup.teste.entity.Customer;
import com.tgroup.teste.entity.dto.AddressDTO;
import com.tgroup.teste.entity.dto.CustomerDTO;
import com.tgroup.teste.exception.ObjectNotFoundException;


@SpringBootTest
public class AddressServiceTest {

	@Autowired
	private AddressService addressService;
    
	@Autowired
    private CustomerService customerService;
	
	private Customer customer;
	
	private Address initialAddress;
    
    @BeforeEach
    public void initTest() {
    	CustomerDTO customerDTO = new CustomerDTO("Teste", "teste@teste.com", "123456", LocalDate.now(), "55864533265", new ArrayList<>(), "teste");
		customer = customerService.create(customerDTO);
    	
    	String city = "city";
    	String zipCode = "25864582";
    	String street = "teste";
    	String state = "state";
    	String country = "country";
    	Integer number = 123123;
    	String complement = null;
    	Integer customerId = customer.getId();
    	String district = "district";
    	AddressDTO addressDTO = new AddressDTO(zipCode, street, number, complement, district, city, state, country, customerId);
    	initialAddress = addressService.create(addressDTO);
    }
    
    @Test
    @DisplayName("Create test")
    public void createTest() {
    	String city = "sao paulo";
    	String zipCode = "11654654";
    	String street = "x";
    	String state = "São Paulo";
    	String country = "Brasil";
    	Integer number = 953;
    	String complement = null;
    	Integer customerId = customer.getId();
    	String district = "y";
    	
    	AddressDTO addressDTO = new AddressDTO(zipCode, street, number, complement, district, city, state, country, customerId);
		Address address = addressService.create(addressDTO);

		Assertions.assertEquals(address.getCity(), city);
		Assertions.assertEquals(address.getComplement(), complement);
		Assertions.assertEquals(address.getCustomer().getId(), customerId);
		Assertions.assertEquals(address.getDistrict(), district);
		Assertions.assertEquals(address.getNumber(), number);
		Assertions.assertEquals(address.getState(), state);
		Assertions.assertEquals(address.getStreet(), street);
		Assertions.assertEquals(address.getZipCode(), zipCode);
		Assertions.assertEquals(address.getCountry(), country);
    }
    
    @Test
    public void findByIdTest() {
    	Address address = addressService.findById(initialAddress.getId());
    	
    	Assertions.assertEquals(address.getCity(), initialAddress.getCity());
    	Assertions.assertEquals(address.getComplement(), initialAddress.getComplement());
    	Assertions.assertEquals(address.getCustomer().getId(),initialAddress.getCustomer().getId());
    	Assertions.assertEquals(address.getDistrict(), initialAddress.getDistrict());
    	Assertions.assertEquals(address.getNumber(), initialAddress.getNumber());
    	Assertions.assertEquals(address.getState(), initialAddress.getState());
    	Assertions.assertEquals(address.getStreet(), initialAddress.getStreet());
    	Assertions.assertEquals(address.getZipCode(), initialAddress.getZipCode());
    	Assertions.assertEquals(address.getCountry(), initialAddress.getCountry());
    	Assertions.assertEquals(address.getId(), initialAddress.getId());
    }
    
    
    @Test
    public void findByIdExceptionTest() {
    	Integer id = 15150;
        Throwable exception = Assertions.assertThrows(
        		ObjectNotFoundException.class, () -> {
                	addressService.findById(id);
                }
        );
        
        Assertions.assertEquals("Address not found. Id: " + id, exception.getMessage());
    }
    
    @Test
    public void updateTest() {
    	String city = "city";
    	String zipCode = "123";
    	String street = "street";
    	String state = "minas";
    	String country = "brasil";
    	Integer number = 1;
    	String complement = "ap 205";
    	Integer customerId = customer.getId();
    	String district = "santa inês";
    	AddressDTO addressDTO = new AddressDTO(zipCode, street, number, complement, district, city, state, country, customerId);
    	
    	Address address = addressService.update(initialAddress.getId(), addressDTO);
    	
    	Assertions.assertEquals(address.getCity(), city);
    	Assertions.assertEquals(address.getComplement(), complement);
    	Assertions.assertEquals(address.getCustomer().getId(), customerId);
    	Assertions.assertEquals(address.getDistrict(), district);
    	Assertions.assertEquals(address.getNumber(), number);
    	Assertions.assertEquals(address.getState(), state);
    	Assertions.assertEquals(address.getStreet(), street);
    	Assertions.assertEquals(address.getZipCode(), zipCode);
    	Assertions.assertEquals(address.getCountry(), country);
    	Assertions.assertEquals(address.getId(), initialAddress.getId());
    }

}