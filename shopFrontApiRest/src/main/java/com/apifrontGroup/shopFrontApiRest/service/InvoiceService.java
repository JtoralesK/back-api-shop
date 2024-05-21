package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.controllers.models.request.InvoiceRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.ProductRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ChangedEntityStatusResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.InvoiceResponse;
import com.apifrontGroup.shopFrontApiRest.dto.WeekSInvoice;
import com.apifrontGroup.shopFrontApiRest.entity.Invoice;
import com.apifrontGroup.shopFrontApiRest.entity.Product;
import com.apifrontGroup.shopFrontApiRest.error.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceService {
    Page<Invoice> findAllInvoices(Pageable firstPage);
    Page<Invoice> findInvoicesByCostumer(String searchTerm, Pageable pageable);

    InvoiceResponse saveInvoice(InvoiceRequest invoice)throws Exception;
    ChangedEntityStatusResponse changeProductState(Long id)throws UserNotFoundException;
    InvoiceResponse findInvoiceById(Long id) throws UserNotFoundException;

    Long getInvoicesCount();

    Long getTodayInvoicesCount();
    List<WeekSInvoice> getWeekSInvoices();

    Double getTotalGainedToday();
}
