package com.apipdv.controller;

import com.apipdv.event.ResourceCreatedEvent;
import com.apipdv.model.Customer;
import com.apipdv.model.Product;
import com.apipdv.repository.ProductRepository;
import com.apipdv.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> list() {
        return productRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> create(@Valid @RequestBody Product product, HttpServletResponse response) {
        Product productSave = productRepository.save(product);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, productSave.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(productSave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> searchId(@PathVariable Long id) {

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return new ResponseEntity(product, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productRepository.delete(productRepository.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product newProduct) {
        Product productSave = productService.update(id, newProduct);
        return ResponseEntity.ok(productSave);
    }

}
