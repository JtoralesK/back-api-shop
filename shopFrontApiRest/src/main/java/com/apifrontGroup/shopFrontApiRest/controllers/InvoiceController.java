package com.apifrontGroup.shopFrontApiRest.controllers;

import com.apifrontGroup.shopFrontApiRest.controllers.models.request.InvoiceRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.ProductRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ChangedEntityStatusResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.InvoiceResponse;
import com.apifrontGroup.shopFrontApiRest.dto.WeekSInvoice;
import com.apifrontGroup.shopFrontApiRest.entity.Invoice;
import com.apifrontGroup.shopFrontApiRest.entity.Product;
import com.apifrontGroup.shopFrontApiRest.error.UserNotFoundException;
import com.apifrontGroup.shopFrontApiRest.service.InvoiceService;
import com.apifrontGroup.shopFrontApiRest.utils.PaginationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @GetMapping("invoices/all")
    Map<String, Object> findAllInvoices
            (
                    @RequestParam(defaultValue = "0") int offset,
                    @RequestParam(defaultValue = "5") int limit
            ){
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Invoice> invoicePage = invoiceService.findAllInvoices(pageable);
        return PaginationUtil.createPageResponse(invoicePage);
    }
    @GetMapping("invoices/all/filter")
    Map<String,java.lang.Object>findAllProductsByName
            (
                    @RequestParam(defaultValue = "0") int offset,
                    @RequestParam(defaultValue = "5") int limit,
                    @RequestParam String searchTerm
            ){
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Invoice> productPage = invoiceService.findInvoicesByCostumer(searchTerm,pageable);
        return PaginationUtil.createPageResponse(productPage);
    }
    @GetMapping("invoices/{id}")
    InvoiceResponse findInvoiceById(@PathVariable long id) throws UserNotFoundException {
        return invoiceService.findInvoiceById(id);
    }
    @GetMapping("invoices/count")
    Long getInvoicesCount()  {
        return invoiceService.getInvoicesCount();
    }
    @GetMapping("invoices/count/today")
    Long getTodayInvoicesCount()  {
        return invoiceService.getTodayInvoicesCount();
    }
    @GetMapping("invoices/total-gained-Today")
    Double getTotalGainedToday()  {
        return invoiceService.getTotalGainedToday();
    }
    @GetMapping("invoices/week")
    List<WeekSInvoice> getWeekSInvoices(){
        return invoiceService.getWeekSInvoices();
    };
    @PostMapping("invoices/save")
    InvoiceResponse saveProduct(@Valid @RequestBody InvoiceRequest invoiceRequest)throws Exception{
        return invoiceService.saveInvoice(invoiceRequest);
    }
    @DeleteMapping("invoices/{id}")
    ChangedEntityStatusResponse deleteProducts(@PathVariable long id) throws UserNotFoundException{
        return invoiceService.changeProductState(id);
    }
}
