package com.devstack.ecom.Upscale.dto.response;


import com.devstack.ecom.Upscale.entity.enums.PaymentType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOrderDto {
    private String propertyId;
    private Date createdDate;
    private double total;
    private int qty;
    private PaymentType paymentType;
    private String customers;
    private String products;
}
