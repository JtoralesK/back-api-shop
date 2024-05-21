package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.dto.StockOfEachProductCategoryDto;
import com.apifrontGroup.shopFrontApiRest.entity.ProductCategory;
import com.apifrontGroup.shopFrontApiRest.repository.ProductCategotyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImp implements ProductCategoryService{
    @Autowired
    ProductCategotyRepository productCategotyRepository;
    @Override
    public List<ProductCategory> findAllProductCategory() {
        return productCategotyRepository.findAll();
    }

    @Override
    public List<StockOfEachProductCategoryDto> getStockOfEachProductCategory() {
        return productCategotyRepository.getStockOfEachProductCategory();
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategotyRepository.save(productCategory);
    }
}
