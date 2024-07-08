package com.devstack.ecom.Upscale.api;

import com.devstack.ecom.Upscale.dto.request.RequestCustomerDto;
import com.devstack.ecom.Upscale.dto.request.RequestOrderDto;
import com.devstack.ecom.Upscale.service.CustomerService;
import com.devstack.ecom.Upscale.service.OrderService;
import com.devstack.ecom.Upscale.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<StandardResponse>  createOrder(@RequestBody RequestOrderDto dto){
        System.out.println(dto);
        System.out.println(dto.getCustomers());
        orderService.create(dto);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Order was created!...",
                        null
                ), HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getById(@PathVariable String id){
        return new ResponseEntity<>(
                new StandardResponse(200,"Order data!..",orderService.findById(id)),
                HttpStatus.OK
        );
    }


    @GetMapping("/list")
    public ResponseEntity<StandardResponse> findAllOrders(
            @RequestParam String customerId,
            @RequestParam int page,
            @RequestParam int size
    ){
        return new ResponseEntity<>(
                new StandardResponse(200,"Orders list!..",
                        orderService.findAll(customerId,page,size)),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteOrder(@PathVariable String id){
        orderService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Order Deleted!..",null),
                HttpStatus.NO_CONTENT
        );
    }

}
