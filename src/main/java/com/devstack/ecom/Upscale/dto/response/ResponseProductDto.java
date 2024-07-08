package com.devstack.ecom.Upscale.dto.response;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProductDto {
    private String propertyId;
    private Long qty;
    private Double unitPrice;
    private String description;
    private List<ResponseProductImageDto> productImages;
}
