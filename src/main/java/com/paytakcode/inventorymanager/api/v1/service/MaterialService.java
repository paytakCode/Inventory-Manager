package com.paytakcode.inventorymanager.api.v1.service;

import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialPurchaseDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialRequestDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SupplierDto;

/**
 * Material Service
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-24 오전 11:46
 */
public interface MaterialService {
	MaterialDto addMaterial(MaterialDto materialDto);

	MaterialRequestDto addMaterialRequest(MaterialRequestDto materialRequestDto);

	MaterialPurchaseDto addMaterialPurchase(MaterialPurchaseDto materialPurchaseDto);

	SupplierDto addSupplier(SupplierDto supplierDto);

	void updateMaterial(Long materialId, MaterialDto materialDto);
}
