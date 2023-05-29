package com.paytakcode.inventorymanager.api.v1.data.dao;

import java.util.Optional;

import com.paytakcode.inventorymanager.api.v1.data.entity.Material;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialPurchase;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialRequest;
import com.paytakcode.inventorymanager.api.v1.data.entity.Supplier;

/**
 * Material DAO
 * @Author 김태산
 * @Version 0.4.0
 * @Since 2023-05-24 오전 11:52
 */
public interface MaterialDao {
	Material saveMaterial(Material material);

	Material findMaterialById(Long materialId);

	void deleteMaterialById(Long materialId);

	MaterialRequest saveMaterialRequest(MaterialRequest materialRequest);

	Optional<MaterialRequest> findMaterialRequestById(Long materialRequestId);

	MaterialRequest getMaterialRequestReferenceById(Long materialRequestId);

	void deleteMaterialRequestById(Long materialRequestId);

	MaterialPurchase saveMaterialPurchase(MaterialPurchase materialPurchase);

	Optional<MaterialPurchase> findMaterialPurchaseById(Long materialPurchaseId);

	void deleteMaterialPurchaseById(Long materialPurchaseId);

	Material getMaterialReferenceById(Long materialId);

	Supplier saveSupplier(Supplier supplier);

	Optional<Supplier> findSupplierById(Long supplierId);

	Supplier getSupplierReferenceById(Long supplierId);
}
