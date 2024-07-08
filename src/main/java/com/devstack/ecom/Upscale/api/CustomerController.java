package com.devstack.ecom.Upscale.api;

import com.devstack.ecom.Upscale.dto.request.RequestCustomerDto;
import com.devstack.ecom.Upscale.service.CustomerService;
import com.devstack.ecom.Upscale.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<StandardResponse>  createCustomer(@RequestBody RequestCustomerDto dto){
        System.out.println(dto);
        System.out.println(dto.getAddress());
        customerService.create(dto);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Customer was created!...",
                        null
                ), HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getById(@PathVariable String id){
        return new ResponseEntity<>(
                new StandardResponse(200,"Customer data!..",customerService.findById(id)),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateCustomer(@PathVariable String id ,@RequestBody RequestCustomerDto dto){
        customerService.update(id,dto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Customer was updated!..",null),
                HttpStatus.CREATED
        );

    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponse> findAllCustomer(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){
        return new ResponseEntity<>(
                new StandardResponse(200,"Customer list!..",
                        customerService.findAll(searchText,page,size)),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable String id){
        customerService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Customer Deleted!..",null),
                HttpStatus.NO_CONTENT
        );
    }

}
