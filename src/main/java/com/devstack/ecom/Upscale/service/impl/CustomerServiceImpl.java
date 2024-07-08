package com.devstack.ecom.Upscale.service.impl;

import com.devstack.ecom.Upscale.dto.request.RequestCustomerDto;
import com.devstack.ecom.Upscale.dto.response.ResponseCustomerDto;
import com.devstack.ecom.Upscale.dto.response.paginate.CustomerPaginateDto;
import com.devstack.ecom.Upscale.entity.Customers;
import com.devstack.ecom.Upscale.exception.EntryNotFoundException;
import com.devstack.ecom.Upscale.repository.CustomerRepo;
import com.devstack.ecom.Upscale.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public void create(RequestCustomerDto customerDto) {
        Customers customers = Customers.builder()
                .propertyId(UUID.randomUUID().toString())
                .name(customerDto.getName())
                .address(customerDto.getAddress())
                .email(customerDto.getEmail())
                .phone(customerDto.getPhone())
                .isActive(customerDto.isActive())
                .build();
        customerRepo.save(customers);
    }

    @Override
    public ResponseCustomerDto findById(String id) {
        Optional<Customers> selectedCustomer = customerRepo.findById(id);
        if (selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Found");
        }
        return toResponseCustomerDto(selectedCustomer.get());
    }

    @Override
    public void update(String id, RequestCustomerDto customerDto) {
        Optional<Customers> selectedCustomer = customerRepo.findById(id);
        if (selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Found");
        }

        Customers customers = selectedCustomer.get();
        customers.setName(customerDto.getName());
        customers.setEmail(customerDto.getEmail());
        customers.setAddress(customerDto.getAddress());
        customers.setPhone(customerDto.getPhone());
        customers.setActive(customerDto.isActive());

        customerRepo.save(customers);
    }

    @Override
    public CustomerPaginateDto findAll(String searchText, int page, int size) {
        return CustomerPaginateDto.builder()
                .dataList(customerRepo.findAllWithSearchText(searchText, PageRequest.of(page,size))
                        .stream().map(this::toResponseCustomerDto).toList())
                .count(
                        customerRepo.countAllWithSearchText(searchText)
                )
                .build();
    }

    @Override
    public void delete(String id) {
        customerRepo.deleteById(id);
    }

    //===========================
    private ResponseCustomerDto toResponseCustomerDto(Customers customers){
        return ResponseCustomerDto.builder()
                .propertyId(customers.getPropertyId())
                .address(customers.getAddress())
                .phone(customers.getPhone())
                .name(customers.getName())
                .isActive(customers.isActive())
                .email(customers.getEmail())
                .build();
    }
}
