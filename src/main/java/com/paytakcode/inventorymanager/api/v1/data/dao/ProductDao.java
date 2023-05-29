package com.paytakcode.inventorymanager.api.v1.data.dao;

import java.util.List;
import java.util.Optional;

import com.paytakcode.inventorymanager.api.v1.data.entity.Product;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterial;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterialId;
import com.paytakcode.inventorymanager.api.v1.data.entity.Production;
import com.paytakcode.inventorymanager.api.v1.data.entity.Supplier;

/**
 * Product DAO
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-25 오전 9:10
 */

public interface ProductDao {

	Product saveProduct(Product product);

	Optional<Product> findProductById(Long productId);

	Product getProductReferenceById(Long productId);

	void deleteProductById(Long productId);

	ProductMaterial saveProductMaterial(ProductMaterial productMaterial);

	Optional<ProductMaterial> findProductMaterialById(ProductMaterialId productMaterialId);

	void deleteProductMaterialById(ProductMaterialId productMaterialId);

	List<ProductMaterial> findProductMaterialListByProductId(Long productId);

	Production saveProduction(Production production);

	Integer findTotalProductionQuantityByProductId(Long productId);

	Optional<Production> findProductionById(Long productionId);

	Supplier getSupplierReferenceById(Long supplierId);
}
