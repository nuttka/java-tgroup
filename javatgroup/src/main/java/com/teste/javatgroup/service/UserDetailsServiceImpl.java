package com.teste.javatgroup.service;

import com.teste.javatgroup.entity.Customer;
import com.teste.javatgroup.repository.CustomerRepository;
import com.teste.javatgroup.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final CustomerRepository repository;

    public UserDetailsServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customer = repository.findByEmail(email);

        if (customer.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(customer);
    }
}
