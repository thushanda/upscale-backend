package com.devstack.ecom.Upscale.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "products")
public class Products {
    @Id
    @Column(name = "property_id")
    private String propertyId;
    private Long qty;
    private Double unitPrice;
    private String description;

    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<ProductImageEntity> images = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private List<Orders> orderEntities = new ArrayList<>();
}
