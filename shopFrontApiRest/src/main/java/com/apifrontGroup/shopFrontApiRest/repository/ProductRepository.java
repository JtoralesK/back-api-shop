package com.apifrontGroup.shopFrontApiRest.repository;

import com.apifrontGroup.shopFrontApiRest.dto.ProductSoldDto;
import com.apifrontGroup.shopFrontApiRest.entity.Product;
import com.apifrontGroup.shopFrontApiRest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:searchTerm%")
    Page<Product> findProductsByName(@Param("searchTerm") String searchTerm, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.active = true ORDER BY p.createdAt DESC")
    Page<Product> findNewActiveProducts(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.active = true and stock < 10 ORDER BY p.stock ASC")
    Page<Product> findAllProductsWithLowStock(Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "JOIN InvoiceXProductDetail ipd ON p.id = ipd.product.id " +
            "GROUP BY p " +
            "ORDER BY SUM(ipd.amount) DESC limit 1")
    Optional<Product> findBestProductSold();
    @Query("SELECT COUNT(*) FROM Product")
    Long getProductsCount();




}
