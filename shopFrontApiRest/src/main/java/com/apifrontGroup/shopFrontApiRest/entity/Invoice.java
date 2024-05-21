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
        name="invoices"
)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "costumer Required")
    private String costumer;

    @NotNull(message = "total required")
    private Float total;

    @NotNull(message = "generatedBy is required")
    @ManyToOne
    @JoinColumn(name = "biller", referencedColumnName = "id")
    private User biller;

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

    @NotNull(message = "paymentMethod required")
    @ManyToOne
    @JoinColumn(name = "paymentMethod", referencedColumnName = "id")
    private PaymentMethod paymentMethod;
}
