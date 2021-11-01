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

import com.tgroup.teste.entity.Address;
import com.tgroup.teste.entity.dto.AddressDTO;
import com.tgroup.teste.service.AddressService;

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/address")
public class AddressController {
	
    @Autowired
    private AddressService service;

    @ApiOperation(value="findAll")
    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
    
    @ApiOperation(value="findById")
    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation(value="create")
    @PostMapping
    public ResponseEntity<Address> create(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok().body(service.create(addressDTO));
    }

    @ApiOperation(value="delete")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
    	service.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @ApiOperation(value="update")
    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Integer id, @RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok().body(service.update(id, addressDTO));
    }

}
