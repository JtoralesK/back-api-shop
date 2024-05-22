package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.entity.PaymentMethod;
import com.apifrontGroup.shopFrontApiRest.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImp implements PaymentMethodService{
    @Autowired
    PaymentMethodRepository paymentMethodRepository;
    @Override
    public PaymentMethod savePaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }
}
