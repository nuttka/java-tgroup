package com.tgroup.teste.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tgroup.teste.entity.Address;
import com.tgroup.teste.entity.Customer;
import com.tgroup.teste.entity.dto.AddressWithoutCustomerDTO;
import com.tgroup.teste.entity.dto.CustomerDTO;
import com.tgroup.teste.entity.enums.Profile;
import com.tgroup.teste.exception.ObjectNotFoundException;

@SpringBootTest
public class CustomerServiceTest {
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	AddressWithoutCustomerDTO addressWithoutCustomerDTO;
	
	Customer customer;
	
	@BeforeEach
	public void initTest() {
		String city = "city";
    	String zipCode = "25864582";
    	String street = "teste";
    	String state = "state";
    	String country = "country";
    	Integer number = 123123;
    	String complement = "ap y";
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
	
	@AfterEach
	public void afterTest() {
		try {
			customer.getAddresses().stream().forEach(address -> addressService.deleteById(address.getId()));
			customerService.deleteById(customer.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Create test")
	public void createTest() {
		String name = "Teste";
		String email = "Teste@teste.com";
		String document = "123321123";
		LocalDate birthDate = LocalDate.now();
		String phone = "11965481235";
		String password = "123";
		
		Set<Profile> profiles = new HashSet<>();
		profiles.add(Profile.CUSTOMER);
		
		List<AddressWithoutCustomerDTO> addressesWithoutCustomerDTO = new ArrayList<>();
		addressesWithoutCustomerDTO.add(addressWithoutCustomerDTO);
		
		CustomerDTO customerDTO = new CustomerDTO(name, email, document, birthDate, phone, addressesWithoutCustomerDTO, password);
		Customer savedCustomer = customerService.create(customerDTO);
		Customer findCustomer = customerService.findById(savedCustomer.getId());

		Address addressSaved = findCustomer.getAddresses().get(0);

		Assertions.assertEquals(findCustomer.getAddresses().size(), addressesWithoutCustomerDTO.size());
		Assertions.assertEquals(addressSaved.getNumber(), addressWithoutCustomerDTO.getNumber());
		Assertions.assertEquals(addressSaved.getCity(), addressWithoutCustomerDTO.getCity());
		Assertions.assertEquals(addressSaved.getComplement(), addressWithoutCustomerDTO.getComplement());
		Assertions.assertEquals(addressSaved.getCountry(), addressWithoutCustomerDTO.getCountry());
		Assertions.assertEquals(addressSaved.getDistrict(), addressWithoutCustomerDTO.getDistrict());
		Assertions.assertEquals(addressSaved.getState(), addressWithoutCustomerDTO.getState());
		Assertions.assertEquals(addressSaved.getStreet(), addressWithoutCustomerDTO.getStreet());
		Assertions.assertEquals(addressSaved.getZipCode(), addressWithoutCustomerDTO.getZipCode());
		
		Assertions.assertEquals(findCustomer.getBirthDate(), birthDate);
		Assertions.assertEquals(findCustomer.getDocument(), document);
		Assertions.assertEquals(findCustomer.getEmail(), email);
		Assertions.assertEquals(findCustomer.getName(), name);
		Assertions.assertTrue(bCryptPasswordEncoder.matches(password, savedCustomer.getPassword()));
		Assertions.assertEquals(findCustomer.getProfiles(), profiles);
		
		customerService.deleteById(findCustomer.getId());
	}
	
	@Test
	@DisplayName("Find by id test")
	public void findByIdTest() {
		Assertions.assertEquals(customer.getId(), customerService.findById(customer.getId()).getId());
	}
	
	@Test
	@DisplayName("Find by exception test")
	public void findByIdExceptionTest() {
    	Integer id = 98564;
        Throwable exception = Assertions.assertThrows(
                ObjectNotFoundException.class, () -> {
                	customerService.findById(id);
                }
        );
        
        Assertions.assertEquals("Customer not found. Id: " + id, exception.getMessage());
	}
	
	@Test
	@DisplayName("Update test")
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
		
		assertArrayEquals(updatedCustomer.getAddresses().toArray(), new ArrayList<>().toArray());
		Assertions.assertEquals(updatedCustomer.getBirthDate(), birthDate);
		Assertions.assertEquals(updatedCustomer.getDocument(), document);
		Assertions.assertEquals(updatedCustomer.getEmail(), email);
		Assertions.assertEquals(updatedCustomer.getName(), name);
		Assertions.assertTrue(bCryptPasswordEncoder.matches(password,updatedCustomer.getPassword()));
		Assertions.assertEquals(customer.getProfiles(), profiles);
	}
	
	@Test
	@DisplayName("Delete by id test")
	public void deleteByIdTest() {
		customerService.deleteById(customer.getId());
		
        Throwable exception = Assertions.assertThrows(
        		ObjectNotFoundException.class, () -> {
                	customerService.findById(customer.getId());
                }
        );
        
        Assertions.assertEquals("Customer not found. Id: " + customer.getId(), exception.getMessage());
	}
	
	@Test
	@DisplayName("Create admin test")
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
		
		List<AddressWithoutCustomerDTO> addressesWithoutCustomerDTO = new ArrayList<>();
		addressesWithoutCustomerDTO.add(addressWithoutCustomerDTO);
		
		CustomerDTO customerDTO = new CustomerDTO(name, email, document, birthDate, phone, addressesWithoutCustomerDTO, password);
		Customer savedCustomer = customerService.createAdmin(customerDTO);
		Customer findCustomer = customerService.findById(savedCustomer.getId());

		Address addressSaved = findCustomer.getAddresses().get(0);

		Assertions.assertEquals(addressSaved.getNumber(), addressWithoutCustomerDTO.getNumber());
		Assertions.assertEquals(addressSaved.getCity(), addressWithoutCustomerDTO.getCity());
		Assertions.assertEquals(addressSaved.getComplement(), addressWithoutCustomerDTO.getComplement());
		Assertions.assertEquals(addressSaved.getCountry(), addressWithoutCustomerDTO.getCountry());
		Assertions.assertEquals(addressSaved.getDistrict(), addressWithoutCustomerDTO.getDistrict());
		Assertions.assertEquals(addressSaved.getState(), addressWithoutCustomerDTO.getState());
		Assertions.assertEquals(addressSaved.getStreet(), addressWithoutCustomerDTO.getStreet());
		Assertions.assertEquals(addressSaved.getZipCode(), addressWithoutCustomerDTO.getZipCode());
		
		Assertions.assertEquals(findCustomer.getBirthDate(), birthDate);
		Assertions.assertEquals(findCustomer.getDocument(), document);
		Assertions.assertEquals(findCustomer.getEmail(), email);
		Assertions.assertEquals(findCustomer.getName(), name);
		Assertions.assertTrue(bCryptPasswordEncoder.matches(password, savedCustomer.getPassword()));
		Assertions.assertEquals(findCustomer.getProfiles(), profiles);

		customerService.deleteById(savedCustomer.getId());
	}
	

}
