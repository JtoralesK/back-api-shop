package com.apifrontGroup.shopFrontApiRest.controllers.models.response;

import com.apifrontGroup.shopFrontApiRest.controllers.models.request.InvoiceItem;
import com.apifrontGroup.shopFrontApiRest.entity.Invoice;
import com.apifrontGroup.shopFrontApiRest.entity.InvoiceXProductDetail;
import com.apifrontGroup.shopFrontApiRest.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceResponse {
    private Invoice invoice;
    private ErrorResponse errorResponse;
    private List<InvoiceItemResponse> invoiceItems;
    public void setInvoiceTotal(float total) {
        if (invoice != null) {
            invoice.setTotal(total);
        }
    }
}
