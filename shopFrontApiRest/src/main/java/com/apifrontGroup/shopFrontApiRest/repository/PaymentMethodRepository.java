package com.apifrontGroup.shopFrontApiRest.repository;

import com.apifrontGroup.shopFrontApiRest.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
