package com.apifrontGroup.shopFrontApiRest.controllers;
import com.apifrontGroup.shopFrontApiRest.dto.StockOfEachProductCategoryDto;
import com.apifrontGroup.shopFrontApiRest.entity.ProductCategory;
import com.apifrontGroup.shopFrontApiRest.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductCategoryController {
    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("productsCategory/all")
    List<ProductCategory> findAllProducts(){
        return productCategoryService.findAllProductCategory();
    }
    @GetMapping("productsCategory/stock")
    List<StockOfEachProductCategoryDto> getStockOfEachProductCategory(){
        return productCategoryService.getStockOfEachProductCategory();
    }

    @GetMapping("productsCategory/save")
    ProductCategory saveProductCategory(@RequestBody ProductCategory productCategory){
        return productCategoryService.saveProductCategory(productCategory);
    }
}
