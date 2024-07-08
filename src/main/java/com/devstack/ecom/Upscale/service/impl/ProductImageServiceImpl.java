package com.devstack.ecom.Upscale.service.impl;

import com.amazonaws.services.dlm.model.InternalServerException;
import com.devstack.ecom.Upscale.dto.response.ResponseProductImageDto;
import com.devstack.ecom.Upscale.entity.Products;
import com.devstack.ecom.Upscale.entity.ProductImageEntity;
import com.devstack.ecom.Upscale.exception.EntryNotFoundException;
import com.devstack.ecom.Upscale.repository.ProductImageRepo;
import com.devstack.ecom.Upscale.repository.ProductRepo;
import com.devstack.ecom.Upscale.service.FileService;
import com.devstack.ecom.Upscale.service.ProductImageService;
import com.devstack.ecom.Upscale.util.CommonFileSavedBinaryDataDto;
import com.devstack.ecom.Upscale.util.FileDataExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Value("${aws.bucket_name}")
    private String bucketName;
    private final ProductImageRepo productImageRepo;
    private final ProductRepo productRepo;
    private final FileService fileService;
    private final FileDataExtractor dataExtractor;

    @Override
    public ResponseProductImageDto create(MultipartFile file, String productId) throws Exception {
        CommonFileSavedBinaryDataDto dataDto = null;

        try {
            Optional<Products> selectedId = productRepo.findById(productId);
            if (selectedId.isEmpty()) {
                throw new EntryNotFoundException("product was not found....!");
            }

            Products product = selectedId.get();

            dataDto = fileService.create(file, "upscale/product_images/", bucketName);

            ProductImageEntity productImageEntity = ProductImageEntity
                    .builder()
                    .propertyId(UUID.randomUUID().toString())
                    .directory(
                            dataDto.getDirectory().getBytes()
                    )
                    .hash(dataExtractor.blobToByteArray(
                            dataDto.getHash()
                    ))
                    .fileName(dataExtractor.blobToByteArray(
                            dataDto.getFileName()
                    ))
                    .resourceUrl(dataExtractor.blobToByteArray(
                            dataDto.getResourceUrl()
                    ))
                    .products(product)
                    .build();
            productImageRepo.save(productImageEntity);
            return createResponseProductImageDto(productImageEntity);
        } catch (Exception e) {
            fileService.delete(dataExtractor.extractActualFileName(new InputStreamReader(
                    dataDto.getFileName().getBinaryStream()
            )), dataDto.getDirectory(), bucketName);
            throw new InternalServerException("Something went wrong..");
        }
    }

    @Override
    public List<ResponseProductImageDto> findById(String id) {
        return productImageRepo.findByProducts_PropertyId(id).stream()
                .map(this::createResponseProductImageDto)
                .collect(Collectors.toList());


    }

    @Override
    public void delete(String id) {

    }


    private ResponseProductImageDto createResponseProductImageDto(ProductImageEntity image) {
        return ResponseProductImageDto.builder()
                .hash(dataExtractor.byteArrayToString(image.getHash()))
                .directory(dataExtractor.byteArrayToString(image.getDirectory()))
                .fileName(dataExtractor.byteArrayToString(image.getFileName()))
                .propertyId(image.getPropertyId())
                .resourceUrl(dataExtractor.byteArrayToString(image.getResourceUrl()))
                .build();
    }
}
