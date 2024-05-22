package com.apifrontGroup.shopFrontApiRest.controllers.models;

import com.apifrontGroup.shopFrontApiRest.entity.Gender;
import com.apifrontGroup.shopFrontApiRest.entity.PaymentMethod;
import com.apifrontGroup.shopFrontApiRest.service.PaymentMethodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentMethodController {
    @Autowired
    PaymentMethodService paymentMethodService;
    @PostMapping("paymentMethod/save")
    PaymentMethod save(@Valid @RequestBody PaymentMethod paymentMethod){
        return paymentMethodService.savePaymentMethod(paymentMethod);
    }
}
