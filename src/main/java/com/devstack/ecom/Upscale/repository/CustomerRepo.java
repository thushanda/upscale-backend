package com.devstack.ecom.Upscale.repository;

import com.devstack.ecom.Upscale.entity.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepo extends JpaRepository<Customers, String> {

    @Query(value = "SELECT * FROM customers WHERE address LIKE CONCAT('%',:searchText,'%') OR email LIKE CONCAT('%',:searchText,'%') OR name LIKE CONCAT('%',:searchText,'%') ORDER BY name DESC", nativeQuery = true)
   public Page<Customers> findAllWithSearchText(@Param("searchText") String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM customers WHERE address LIKE CONCAT('%',:searchText,'%') OR email LIKE CONCAT('%',:searchText,'%') OR name LIKE CONCAT('%',:searchText,'%')", nativeQuery = true)
    public long countAllWithSearchText(@Param("searchText") String searchText);
}
