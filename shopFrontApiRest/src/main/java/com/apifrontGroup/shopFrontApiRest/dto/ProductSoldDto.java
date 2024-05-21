package com.apifrontGroup.shopFrontApiRest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSoldDto {
    private Long productId;
    private Integer totalSold;
}
