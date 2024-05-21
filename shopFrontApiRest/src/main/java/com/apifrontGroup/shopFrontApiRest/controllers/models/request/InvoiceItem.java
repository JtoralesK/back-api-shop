package com.apifrontGroup.shopFrontApiRest.controllers.models.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceItem {
    @NotNull(message = "product Required")
    private long product;
    @NotNull(message = "amount Required")
    private Integer amount;
}
