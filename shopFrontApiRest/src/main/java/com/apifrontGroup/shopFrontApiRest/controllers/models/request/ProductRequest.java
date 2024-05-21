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
public class ProductRequest {

    @NotNull(message = "name Required")
    private String name;
    @NotNull(message = "description Required")
    private String description;
    @NotNull(message = "price Required")
    private Float price;
    @NotNull(message = "stock Required")
    private Integer stock;
    @NotNull(message = "image Required")
    private String image;
    @NotNull(message = "productCategoryId Required")
    private long productCategoryId;
    @NotNull(message = "genderId Required")
    private long genderId;
}
