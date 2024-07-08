package com.devstack.ecom.Upscale.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProductImageDto {
    private String propertyId;
    private String directory;
    private String resourceUrl;
    private String hash;
    private String fileName;

}
