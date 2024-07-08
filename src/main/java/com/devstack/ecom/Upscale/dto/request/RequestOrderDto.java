package com.devstack.ecom.Upscale.dto.request;

import com.devstack.ecom.Upscale.entity.enums.PaymentType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RequestOrderDto {
    private Date createdDate;
    private double total;
    private int qty;
    private PaymentType paymentType;
    private String customers;
    private String products;
}
