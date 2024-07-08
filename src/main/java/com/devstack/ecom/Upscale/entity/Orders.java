package com.devstack.ecom.Upscale.entity;

import com.devstack.ecom.Upscale.entity.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "orders")
public class Orders {
    @Id
    @Column(name = "property_id", nullable = false, length = 80)
    private String propertyId;
    @Column(name = "created_date", columnDefinition = "DATETIME", nullable = false)
    private Date createdDate;
    private double total;
    private int qty;

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "property_Id")
    private Customers customers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "property_Id")
    private Products products;
}
