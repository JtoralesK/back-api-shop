package com.apifrontGroup.shopFrontApiRest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name="products"
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @NotNull(message = "ProductCategory Required")
    @ManyToOne
    @JoinColumn(name = "ProductCategory", referencedColumnName = "id")
    private ProductCategory ProductCategory;

    @NotNull(message = "gender Required")
    @ManyToOne
    @JoinColumn(name = "gender", referencedColumnName = "id")
    private Gender gender;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date createdAt;
    @PrePersist
    protected void prePersist() {
        createdAt = new Date();
    }
    @Column(name = "active")
    private boolean active ;


}
