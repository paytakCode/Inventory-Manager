package com.paytakcode.inventorymanager.api.v1.data.dao.impl;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.paytakcode.inventorymanager.api.v1.data.dao.MaterialDao;
import com.paytakcode.inventorymanager.api.v1.data.entity.Material;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialPurchase;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialRequest;
import com.paytakcode.inventorymanager.api.v1.data.entity.Supplier;
import com.paytakcode.inventorymanager.api.v1.data.repository.MaterialPurchaseRepository;
import com.paytakcode.inventorymanager.api.v1.data.repository.MaterialRepository;
import com.paytakcode.inventorymanager.api.v1.data.repository.MaterialRequestRepository;
import com.paytakcode.inventorymanager.api.v1.data.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Material DAO Implementation
 * @Author 김태산
 * @Version 0.5.0
 * @Since 2023-05-24 오전 11:53
 */

@Repository
@Slf4j
@RequiredArgsConstructor
public class MaterialDaoImpl implements MaterialDao {

	private final MaterialPurchaseRepository materialPurchaseRepository;
	private final SupplierRepository supplierRepository;
	private final MaterialRepository materialRepository;
	private final MaterialRequestRepository materialRequestRepository;

	@Override
	public Material saveMaterial(Material material) {
		log.info("[saveMaterial] param - material: {}", material);

		Material savedMaterial = materialRepository.save(material);

		log.info("[saveMaterial] return - savedMaterial: {}", savedMaterial);
		return savedMaterial;
	}

	@Override
	public Material findMaterialById(Long materialId) {
		log.info("[findMaterialById] param - materialId: {}", materialId);

		Material foundMaterial = materialRepository.findById(materialId)
			.orElseThrow(() -> new EmptyResultDataAccessException(1));

		log.info("[findMaterialById] return - foundMaterial: {}", foundMaterial);
		return foundMaterial;
	}

	@Override
	public void deleteMaterialById(Long materialId) {
		log.info("[deleteMaterialById] param - materialId: {}", materialId);

		materialRepository.deleteById(materialId);

		log.info("[deleteMaterialById] result - material Deleted: {}", materialId);
	}

	@Override
	public MaterialRequest saveMaterialRequest(MaterialRequest materialRequest) {
		log.info("[saveMaterialRequest] param - materialRequest: {}", materialRequest);

		MaterialRequest savedMaterialRequest = materialRequestRepository.save(materialRequest);

		log.info("[saveMaterialRequest] return - savedMaterialRequest: {}", savedMaterialRequest);
		return savedMaterialRequest;
	}

	@Override
	public Optional<MaterialRequest> findMaterialRequestById(Long materialRequestId) {
		log.info("[findMaterialRequestById] param - materialRequestId: {}", materialRequestId);

		Optional<MaterialRequest> foundMaterialRequest = materialRequestRepository.findById(materialRequestId);

		log.info("[findMaterialRequestById] return - foundMaterialRequest: {}", foundMaterialRequest);
		return foundMaterialRequest;
	}

	@Override
	public MaterialRequest getMaterialRequestReferenceById(Long materialRequestId) {
		log.info("[getMaterialRequestReferenceById] param - materialRequestId: {}", materialRequestId);

		MaterialRequest gotMaterialRequestReference = materialRequestRepository.getReferenceById(materialRequestId);

		log.info("[getMaterialRequestReferenceById] return - gotMaterialRequestReference: {}",
			gotMaterialRequestReference);
		return gotMaterialRequestReference;
	}

	@Override
	public void deleteMaterialRequestById(Long materialRequestId) {
		log.info("[deleteMaterialRequestById] param - materialRequestId: {}", materialRequestId);

		materialRequestRepository.deleteById(materialRequestId);

		log.info("[deleteMaterialRequestById] result - materialRequest Deleted: {}", materialRequestId);
	}

	@Override
	public MaterialPurchase saveMaterialPurchase(MaterialPurchase materialPurchase) {
		log.info("[saveMaterialPurchase] param - materialPurchase: {}", materialPurchase);

		MaterialPurchase savedMaterialPurchase = materialPurchaseRepository.save(materialPurchase);

		log.info("[saveMaterialPurchase] return - savedMaterialPurchase: {}", savedMaterialPurchase);
		return savedMaterialPurchase;
	}

	@Override
	public Optional<MaterialPurchase> findMaterialPurchaseById(Long materialPurchaseId) {
		log.info("[findMaterialPurchaseById] param - materialPurchaseId: {}", materialPurchaseId);

		Optional<MaterialPurchase> foundMaterialPurchase = materialPurchaseRepository.findById(materialPurchaseId);

		log.info("[findMaterialPurchaseById] return - foundMaterialPurchase: {}", foundMaterialPurchase);
		return foundMaterialPurchase;
	}

	@Override
	public void deleteMaterialPurchaseById(Long materialPurchaseId) {
		log.info("[deleteMaterialPurchaseById] param - materialPurchaseId: {}", materialPurchaseId);

		materialPurchaseRepository.deleteById(materialPurchaseId);

		log.info("[deleteMaterialPurchaseById] result - materialPurchase Deleted: {}", materialPurchaseId);
	}

	@Override
	public Material getMaterialReferenceById(Long materialId) {
		log.info("[getMaterialReferenceById] param - materialId: {}", materialId);

		Material gotMaterialReference = materialRepository.getReferenceById(materialId);

		log.info("[getMaterialReferenceById] return - gotMaterialReference: {}", gotMaterialReference);
		return gotMaterialReference;
	}

	@Override
	public Supplier saveSupplier(Supplier supplier) {
		log.info("[saveSupplier] param - supplier: {}", supplier);

		Supplier savedSupplier = supplierRepository.save(supplier);

		log.info("[saveSupplier] return - savedSupplier: {}", savedSupplier);
		return savedSupplier;
	}

	@Override
	public Optional<Supplier> findSupplierById(Long supplierId) {
		log.info("[findSupplierById] param - supplierId: {}", supplierId);

		Optional<Supplier> foundSupplier = supplierRepository.findById(supplierId);

		log.info("[findSupplierById] return - foundSupplier: {}", foundSupplier);
		return foundSupplier;
	}

	@Override
	public Supplier getSupplierReferenceById(Long supplierId) {
		log.info("[getSupplierById] param - supplierId: {}", supplierId);

		Supplier gotReferenceSupplier = supplierRepository.getReferenceById(supplierId);

		log.info("[getSupplierById] return - gotReferenceSupplier: {}", gotReferenceSupplier);
		return gotReferenceSupplier;
	}

	@Override
	public void deleteSupplierById(Long supplierId) {
		log.info("[deleteSupplierById] param - supplierId: {}", supplierId);

		supplierRepository.deleteById(supplierId);

		log.info("[deleteSupplierById] result - supplier Deleted: {}", supplierId);
	}
}
