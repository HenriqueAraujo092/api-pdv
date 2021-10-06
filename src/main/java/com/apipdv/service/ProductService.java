package com.apipdv.service;

import com.apipdv.model.Customer;
import com.apipdv.model.Product;
import com.apipdv.repository.CustomerRepository;
import com.apipdv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product update(Long id, Product newProduct) {
        return productRepository.findById(id)
            .map(product -> {
                product.setName(newProduct.getName());
                product.setSale_price(newProduct.getSale_price());
                product.setCost_price(newProduct.getCost_price());
                product.setActive(newProduct.getActive());
                return productRepository.save(product);
            })
            .orElseGet(() -> {
                throw new EmptyResultDataAccessException(1);
            });
    }

}
