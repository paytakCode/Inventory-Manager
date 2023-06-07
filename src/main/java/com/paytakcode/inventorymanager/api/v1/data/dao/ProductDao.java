package com.paytakcode.inventorymanager.api.v1.data.dao;

import java.util.List;
import java.util.Optional;

import com.paytakcode.inventorymanager.api.v1.data.emum.ProductionStatus;
import com.paytakcode.inventorymanager.api.v1.data.entity.Product;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterial;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterialId;
import com.paytakcode.inventorymanager.api.v1.data.entity.Production;
import com.paytakcode.inventorymanager.api.v1.data.entity.Supplier;

/**
 * Product DAO
 * @Author 김태산
 * @Version 0.5.0
 * @Since 2023-05-25 오전 9:10
 */

public interface ProductDao {

	Product saveProduct(Product product);

	List<Product> findProductList();

	Optional<Product> findProductById(Long productId);

	Product getProductReferenceById(Long productId);

	void deleteProductById(Long productId);

	ProductMaterial saveProductMaterial(ProductMaterial productMaterial);

	List<ProductMaterial> findProductMaterialList();

	Optional<ProductMaterial> findProductMaterialById(ProductMaterialId productMaterialId);

	void deleteProductMaterialById(ProductMaterialId productMaterialId);

	List<ProductMaterial> findProductMaterialListByProductId(Long productId);

	Production saveProduction(Production production);

	List<Production> findProductionList();

	Optional<Production> findProductionById(Long productionId);

	void deleteProductionById(Long productionId);

	Supplier getSupplierReferenceById(Long supplierId);

	Integer findTotalProductQuantityByProductIdAndStatus(Long productId, ProductionStatus status);
}
