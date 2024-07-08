package com.devstack.ecom.Upscale.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RequestProductDto {
    private Long qty;
    private Double unitPrice;
    private String description;
}
//