package com.tgroup.teste.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tgroup.teste.entity.Address;
import com.tgroup.teste.entity.Customer;
import com.tgroup.teste.entity.dto.AddressDTO;
import com.tgroup.teste.entity.dto.CustomerDTO;

@SpringBootTest
public class AddressServiceTest {
	
	@Autowired
	private AddressService addressService;
    
    @Autowired
    private CustomerService customerService;
    
    Customer customer;
    Address initialAddress;
    
    @Before
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

		assertEquals(address.getCity(), city);
		assertEquals(address.getComplement(), complement);
		assertEquals(address.getCustomer().getId(), customerId);
		assertEquals(address.getDistrict(), district);
		assertEquals(address.getNumber(), number);
		assertEquals(address.getState(), state);
		assertEquals(address.getStreet(), street);
		assertEquals(address.getZipCode(), zipCode);
		assertEquals(address.getCountry(), country);
    }
    
    @Test
    public void findByIdTest() {
    	Address address = addressService.findById(initialAddress.getId());
    	
		assertEquals(address.getCity(), initialAddress.getCity());
		assertEquals(address.getComplement(), initialAddress.getComplement());
		assertEquals(address.getCustomer().getId(),initialAddress.getCustomer().getId());
		assertEquals(address.getDistrict(), initialAddress.getDistrict());
		assertEquals(address.getNumber(), initialAddress.getNumber());
		assertEquals(address.getState(), initialAddress.getState());
		assertEquals(address.getStreet(), initialAddress.getStreet());
		assertEquals(address.getZipCode(), initialAddress.getZipCode());
		assertEquals(address.getCountry(), initialAddress.getCountry());
		assertEquals(address.getId(), initialAddress.getId());
    }
    
    
    @Test
    public void findByIdExceptionTest() {
    	Integer id = 15150;
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                	addressService.findById(id);
                }
        );
        
        assertEquals("Address not found. Id: " + id, exception.getMessage());
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
    	
		assertEquals(address.getCity(), city);
		assertEquals(address.getComplement(), complement);
		assertEquals(address.getCustomer().getId(), customerId);
		assertEquals(address.getDistrict(), district);
		assertEquals(address.getNumber(), number);
		assertEquals(address.getState(), state);
		assertEquals(address.getStreet(), street);
		assertEquals(address.getZipCode(), zipCode);
		assertEquals(address.getCountry(), country);
		assertEquals(address.getId(), initialAddress.getId());
    }
    
    
	@Test
	public void deleteByIdTest() {
		addressService.deleteById(initialAddress.getId());
		
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                	addressService.findById(initialAddress.getId());
                }
        );
        
        assertEquals("Address not found. Id: " + initialAddress.getId(), exception.getMessage());
	}

}

