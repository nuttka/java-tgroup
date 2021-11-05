package com.tgroup.teste.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgroup.teste.entity.Address;
import com.tgroup.teste.entity.Customer;
import com.tgroup.teste.entity.dto.AddressDTO;
import com.tgroup.teste.entity.dto.CustomerDTO;
import com.tgroup.teste.service.AddressService;
import com.tgroup.teste.service.CustomerService;


@SpringBootTest
@WebAppConfiguration
public class AddressControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AddressService addressService;

	@MockBean
	private CustomerService customerService;
	
	Customer customer;
	
	
	
	@BeforeEach
	public void initTest() {  
		CustomerDTO customerDTO = new CustomerDTO("teste", "teste@teste.com", "123456", LocalDate.now(), "2195135271", new ArrayList<>(), "teste");
		
		customer = customerService.create(customerDTO);
	}
	
	@Test
	public void createTest() throws Exception {
		
		Address address = new Address("15975321", "street", 1, "ap 999", "district", "city", "state", "country", customer);
		address.setId(1);
		String inputInJson = this.mapToJson(address);
		String url = "/address";
		
		Mockito.when(addressService.create(Mockito.any(AddressDTO.class))).thenReturn(address);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(url)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		
		Assertions.assertEquals(outputInJson, inputInJson);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	
	@Test
	public void findByIdTest() throws Exception {
		
		Address address = new Address("15975321", "street", 1, "ap 999", "district", "city", "state", "country", customer);
		address.setId(1);
		
		Mockito.when(addressService.findById(Mockito.anyInt())).thenReturn(address);
		
		String URI = "/address/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(address);
		String outputInJson = result.getResponse().getContentAsString();
		Assertions.assertEquals(outputInJson, expectedJson);
	}
	
	
	private String mapToJson(Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}

}
