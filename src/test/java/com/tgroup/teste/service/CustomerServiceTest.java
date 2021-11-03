package com.tgroup.teste.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tgroup.teste.entity.Customer;
import com.tgroup.teste.entity.dto.AddressWithoutCustomerDTO;
import com.tgroup.teste.entity.dto.CustomerDTO;
import com.tgroup.teste.entity.enums.Profile;

@SpringBootTest
public class CustomerServiceTest {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AddressService addressService;
	
	AddressWithoutCustomerDTO addressWithoutCustomerDTO;
	
	Customer customer;
	
	@Before
	public void initTest() {
		String city = "city";
    	String zipCode = "25864582";
    	String street = "teste";
    	String state = "state";
    	String country = "country";
    	Integer number = 123123;
    	String complement = "ap x";
    	String district = "district";
    	addressWithoutCustomerDTO = new AddressWithoutCustomerDTO(zipCode, street, number, complement, district, city, state, country);
    	
		String name = "Initial Teste";
		String email = "iTeste@teste.com";
		String document = "321";
		LocalDate birthDate = LocalDate.now();
		String phone = "9512345687";
		String password = "321";
		
		List<AddressWithoutCustomerDTO> addresses = new ArrayList<>();
		addresses.add(addressWithoutCustomerDTO);
		
		CustomerDTO customerDTO = new CustomerDTO(name, email, document, birthDate, phone, addresses, password);
		
		customer = customerService.create(customerDTO);
	}
	
	
	@Test
	public void createTest() {
		String name = "Teste";
		String email = "Teste@teste.com";
		String document = "123321123";
		LocalDate birthDate = LocalDate.now();
		String phone = "11965481235";
		String password = "123";
		
		List<AddressWithoutCustomerDTO> addresses = new ArrayList<>();
		addresses.add(addressWithoutCustomerDTO);
		
		CustomerDTO customerDTO = new CustomerDTO(name, email, document, birthDate, phone, addresses, password);
		
		Customer customer = customerService.create(customerDTO);
		
		assertEquals(customer.getAddresses(), addresses);
		assertEquals(customer.getBirthDate(), birthDate);
		assertEquals(customer.getDocument(), document);
		assertEquals(customer.getEmail(), email);
		assertEquals(customer.getName(), name);
		assertEquals(customer.getPassword(), password);
	}
	
	@Test
	public void findByIdTest() {
		assertEquals(customer.getId(), customerService.findById(customer.getId()));
	}
	
	@Test
	public void findByIdExceptionTest() {
    	Integer id = 98564;
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                	customerService.findById(id);
                }
        );
        
        assertEquals("Customer not found. Id: " + id, exception.getMessage());
	}
	
	@Test
	public void updateTest() {
		String name = "Initial Teste Modified";
		String email = "iTesteModified@teste.com";
		String document = "321123";
		LocalDate birthDate = LocalDate.now();
		String phone = "9512345687";
		String password = "321";
		
		Set<Profile> profiles = new HashSet<>();
		profiles.add(Profile.CUSTOMER);
		
		CustomerDTO customerDTO = new CustomerDTO(name, email, document, birthDate, phone, new ArrayList<>(), password);
		
		Customer updatedCustomer = customerService.update(customer.getId(), customerDTO);
		
		assertEquals(updatedCustomer.getAddresses(), new ArrayList<>());
		assertEquals(updatedCustomer.getBirthDate(), birthDate);
		assertEquals(updatedCustomer.getDocument(), document);
		assertEquals(updatedCustomer.getEmail(), email);
		assertEquals(updatedCustomer.getName(), name);
		assertEquals(updatedCustomer.getPassword(), password);
		assertEquals(customer.getProfiles(), profiles);
	}
	
	@Test
	public void deleteByIdTest() {
		customerService.deleteById(customer.getId());
		
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                	customerService.findById(customer.getId());
                }
        );
        
        assertEquals("Address not found. Id: " + customer.getId(), exception.getMessage());
	}
	
	@Test
	public void createAdminTest() {
		String name = "Teste";
		String email = "Teste@teste.com";
		String document = "123321123";
		LocalDate birthDate = LocalDate.now();
		String phone = "11965481235";
		String password = "123";
		
		Set<Profile> profiles = new HashSet<>();
		profiles.add(Profile.ADMIN);
		profiles.add(Profile.CUSTOMER);
		
		List<AddressWithoutCustomerDTO> addresses = new ArrayList<>();
		addresses.add(addressWithoutCustomerDTO);
		
		CustomerDTO customerDTO = new CustomerDTO(name, email, document, birthDate, phone, addresses, password);
		
		Customer customer = customerService.createAdmin(customerDTO);
		
		assertEquals(customer.getAddresses(), addresses);
		assertEquals(customer.getBirthDate(), birthDate);
		assertEquals(customer.getDocument(), document);
		assertEquals(customer.getEmail(), email);
		assertEquals(customer.getName(), name);
		assertEquals(customer.getPassword(), password);
		assertEquals(customer.getProfiles(), profiles);
	}
	

}
