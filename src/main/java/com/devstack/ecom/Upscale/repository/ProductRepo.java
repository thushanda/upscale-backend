package com.devstack.ecom.Upscale.repository;

import com.devstack.ecom.Upscale.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Products, String> {
    @Query(value = "SELECT * FROM products WHERE description LIKE CONCAT('%', :searchText, '%') OR property_id LIKE CONCAT('%', :searchText, '%') ORDER BY description DESC", nativeQuery = true)
    public Page<Products> findAllWithSearchText(@Param("searchText") String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM products WHERE description LIKE CONCAT('%', :searchText, '%')", nativeQuery = true)
    public long countAllWithSearchText(@Param("searchText") String searchText);

}
