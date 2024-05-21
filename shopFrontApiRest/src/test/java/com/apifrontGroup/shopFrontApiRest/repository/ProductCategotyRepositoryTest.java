package com.apifrontGroup.shopFrontApiRest.repository;

import com.apifrontGroup.shopFrontApiRest.entity.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProductCategotyRepositoryTest {
@Autowired
ProductCategotyRepository productCategotyRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void test(){
        ProductCategory p = new ProductCategory();
        p.setProductCategory("trousers");
        p.setId(1L);
       ProductCategory newP =  productCategotyRepository.save(p);
        System.out.println(newP);
    }
}