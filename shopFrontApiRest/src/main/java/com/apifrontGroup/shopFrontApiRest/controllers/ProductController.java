package com.apifrontGroup.shopFrontApiRest.controllers;

import com.apifrontGroup.shopFrontApiRest.controllers.models.request.ProductRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.UserRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ChangedEntityStatusResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ProductResponse;
import com.apifrontGroup.shopFrontApiRest.dto.ProductSoldDto;
import com.apifrontGroup.shopFrontApiRest.entity.Product;
import com.apifrontGroup.shopFrontApiRest.entity.User;
import com.apifrontGroup.shopFrontApiRest.error.UserNotFoundException;
import com.apifrontGroup.shopFrontApiRest.service.ProductService;
import com.apifrontGroup.shopFrontApiRest.service.UserService;
import com.apifrontGroup.shopFrontApiRest.utils.PaginationUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("products/all")
    Map<String, Object> findAllProducts
            (
                    @RequestParam(defaultValue = "0") int offset,
                    @RequestParam(defaultValue = "5") int limit
            ){
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Product> productPage = productService.findAllProducts(pageable);
        return PaginationUtil.createPageResponse(productPage);
    }
    @GetMapping("products/new")
    Map<String, Object> findAllNewActiveProducts
            (
                    @RequestParam(defaultValue = "0") int offset,
                    @RequestParam(defaultValue = "5") int limit
            ){
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Product> productPage = productService.findNewActiveProducts(pageable);
        return PaginationUtil.createPageResponse(productPage);
    }
    @GetMapping("products/lowStock")
    Map<String, Object> findAllProductsLowStock
            (
                    @RequestParam(defaultValue = "0") int offset,
                    @RequestParam(defaultValue = "5") int limit
            ){
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Product> productPage = productService.findAllProductsWithLowStock(pageable);
        return PaginationUtil.createPageResponse(productPage);
    }
    @GetMapping("products/all/filter")
    Map<String,java.lang.Object>findAllProductsByName
            (
                    @RequestParam(defaultValue = "0") int offset,
                    @RequestParam(defaultValue = "5") int limit,
                    @RequestParam String searchTerm
            ){
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Product> productPage = productService.findProductsByName(searchTerm,pageable);
        return PaginationUtil.createPageResponse(productPage);
    }
    @GetMapping("products/bestSeller")
    Optional<Product> findBestProductSold(){
        return productService.findBestProductSold();
    }
    @GetMapping("products/count")
    Long getProductsCount(){
        return productService.getProductsCount();
    }
    @GetMapping("products/{id}")
    Product findUserById(@PathVariable long id) throws UserNotFoundException {
        return productService.findProductById(id);
    }

    @PostMapping("products/save")
    Product saveProduct(@Valid @RequestBody ProductRequest productRequest)throws Exception{
        return productService.saveProduct(productRequest);
    }

    @PutMapping("products/{id}")
    ProductResponse updateProduct(@PathVariable long id, @RequestBody ProductRequest product){
        return productService.updateUser(id,product);
    }
    @DeleteMapping("products/{id}")
    ChangedEntityStatusResponse deleteProducts(@PathVariable long id) throws UserNotFoundException{
        return productService.changeProductState(id);
    }
}
