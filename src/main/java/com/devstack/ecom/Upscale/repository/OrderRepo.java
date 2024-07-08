package com.devstack.ecom.Upscale.repository;

import com.devstack.ecom.Upscale.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Orders,String> {
    @Query(value = "SELECT * FROM orders WHERE customer_id =?1 ", nativeQuery = true)
    public Page<Orders> findAllWithSearchText(String customerId, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM orders WHERE customer_id =?1", nativeQuery = true)
    public long countAllWithSearchText(String customerId);
}
