package com.devstack.ecom.Upscale.service;

import com.devstack.ecom.Upscale.dto.request.RequestCustomerDto;
import com.devstack.ecom.Upscale.dto.response.ResponseCustomerDto;
import com.devstack.ecom.Upscale.dto.response.paginate.CustomerPaginateDto;

public interface CustomerService {
    public void create(RequestCustomerDto customerDto);
    public ResponseCustomerDto findById(String id);
    public void update(String id, RequestCustomerDto customerDto);
    CustomerPaginateDto findAll(String searchText, int page, int size);
    void delete(String id);
}
