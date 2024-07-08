package com.devstack.ecom.Upscale.service;

import com.devstack.ecom.Upscale.dto.response.ResponseProductImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {
    public  ResponseProductImageDto create(MultipartFile file, String productId) throws Exception;
    List<ResponseProductImageDto>  findById(String id);
    void delete(String id);
}
