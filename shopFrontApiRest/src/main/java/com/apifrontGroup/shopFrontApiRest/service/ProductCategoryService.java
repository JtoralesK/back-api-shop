package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.dto.StockOfEachProductCategoryDto;
import com.apifrontGroup.shopFrontApiRest.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
     List<ProductCategory> findAllProductCategory();
     List<StockOfEachProductCategoryDto> getStockOfEachProductCategory();

     ProductCategory saveProductCategory(ProductCategory productCategory);
}
