package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.controllers.models.request.InvoiceItem;
import com.apifrontGroup.shopFrontApiRest.controllers.models.request.InvoiceRequest;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ChangedEntityStatusResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.ErrorResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.InvoiceItemResponse;
import com.apifrontGroup.shopFrontApiRest.controllers.models.response.InvoiceResponse;
import com.apifrontGroup.shopFrontApiRest.dto.WeekSInvoice;
import com.apifrontGroup.shopFrontApiRest.entity.*;
import com.apifrontGroup.shopFrontApiRest.error.UserNotFoundException;
import com.apifrontGroup.shopFrontApiRest.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImp implements InvoiceService{
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PaymentMethodRepository paymentMethodRepository;
    @Autowired
    InvoiceXProductDetailRepository invoiceXProductDetailRepository;

    @Override
    public Page<Invoice> findAllInvoices(Pageable firstPage) {
        return invoiceRepository.findAll(firstPage);
    }
   @Override
   public InvoiceResponse findInvoiceById(Long id)throws UserNotFoundException{
        Invoice invoice = new Invoice();
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        Optional i = invoiceRepository.findById(id);
       if(i.isEmpty()){
           throw new UserNotFoundException("Invoice with ID "+id+" Not Found");
       }
       invoice  = (Invoice) i.get();
       invoiceResponse.setInvoice(invoice);

       List<InvoiceXProductDetail> invoiceXProductDetails = invoiceXProductDetailRepository.findAllInvoiceXProductDetailByInvoiceId( invoice);
       List<InvoiceItemResponse> listItems = new ArrayList<InvoiceItemResponse>();
       for(InvoiceXProductDetail item : invoiceXProductDetails){
           InvoiceItemResponse newItem = new InvoiceItemResponse();
           newItem.setAmount(item.getAmount());
           newItem.setItem(item.getProduct());
           listItems.add(newItem);
       }
       invoiceResponse.setInvoiceItems(listItems);
       return invoiceResponse;
   }

    @Override
    public Long getInvoicesCount() {
        return invoiceRepository.getInvoicesCount();
    }

    @Override
    public Long getTodayInvoicesCount() {
        return invoiceRepository.getTodayInvoicesCount();
    }

    @Override
    public List<WeekSInvoice> getWeekSInvoices() {
        return invoiceRepository.getWeekSInvoices();
    }

    @Override
    public Double getTotalGainedToday() {
        return invoiceRepository.getTotalGainedToday();
    }

    public InvoiceResponse saveInvoice(InvoiceRequest invRequest) throws Exception {
        InvoiceResponse invoiceResponse = InvoiceResponse.builder().build();
       try{
           List<InvoiceItemResponse> invoiceItemsResponse = new ArrayList<InvoiceItemResponse>();
           invoiceResponse.setInvoiceItems(invoiceItemsResponse);
           checkAndFindProduct(invRequest,invoiceItemsResponse);
           Invoice newInvoice = createInvoiceWithCustomer(invRequest);
           setBillerAndPaymentMethod(invRequest,newInvoice);
           setProductsToInvoiceRepository(invRequest,newInvoice,invoiceResponse);
           invoiceResponse.setInvoice(newInvoice);
       }catch (Exception error){
           invoiceResponse.setErrorResponse(ErrorResponse.builder().error(true).messageError(error.getMessage()).build());
           invoiceResponse.setInvoice(null);
       }

        return invoiceResponse;
    }
    public Invoice createInvoiceWithCustomer (InvoiceRequest invRequest){
        Invoice newInvoice = Invoice.builder().
                costumer(invRequest.getCostumer()).build();
        newInvoice.setActive(true);
        newInvoice.setTotal((float)0);
        return newInvoice;
    }
    public  void setBillerAndPaymentMethod(InvoiceRequest invoice,Invoice newInvoice)throws Exception{

                User user = userRepository.findById(invoice.getBiller()).
                orElseThrow(()->new EntityNotFoundException("User with ID" + invoice.getBiller()+" not found"));
        PaymentMethod paymentMethod = paymentMethodRepository.findById(invoice.getPaymentMethodId()).
                orElseThrow(()->new EntityNotFoundException("PaymentMethod with ID" + invoice.getPaymentMethodId()+" not found"));
            newInvoice.setPaymentMethod(paymentMethod);
            newInvoice.setBiller(user);
    }
    public void checkAndFindProduct(InvoiceRequest invoiceRequest,List<InvoiceItemResponse> invoiceItemsResponse) {
       for(InvoiceItem item : invoiceRequest.getInvoiceItems()){
           Product product = productRepository.findById(item.getProduct()).
                   orElseThrow(() -> new EntityNotFoundException("Product with ID" + item.getProduct() + " not found"));
           if (product.getStock() < item.getAmount()) {
               throw new RuntimeException("Product with ID" + item.getProduct() + " not enough stock");
           }
           invoiceItemsResponse.add(InvoiceItemResponse.builder().item(product).amount(item.getAmount()).build());
       }
    }
    public void setProductsToInvoiceRepository(InvoiceRequest invoice, Invoice newInvoice, InvoiceResponse invoiceResponse){
            Invoice invoiceCreated = invoiceRepository.save(newInvoice);
            createInvoiceXProductDetail(invoice,invoiceCreated,invoiceResponse);
            invoiceRepository.save(invoiceCreated);
    }
    void createInvoiceXProductDetail(InvoiceRequest invRequest, Invoice invoiceCreated,InvoiceResponse invoiceResponse){
        float total = 0;
        for(InvoiceItemResponse i : invoiceResponse.getInvoiceItems()){
            Integer amount = i.getAmount();
            total+=amount*i.getItem().getPrice();
            InvoiceXProductDetail newDetail = InvoiceXProductDetail.builder()
                    .product(i.getItem())
                    .invoice(invoiceCreated)
                    .amount(amount).build();
            newDetail.setActive(true);
            InvoiceXProductDetail iXPDetailItem = invoiceXProductDetailRepository.save(newDetail);

        }
        invoiceCreated.setTotal(total);

    }

    @Override
    public ChangedEntityStatusResponse changeProductState(Long id) throws UserNotFoundException {
        return null;
    }




    @Override
    public Page<Invoice> findInvoicesByCostumer(String searchTerm, Pageable pageable) {
        return invoiceRepository.findInvoicesByCostumer(searchTerm,pageable);
    }


}
