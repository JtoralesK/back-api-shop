package com.apifrontGroup.shopFrontApiRest.repository;

import com.apifrontGroup.shopFrontApiRest.dto.StockOfEachProductCategoryDto;
import com.apifrontGroup.shopFrontApiRest.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategotyRepository extends JpaRepository<ProductCategory,Long> {
    @Query(value = "SELECT product_category as productCategory, SUM(stock) AS totalStock\n" +
            "FROM products\n" +
            "GROUP BY product_category;\n",nativeQuery = true)
    List<StockOfEachProductCategoryDto> getStockOfEachProductCategory();
}
