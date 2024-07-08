package com.devstack.ecom.Upscale.api;

import com.devstack.ecom.Upscale.dto.request.RequestProductDto;
import com.devstack.ecom.Upscale.service.ProductService;
import com.devstack.ecom.Upscale.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<StandardResponse>  createProduct(@RequestBody RequestProductDto dto){
        System.out.println(dto);
        System.out.println(dto.getDescription());
        productService.create(dto);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Product was created!...",
                        null
                ), HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getById(@PathVariable String id){
        return new ResponseEntity<>(
                new StandardResponse(200,"Customer data!..",productService.findById(id)),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateProduct(@PathVariable String id ,@RequestBody RequestProductDto dto){
        productService.update(id,dto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Product was updated!..",null),
                HttpStatus.CREATED
        );

    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponse> findAllProduct(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){
        return new ResponseEntity<>(
                new StandardResponse(200,"Product list!..",
                        productService.findAll(searchText,page,size)),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteProduct(@PathVariable String id){
        productService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Product was Deleted!..",null),
                HttpStatus.NO_CONTENT
        );
    }

}
