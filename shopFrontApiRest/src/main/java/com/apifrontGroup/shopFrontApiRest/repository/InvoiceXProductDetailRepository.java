package com.apifrontGroup.shopFrontApiRest.repository;

import com.apifrontGroup.shopFrontApiRest.entity.Invoice;
import com.apifrontGroup.shopFrontApiRest.entity.InvoiceXProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceXProductDetailRepository extends JpaRepository<InvoiceXProductDetail,Long> {

    @Query("SELECT i FROM InvoiceXProductDetail i WHERE i.invoice = :invoice")
    List<InvoiceXProductDetail> findAllInvoiceXProductDetailByInvoiceId(@Param("invoice") Invoice invoice);

}
