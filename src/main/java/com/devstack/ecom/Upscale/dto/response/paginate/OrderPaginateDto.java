package com.devstack.ecom.Upscale.dto.response.paginate;

import com.devstack.ecom.Upscale.dto.response.ResponseCustomerDto;
import com.devstack.ecom.Upscale.dto.response.ResponseOrderDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPaginateDto {

    private long count;
    private List<ResponseOrderDto> dataList;
}
