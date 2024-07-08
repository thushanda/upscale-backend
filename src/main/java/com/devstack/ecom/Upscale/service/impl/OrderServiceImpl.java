package com.devstack.ecom.Upscale.service.impl;

import com.devstack.ecom.Upscale.dto.request.RequestOrderDto;
import com.devstack.ecom.Upscale.dto.response.ResponseOrderDto;
import com.devstack.ecom.Upscale.dto.response.paginate.OrderPaginateDto;
import com.devstack.ecom.Upscale.entity.Customers;
import com.devstack.ecom.Upscale.entity.Orders;
import com.devstack.ecom.Upscale.entity.Products;
import com.devstack.ecom.Upscale.exception.EntryNotFoundException;
import com.devstack.ecom.Upscale.repository.CustomerRepo;
import com.devstack.ecom.Upscale.repository.OrderRepo;
import com.devstack.ecom.Upscale.repository.ProductRepo;
import com.devstack.ecom.Upscale.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepo orderRepo;
    private CustomerRepo customerRepo;
    private ProductRepo productRepo;

    @Override
    public void create(RequestOrderDto orderDto) {
        Optional<Customers> selectedCustomer = customerRepo.findById(orderDto.getCustomers());
        if (selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Found");
        }
        Optional<Products> selectedProduct = productRepo.findById(orderDto.getProducts());
        if (selectedProduct.isEmpty()){
            throw new EntryNotFoundException("Product was not found");
        }

        Orders orders = Orders
                .builder()
                .propertyId(UUID.randomUUID().toString())
                .createdDate(orderDto.getCreatedDate())
                .qty(orderDto.getQty())
                .customers(selectedCustomer.get())
                .products(selectedProduct.get())
                .total(orderDto.getTotal())
                .build();
        orderRepo.save(orders);
    }

    @Override
    public ResponseOrderDto findById(String id) {
        Optional<Orders> selectedOrder = orderRepo.findById(id);
        if (selectedOrder.isEmpty()){
            throw new EntryNotFoundException("Order Not Found....!");
        }
        return createOrderDto(selectedOrder.get());
    }

    @Override
    public void update(String id, RequestOrderDto orderDto) {
        // no need update
    }

    @Override
    public OrderPaginateDto findAll(String customerId, int page, int size) {
        return OrderPaginateDto.builder()
                .dataList(orderRepo.findAllWithSearchText(customerId, PageRequest.of(page,size))
                        .stream().map(this::createOrderDto).toList())
                .count(
                        orderRepo.countAllWithSearchText(customerId)
                )
                .build();
    }

    @Override
    public void delete(String id) {
        orderRepo.deleteById(id);
    }

    private ResponseOrderDto createOrderDto(Orders orders){
        return ResponseOrderDto
                .builder()
                .propertyId(orders.getPropertyId())
                .createdDate(orders.getCreatedDate())
                .customers(orders.getCustomers().getName())
                .products(orders.getProducts().getDescription())
                .total(orders.getTotal())
                .qty(orders.getQty())
                .build();
    }


}
