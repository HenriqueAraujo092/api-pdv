package com.apipdv.controller;

import com.apipdv.model.Customer;
import com.apipdv.model.Seller;
import com.apipdv.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> list() {
        return customerRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer, HttpServletResponse response) {
        Customer customerSave = customerRepository.save(customer);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(customerSave.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(customerSave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> searchId(@PathVariable Long id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            return new ResponseEntity(customer, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

}
