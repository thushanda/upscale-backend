package com.devstack.ecom.Upscale.service;

import com.devstack.ecom.Upscale.dto.request.RequestProductDto;
import com.devstack.ecom.Upscale.dto.response.ResponseProductDto;
import com.devstack.ecom.Upscale.dto.response.paginate.ProductPaginateDto;

public interface ProductService {
    public void create(RequestProductDto productDto);
    public ResponseProductDto findById(String id);
    public void update(String id, RequestProductDto productDto);
    ProductPaginateDto findAll(String searchText, int page, int size);
    void delete(String id);
}
