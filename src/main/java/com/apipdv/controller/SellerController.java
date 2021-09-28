package com.apipdv.controller;

import com.apipdv.model.Seller;
import com.apipdv.repository.SellerRepository;
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
@RequestMapping("sellers")
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;

    @GetMapping
    public List<Seller> list() {
        return sellerRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Seller> create(@Valid @RequestBody Seller seller, HttpServletResponse response) {
        Seller sellerSave = sellerRepository.save(seller);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(sellerSave.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(sellerSave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> searchId(@PathVariable Long id) {

        Optional<Seller> seller = sellerRepository.findById(id);

        if (seller.isPresent()) {
            return new ResponseEntity(seller, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

}
