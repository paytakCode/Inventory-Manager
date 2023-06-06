package com.paytakcode.inventorymanager.api.v1.service;

import java.util.List;

import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialPurchaseContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialPurchaseDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialRequestContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialRequestDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SupplierContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SupplierDto;

/**
 * Material Service
 * @Author 김태산
 * @Version 0.9.0
 * @Since 2023-05-24 오전 11:46
 */
public interface MaterialService {

	MaterialDto addMaterial(MaterialDto materialDto);

	List<MaterialDto> getMaterialList();

	MaterialDto getMaterialById(Long materialId);

	void updateMaterial(Long materialId, MaterialDto materialDto);

	void deleteMaterialById(Long materialId);

	void updateMaterialIsDeletedToTrueById(Long materialId);

	MaterialRequestDto addMaterialRequest(MaterialRequestDto materialRequestDto);

	List<MaterialRequestDto> getMaterialRequestList();

	MaterialRequestDto getMaterialRequestById(Long materialRequestId);

	void updateMaterialRequest(Long materialRequestId, MaterialRequestDto materialRequestDto);

	void deleteMaterialRequestById(Long materialRequestId);

	void updateMaterialRequestIsDeletedToTrueById(Long materialRequestId);

	MaterialPurchaseDto addMaterialPurchase(MaterialPurchaseDto materialPurchaseDto);

	List<MaterialPurchaseDto> getMaterialPurchaseList();

	MaterialPurchaseDto getMaterialPurchaseById(Long materialPurchaseId);

	void updateMaterialPurchase(Long materialPurchaseId, MaterialPurchaseDto materialPurchaseDto);

	void deleteMaterialPurchaseById(Long materialPurchaseId);

	void updateMaterialPurchaseIsDeletedToTrueById(Long materialPurchaseId);

	SupplierDto addSupplier(SupplierDto supplierDto);

	List<SupplierDto> getSupplierList();

	SupplierDto getSupplierById(Long supplierId);

	void updateSupplier(Long supplierId, SupplierDto supplierDto);

	void deleteSupplierById(Long supplierId);

	void updateSupplierIsDeletedToTrueById(Long supplierId);

	List<MaterialContentDto> getMaterialContentList();

	List<MaterialRequestContentDto> getMaterialRequestContentList();

	List<MaterialPurchaseContentDto> getMaterialPurchaseContentList();

	List<SupplierContentDto> getSupplierContentList();
}
