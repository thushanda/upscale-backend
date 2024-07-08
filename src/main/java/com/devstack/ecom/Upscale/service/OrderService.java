package com.devstack.ecom.Upscale.service;

import com.devstack.ecom.Upscale.dto.request.RequestOrderDto;
import com.devstack.ecom.Upscale.dto.response.ResponseOrderDto;
import com.devstack.ecom.Upscale.dto.response.paginate.OrderPaginateDto;

public interface OrderService {
    public void create(RequestOrderDto orderDto);
    public ResponseOrderDto findById(String id);
    public void update(String id, RequestOrderDto orderDto);
    OrderPaginateDto findAll(String customerId, int page, int size);
    void delete(String id);
}
