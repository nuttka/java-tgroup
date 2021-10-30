package com.tgroup.teste.entity.dto;


import javax.validation.constraints.NotNull;

import com.tgroup.teste.entity.Address;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

    private Integer id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Document cannot be null")
    private String document;
    @NotNull(message = "Birth date cannot be null")
    private LocalDate birthDate;
    @NotNull(message = "Phone cannot be null")
    private String phone;
    @NotNull(message = "Addresses cannot be null")
    private List<Address> addresses = new ArrayList<>();

    public CustomerDTO() {};

    public CustomerDTO(Integer id, String name, String email, String document, LocalDate birthDate, String phone, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.birthDate = birthDate;
        this.phone = phone;
        this.addresses = addresses;
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
