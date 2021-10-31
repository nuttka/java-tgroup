package com.tgroup.teste.entity.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.tgroup.teste.entity.Address;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

    private Integer id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @NotEmpty(message = "Document cannot be empty")
    private String document;
    @NotEmpty(message = "Birth date cannot be empty")
    private LocalDate birthDate;
    @NotEmpty(message = "Phone cannot be empty")
    private String phone;
    @NotNull(message = "Addresses cannot be null")
    private List<Address> addresses = new ArrayList<>();

    public CustomerDTO() {};

    public CustomerDTO(Integer id, String name, String email, String document, LocalDate birthDate, String phone, List<Address> addresses, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.birthDate = birthDate;
        this.phone = phone;
        this.addresses = addresses;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
