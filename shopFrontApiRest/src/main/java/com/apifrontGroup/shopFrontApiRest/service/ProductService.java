package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.controllers.models.request.ProductRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ChangedEntityStatusResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.UserRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ProductResponse;
import com.apifrontGroup.shopFrontApiRest.dto.ProductSoldDto;
import com.apifrontGroup.shopFrontApiRest.entity.Product;
import com.apifrontGroup.shopFrontApiRest.error.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> findAllProducts(Pageable firstPage);
    Page<Product> findNewActiveProducts(Pageable firstPage);
    Page<Product> findAllProductsWithLowStock(Pageable firstPage);
    Optional<Product> findBestProductSold();

    Product saveProduct(ProductRequest product)throws Exception;
    ProductResponse updateUser(Long id, ProductRequest product);
    ChangedEntityStatusResponse changeProductState(Long id)throws UserNotFoundException;
    Product findProductById(Long id) throws UserNotFoundException;
    Page<Product> findProductsByName(String searchTerm, Pageable pageable);
    Long getProductsCount();
}
