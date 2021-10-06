package com.apipdv.service;

import com.apipdv.model.PaymentType;
import com.apipdv.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeService {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    public PaymentType update(Long id, PaymentType newPaymentType) {
        return paymentTypeRepository.findById(id)
            .map(paymentType -> {
                paymentType.setName(newPaymentType.getName());
                paymentType.setActive(newPaymentType.getActive());
                return paymentTypeRepository.save(paymentType);
            })
            .orElseGet(() -> {
                throw new EmptyResultDataAccessException(1);
            });
    }

}
