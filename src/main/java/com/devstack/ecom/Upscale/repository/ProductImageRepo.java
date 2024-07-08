package com.devstack.ecom.Upscale.repository;

import com.devstack.ecom.Upscale.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepo extends JpaRepository<ProductImageEntity,String> {
    List<ProductImageEntity> findByProducts_PropertyId(String productId);
}
