package com.apipdv.controller;

import com.apipdv.event.ResourceCreatedEvent;
import com.apipdv.model.PaymentType;
import com.apipdv.repository.PaymentTypeRepository;
import com.apipdv.service.PaymentTypeService;
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
@RequestMapping("paymentTypes")
public class PaymentTypeController {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PaymentTypeService paymentTypeService;

    @GetMapping
    public List<PaymentType> list() {
        return paymentTypeRepository.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PaymentType> create(@Valid @RequestBody PaymentType paymentType, HttpServletResponse response) {
        PaymentType paymentTypeSave = paymentTypeRepository.save(paymentType);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, paymentTypeSave.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentTypeSave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentType> searchId(@PathVariable Long id) {

        Optional<PaymentType> paymentType = paymentTypeRepository.findById(id);

        if (paymentType.isPresent()) {
            return new ResponseEntity(paymentType, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        paymentTypeRepository.delete(paymentTypeRepository.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentType> update(@PathVariable Long id, @Valid @RequestBody PaymentType newPaymentType) {
        PaymentType paymentTypeSave = paymentTypeService.update(id, newPaymentType);
        return ResponseEntity.ok(paymentTypeSave);
    }

}
