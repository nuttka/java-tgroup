package com.tgroup.teste.entity.dto;


import javax.validation.constraints.NotNull;

import com.tgroup.teste.entity.Address;
import com.tgroup.teste.entity.Customer;


public class AddressWithoutCustomerDTO {

    private Integer id;
    @NotNull(message = "Zip code cannot be null")
    private String zipCode;
    @NotNull(message = "Street cannot be null")
    private String street;
    @NotNull(message = "Number cannot be null")
    private Integer number;

    private String complement;
    @NotNull(message = "District cannot be null")
    private String district;
    @NotNull(message = "City cannot be null")
    private String city;
    @NotNull(message = "State cannot be null")
    private String state;
    @NotNull(message = "Country cannot be null")
    private String country;

    public AddressWithoutCustomerDTO() {};

    public AddressWithoutCustomerDTO(String zipCode, String street, Integer number, String complement, String district, String city, String state, String country) {
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public AddressDTO toAddressDTO(Integer id) {
    	return new AddressDTO(zipCode, street, number, complement, district, city, state, country, id);
    }
    
    public Address toAddress(Customer customer) {
    	return new Address(zipCode, street, number, complement, district, city, state, country, customer);
    }
    
}
