package com.paytakcode.inventorymanager.api.v1.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paytakcode.inventorymanager.api.v1.data.entity.Material;
import com.paytakcode.inventorymanager.api.v1.data.entity.Product;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterial;

/**
 * Product Material Repository
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-25 오전 9:17
 */
public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Long> {
	List<ProductMaterial> findAllById_Product_id(Long productId);

	Optional<ProductMaterial> findByIdProductAndIdMaterial(Product product, Material material);

	void deleteByIdProductAndIdMaterial(Product product, Material material);
}
