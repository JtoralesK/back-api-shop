package com.apifrontGroup.shopFrontApiRest.controllers.models.request;

import com.apifrontGroup.shopFrontApiRest.entity.Product;
import com.apifrontGroup.shopFrontApiRest.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceRequest {

    @NotNull(message = "costumer Required")
    private String costumer;
    @NotNull(message = "biller is required")
    private long biller;
    @NotNull(message = "invoiceItem is required")
    private List<InvoiceItem> invoiceItems;
    @NotNull(message = "paymentMethodId paymentMethod")
    private long paymentMethodId;

}
