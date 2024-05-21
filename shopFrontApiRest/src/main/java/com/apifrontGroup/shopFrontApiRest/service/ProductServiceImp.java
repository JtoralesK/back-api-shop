package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.controllers.models.request.ProductRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ChangedEntityStatusResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.UserRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ErrorResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ProductResponse;
import com.apifrontGroup.shopFrontApiRest.dto.ProductSoldDto;
import com.apifrontGroup.shopFrontApiRest.entity.*;
import com.apifrontGroup.shopFrontApiRest.error.UserNotFoundException;
import com.apifrontGroup.shopFrontApiRest.repository.GenderRepository;
import com.apifrontGroup.shopFrontApiRest.repository.ProductCategotyRepository;
import com.apifrontGroup.shopFrontApiRest.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GenderRepository genderRepository;
    @Autowired
    ProductCategotyRepository productCategotyRepository;

    @Override
    public Page<Product> findAllProducts(Pageable firstPage) {
        return productRepository.findAll(firstPage);
    }

    @Override
    public Page<Product> findNewActiveProducts(Pageable firstPage) {
        return  productRepository.findNewActiveProducts(firstPage);
    }

    @Override
    public Page<Product> findAllProductsWithLowStock(Pageable firstPage) {
        return productRepository.findAllProductsWithLowStock(firstPage);
    }

    @Override
    public Optional<Product> findBestProductSold() {
        return productRepository.findBestProductSold();
    }

    @Override
    public Product saveProduct(ProductRequest product) throws EntityNotFoundException {
        Gender gender = genderRepository.findById(product.getGenderId())
                .orElseThrow(() -> new EntityNotFoundException("Gender with ID " + product.getGenderId() + " not found"));

        ProductCategory productCategory = productCategotyRepository.findById(product.getProductCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("product_category with ID " + product.getProductCategoryId() + " not found"));

        Product newProduct = Product.builder()
                .stock(product.getStock())
                .gender(gender)
                .ProductCategory(productCategory)
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
        newProduct.setActive(true);
        return productRepository.save(newProduct);

    }

    @Override
    public ProductResponse updateUser(Long id, ProductRequest product) {
        ProductResponse productResponse = new ProductResponse();
        try{
            Product newProduct = findProductbyID(id);
            updateProductFiles(product,newProduct);
            setCategoryAndGenre(product,newProduct);
            productResponse.setProduct(productRepository.save(newProduct));
        }catch (Exception err){
            productResponse.setErrorResponse(ErrorResponse.builder().messageError(err.getMessage()).build());
        }

      return productResponse;
    }
    public void setCategoryAndGenre(ProductRequest product, Product newProduct){
        if(product.getGenderId()>0){
            Gender gender = genderRepository.findById(product.getGenderId())
                    .orElseThrow(() -> new EntityNotFoundException("Gender with ID " + product.getGenderId() + " not found"));

        }
        if(product.getProductCategoryId()>0){
            ProductCategory productCategory = productCategotyRepository.findById(product.getProductCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("product_category with ID " + product.getProductCategoryId() + " not found"));

        }

    }
    public void updateProductFiles(ProductRequest product, Product newProduct){
        if (Objects.nonNull(product.getName()) && !"".equalsIgnoreCase(product.getName())) {
            newProduct.setName(product.getName());
        }
        if (Objects.nonNull(product.getImage()) && !"".equalsIgnoreCase(product.getImage())) {
            newProduct.setImage(product.getImage());
        }
        if (Objects.nonNull(product.getDescription()) && !"".equalsIgnoreCase(product.getDescription())) {
            newProduct.setDescription(product.getDescription());
        }
        if (Objects.nonNull(product.getPrice()) && product.getPrice()>0) {
            newProduct.setPrice(product.getPrice());
        }
        if (Objects.nonNull(product.getStock()) && product.getStock()>0) {
            newProduct.setStock(product.getStock());
        }
    }
    public Product findProductbyID(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new RuntimeException("Product with id "+id+" not found");
        }
        return product.get();
    }

    @Override
    public ChangedEntityStatusResponse changeProductState(Long id) throws UserNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new UserNotFoundException("Error, Product not found");
        }
        product.get().setActive(!product.get().isActive());
        productRepository.save(product.get());
        ChangedEntityStatusResponse response = new ChangedEntityStatusResponse();
        response.setId(product.get().getId());
        response.setMessage("Este product ha cambiado su estado correctamente");
        return response;
    }

    @Override
    public Product findProductById(Long id) throws UserNotFoundException {
        return null;
    }

    @Override
    public Page<Product> findProductsByName(String searchTerm, Pageable pageable) {
        return productRepository.findProductsByName(searchTerm,pageable);
    }

    @Override
    public Long getProductsCount() {
        return productRepository.getProductsCount();
    }
}
