package com.devstack.ecom.Upscale.dto.response.paginate;

import com.devstack.ecom.Upscale.dto.response.ResponseCustomerDto;
import com.devstack.ecom.Upscale.dto.response.ResponseProductDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPaginateDto {

    private long count;
    private List<ResponseProductDto> dataList;
}
