package com.devstack.ecom.Upscale.service.impl;

import com.devstack.ecom.Upscale.dto.request.RequestProductDto;
import com.devstack.ecom.Upscale.dto.response.ResponseProductDto;
import com.devstack.ecom.Upscale.dto.response.ResponseProductImageDto;
import com.devstack.ecom.Upscale.dto.response.paginate.ProductPaginateDto;
import com.devstack.ecom.Upscale.entity.Products;
import com.devstack.ecom.Upscale.entity.ProductImageEntity;
import com.devstack.ecom.Upscale.exception.EntryNotFoundException;
import com.devstack.ecom.Upscale.repository.ProductRepo;
import com.devstack.ecom.Upscale.service.ProductService;
import com.devstack.ecom.Upscale.util.FileDataExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final FileDataExtractor dataExtractor;

    @Override
    public void create(RequestProductDto productDto) {
        Products product = Products
                .builder()
                .propertyId(UUID.randomUUID().toString())
                .description(productDto.getDescription())
                .qty(productDto.getQty())
                .unitPrice(productDto.getUnitPrice())
                .build();
        productRepo.save(product);
    }

    @Override
    public ResponseProductDto findById(String id) {
        Optional<Products> selectedProduct = productRepo.findById(id);
        if (selectedProduct.isEmpty()){
            throw new EntryNotFoundException("Product was not found");
        }
        return createResponseProductDto(selectedProduct.get());
    }

    @Override
    public void update(String id, RequestProductDto productDto) {
        Optional<Products> selectedProduct = productRepo.findById(id);
        if (selectedProduct.isEmpty()){
            throw new EntryNotFoundException("Product was not found");
        }
        Products product = Products
                .builder()
                .propertyId(id)
                .description(productDto.getDescription())
                .qty(productDto.getQty())
                .unitPrice(productDto.getUnitPrice())
                .build();
        productRepo.save(product);
    }

    @Override
    public ProductPaginateDto findAll(String searchText, int page, int size) {
        return ProductPaginateDto.builder()
                .dataList(productRepo.findAllWithSearchText(searchText, PageRequest.of(page,size))
                        .stream().map(this::createResponseProductDto).toList())
                .count(
                        productRepo.countAllWithSearchText(searchText)
                )
                .build();
    }

    @Override
    public void delete(String id) {
        productRepo.deleteById(id);
    }

    private ResponseProductDto createResponseProductDto(Products products){
        List<ResponseProductImageDto> images = products.getImages().stream().map(
                this::createResponseProductImage
        ).toList();

        return ResponseProductDto.builder()
                .propertyId(products.getPropertyId())
                .description(products.getDescription())
                .productImages(images)
                .qty(products.getQty())
                .unitPrice(products.getUnitPrice())
                .build();
    }
    private ResponseProductImageDto createResponseProductImage(ProductImageEntity image){
        return ResponseProductImageDto.builder()
                .hash(dataExtractor.byteArrayToString(image.getHash()))
                .directory(dataExtractor.byteArrayToString(image.getDirectory()))
                .fileName(dataExtractor.byteArrayToString(image.getFileName()))
                .propertyId(image.getPropertyId())
                .resourceUrl(dataExtractor.byteArrayToString(image.getResourceUrl()))
                .build();
    }
}
