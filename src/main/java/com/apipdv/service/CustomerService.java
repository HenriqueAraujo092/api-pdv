package com.apipdv.service;

import com.apipdv.model.Customer;
import com.apipdv.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer update(Long id, Customer newCustomer) {
        return customerRepository.findById(id)
            .map(customer -> {
                customer.setName(newCustomer.getName());
                customer.setActive(newCustomer.getActive());
                customer.setAddres(newCustomer.getAddres());
                return customerRepository.save(customer);
            })
            .orElseGet(() -> {
                throw new EmptyResultDataAccessException(1);
//                newCustomer.setId(id);
//                return customerRepository.save(newCustomer);
            });
    }
}
