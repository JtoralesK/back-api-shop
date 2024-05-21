package com.apifrontGroup.shopFrontApiRest.controllers.models.response;

import com.apifrontGroup.shopFrontApiRest.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Product product;
    private ErrorResponse errorResponse;
}
