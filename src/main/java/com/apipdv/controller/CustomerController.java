package com.apipdv.controller;

import com.apipdv.event.ResourceCreatedEvent;
import com.apipdv.model.Customer;
import com.apipdv.model.Seller;
import com.apipdv.repository.CustomerRepository;
import com.apipdv.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
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

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> list() {
        return customerRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer, HttpServletResponse response) {
        Customer customerSave = customerRepository.save(customer);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, customerSave.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(customerSave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> searchId(@PathVariable Long id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            return new ResponseEntity(customer, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerRepository.delete(customerRepository.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @Valid @RequestBody Customer newCustomer) {
        Customer customerSave = customerService.update(id, newCustomer);
        return ResponseEntity.ok(customerSave);
    }
}
