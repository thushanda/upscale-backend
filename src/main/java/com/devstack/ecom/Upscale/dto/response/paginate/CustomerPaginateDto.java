package com.devstack.ecom.Upscale.dto.response.paginate;

import com.devstack.ecom.Upscale.dto.response.ResponseCustomerDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPaginateDto {

    private long count;
    private List<ResponseCustomerDto> dataList;
}
