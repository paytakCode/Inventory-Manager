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
 * @Version 0.1.0
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
	public MaterialPurchase saveMaterialPurchase(MaterialPurchase materialPurchase) {
		log.info("[saveMaterialPurchase] param - materialPurchase: {}", materialPurchase);

		MaterialPurchase savedMaterialPurchase = materialPurchaseRepository.save(materialPurchase);

		log.info("[saveMaterialPurchase] return - savedMaterialPurchase: {}", savedMaterialPurchase);
		return savedMaterialPurchase;
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
}
