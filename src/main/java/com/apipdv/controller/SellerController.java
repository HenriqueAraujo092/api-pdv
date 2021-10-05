package com.apipdv.controller;

import com.apipdv.event.ResourceCreatedEvent;
import com.apipdv.model.Customer;
import com.apipdv.model.Seller;
import com.apipdv.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("sellers")
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Seller> list() {
        return sellerRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Seller> create(@Valid @RequestBody Seller seller, HttpServletResponse response) {
        Seller sellerSave = sellerRepository.save(seller);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, sellerSave.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(sellerSave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> searchId(@PathVariable Long id) {

        Optional<Seller> seller = sellerRepository.findById(id);

        if (seller.isPresent()) {
            return new ResponseEntity(seller, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        sellerRepository.delete(sellerRepository.getById(id));
    }

    @PutMapping("/{id}")
    public Seller update(@PathVariable Long id, @Valid @RequestBody Seller newSeller) {

        return sellerRepository.findById(id)
            .map(seller -> {
                seller.setName(newSeller.getName());
                return sellerRepository.save(seller);
            })
            .orElseGet(() -> {
                newSeller.setId(id);
                return sellerRepository.save(newSeller);
            });
    }

}
