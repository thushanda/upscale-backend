package com.devstack.ecom.Upscale.api;

import com.devstack.ecom.Upscale.dto.response.ResponseProductImageDto;
import com.devstack.ecom.Upscale.service.ProductImageService;
import com.devstack.ecom.Upscale.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @PostMapping("/{product}")
    public ResponseEntity<StandardResponse>  createProduct(@RequestParam("productImage") MultipartFile file,
                                                           @PathVariable String product) throws Exception {
        System.out.println(file);
        ResponseProductImageDto createdProductImage = productImageService.create(file, product);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Product image was created!...",
                        createdProductImage
                ), HttpStatus.CREATED
        );
    }

    @GetMapping("/{product}")
    public ResponseEntity<StandardResponse> getProductImages(@PathVariable String product){
        List<ResponseProductImageDto> images = productImageService.findById(product);
        return new ResponseEntity<>(
                new StandardResponse(200, "Product images retrieved!", images),
                HttpStatus.OK
        );
    }


}
