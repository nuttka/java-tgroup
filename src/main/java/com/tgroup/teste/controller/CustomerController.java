package com.tgroup.teste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import com.tgroup.teste.entity.Customer;
import com.tgroup.teste.entity.dto.CustomerDTO;
import com.tgroup.teste.service.CustomerService;

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerController {
	
    @Autowired
    private CustomerService service;

    @ApiOperation(value="findAll")
    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @ApiOperation(value="findById")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation(value="create")
    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok().body(service.create(customerDTO));
    }

    @ApiOperation(value="createAdmin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<Customer> createAdmin(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok().body(service.createAdmin(customerDTO));
    }

    @ApiOperation(value="delete")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
    	service.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @ApiOperation(value="update")
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok().body(service.update(id, customerDTO));
    }


}
