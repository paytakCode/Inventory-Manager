package com.paytakcode.inventorymanager.api.v1.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytakcode.inventorymanager.api.v1.data.dao.MaterialDao;
import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialPurchaseDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialRequestDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SupplierDto;
import com.paytakcode.inventorymanager.api.v1.data.emum.PurchaseStatus;
import com.paytakcode.inventorymanager.api.v1.data.entity.Material;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialPurchase;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialRequest;
import com.paytakcode.inventorymanager.api.v1.data.entity.Supplier;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;
import com.paytakcode.inventorymanager.api.v1.service.MaterialService;
import com.paytakcode.inventorymanager.api.v1.util.DtoToEntityMapper;
import com.paytakcode.inventorymanager.api.v1.util.EntityToDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Material Service Implementation
 * @Author 김태산
 * @Version 0.5.0
 * @Since 2023-05-24 오전 11:46
 */

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {


	private final DtoToEntityMapper dtoToEntityMapper;
	private final MaterialDao materialDao;
	private final UserDao userDao;

	@Override
	public MaterialDto addMaterial(MaterialDto materialDto) {
		log.info("[addMaterial] param - materialDto: {}", materialDto);

		Material material = dtoToEntityMapper.convertMaterialDtoToEntity(materialDto);

		Material savedMaterial = materialDao.saveMaterial(material);

		MaterialDto savedMaterialDto = EntityToDtoMapper.convertMaterialToDto(savedMaterial);

		log.info("[addMaterial] return - savedMaterialDto: {}", savedMaterialDto);
		return savedMaterialDto;
	}

	@Override
	public MaterialDto getMaterialById(Long materialId) {
		log.info("[getMaterialById] param - materialId: {}", materialId);

		Material foundMaterial = materialDao.findMaterialById(materialId)
			.orElseThrow();

		MaterialDto foundMaterialDto = EntityToDtoMapper.convertMaterialToDto(foundMaterial);

		log.info("[getMaterialById] return - foundMaterialDto: {}", foundMaterialDto);
		return foundMaterialDto;
	}

	@Override
	public void updateMaterial(Long materialId, MaterialDto materialDto) {
		log.info("[updateMaterial] param - materialId: {}, materialDto: {}", materialId, materialDto);

		Material material = materialDao.findMaterialById(materialId)
			.orElseThrow();

		material.setName(materialDto.getName());
		material.setSpec(materialDto.getSpec());
		material.setDetails(materialDto.getDetails());

		Supplier newSupplier = materialDto.getSupplierId() != null ?
			materialDao.getSupplierReferenceById(materialDto.getSupplierId()) : null;

		material.setSupplier(newSupplier);

		Material updatedMaterial = materialDao.saveMaterial(material);

		MaterialDto updatedMaterialDto = EntityToDtoMapper.convertMaterialToDto(updatedMaterial);

		log.info("[updateMaterial] result - updatedMaterialDto: {}", updatedMaterialDto);
	}

	@Override
	public void deleteMaterialById(Long materialId) {
		log.info("[deleteMaterialById] param - materialId: {}", materialId);

		materialDao.deleteMaterialById(materialId);
	}

	@Override
	public MaterialRequestDto addMaterialRequest(MaterialRequestDto materialRequestDto) {
		log.info("[addMaterialRequest] param - materialRequestDto: {}", materialRequestDto);

		MaterialRequest materialRequest = dtoToEntityMapper.convertMaterialRequestDtoToEntity(materialRequestDto);

		MaterialRequest savedMaterialRequest = materialDao.saveMaterialRequest(materialRequest);

		MaterialRequestDto savedMaterialRequestDto = EntityToDtoMapper.convertMaterialRequestToDto(
			savedMaterialRequest);

		log.info("[addMaterialRequest] return - savedMaterialRequestDto: {}", savedMaterialRequestDto);
		return savedMaterialRequestDto;
	}

	@Override
	public MaterialRequestDto getMaterialRequestById(Long materialRequestId) {
		log.info("[getMaterialRequestById] param - materialRequestId: {}", materialRequestId);

		MaterialRequest foundMaterialRequest = materialDao.findMaterialRequestById(materialRequestId)
			.orElseThrow();

		MaterialRequestDto foundMaterialRequestDto = EntityToDtoMapper.convertMaterialRequestToDto(
			foundMaterialRequest);

		log.info("[getMaterialRequestById] return - foundMaterialRequestDto: {}", foundMaterialRequestDto);
		return foundMaterialRequestDto;
	}

	@Override
	public void updateMaterialRequest(Long materialRequestId, MaterialRequestDto materialRequestDto) {
		log.info("[updateMaterialRequest] param - materialRequestId: {}, materialRequestDto: {}", materialRequestId,
			materialRequestDto);

		MaterialRequest materialRequest = materialDao.findMaterialRequestById(materialRequestId)
			.orElseThrow();
		Material material = materialDao.findMaterialById(materialRequestDto.getMaterialId())
			.orElseThrow();
		UserEntity requester = userDao.getUserReferenceById(materialRequestDto.getRequesterId());

		materialRequest.setMaterial(material);
		materialRequest.setQuantity(materialRequestDto.getQuantity());
		materialRequest.setDetails(materialRequestDto.getDetails());
		materialRequest.setRequester(requester);

		MaterialRequest updatedMaterialRequest = materialDao.saveMaterialRequest(materialRequest);

		MaterialRequestDto updatedMaterialRequestDto = EntityToDtoMapper.convertMaterialRequestToDto(
			updatedMaterialRequest);

		log.info("[updateMaterialRequest] result - updatedMaterialRequestDto: {}", updatedMaterialRequestDto);
	}

	@Override
	public void deleteMaterialRequestById(Long materialRequestId) {
		log.info("[deleteMaterialRequestById] param - materialRequestId: {}", materialRequestId);

		materialDao.deleteMaterialRequestById(materialRequestId);
	}

	@Override
	public MaterialPurchaseDto addMaterialPurchase(MaterialPurchaseDto materialPurchaseDto) {
		log.info("[addMaterialPurchase] param - materialPurchaseDto: {}", materialPurchaseDto);

		MaterialPurchase materialPurchase = dtoToEntityMapper.convertMaterialPurchaseDtoToEntity(materialPurchaseDto);

		MaterialPurchase savedMaterialPurchase = materialDao.saveMaterialPurchase(materialPurchase);

		MaterialPurchaseDto savedMaterialPurchaseDto = EntityToDtoMapper.convertMaterialPurchaseToDto(
			savedMaterialPurchase);

		log.info("[addMaterialPurchase] return - savedMaterialPurchaseDto: {}", savedMaterialPurchaseDto);
		return savedMaterialPurchaseDto;
	}

	@Override
	public MaterialPurchaseDto getMaterialPurchaseById(Long materialPurchaseId) {
		log.info("[getMaterialPurchaseById] param - materialPurchaseId: {}", materialPurchaseId);

		MaterialPurchase foundMaterialPurchase = materialDao.findMaterialPurchaseById(materialPurchaseId)
			.orElseThrow();

		MaterialPurchaseDto foundMaterialPurchaseDto = EntityToDtoMapper.convertMaterialPurchaseToDto(
			foundMaterialPurchase);

		log.info("[getMaterialPurchaseById] return - foundMaterialPurchaseDto: {}", foundMaterialPurchaseDto);
		return foundMaterialPurchaseDto;
	}

	@Override
	public void updateMaterialPurchase(Long materialPurchaseId, MaterialPurchaseDto materialPurchaseDto) {
		log.info("[updateMaterialPurchase] param - materialPurchaseId: {}, materialPurchaseDto: {}", materialPurchaseId,
			materialPurchaseDto);

		MaterialPurchase materialPurchase = materialDao.findMaterialPurchaseById(materialPurchaseId)
			.orElseThrow();
		Material material = materialDao.findMaterialById(materialPurchaseDto.getMaterialId())
			.orElseThrow();
		UserEntity manager = userDao.getUserReferenceById(materialPurchaseDto.getManagerId());
		PurchaseStatus status =
			materialPurchaseDto.getStatus() == null ? PurchaseStatus.ACCEPTED : materialPurchaseDto.getStatus();
		MaterialRequest materialRequest = materialPurchaseDto.getMaterialRequestId() == null
			? null : materialDao.getMaterialRequestReferenceById(materialPurchaseDto.getMaterialRequestId());

		materialPurchase.setMaterial(material);
		materialPurchase.setQuantity(materialPurchaseDto.getQuantity());
		materialPurchase.setDetails(materialPurchaseDto.getDetails());
		materialPurchase.setLotNo(materialPurchaseDto.getLotNo());
		materialPurchase.setManager(manager);
		materialPurchase.setPrice(materialPurchaseDto.getPrice());
		materialPurchase.setStatus(status);
		materialPurchase.setMaterialRequest(materialRequest);

		MaterialPurchase updatedMaterialPurchase = materialDao.saveMaterialPurchase(materialPurchase);

		MaterialPurchaseDto updatedMaterialPurchaseDto = EntityToDtoMapper.convertMaterialPurchaseToDto(
			updatedMaterialPurchase);

		log.info("[updateMaterialPurchase] result - updatedMaterialPurchaseDto: {}", updatedMaterialPurchaseDto);
	}

	@Override
	public void deleteMaterialPurchaseById(Long materialPurchaseId) {
		log.info("[deleteMaterialPurchaseById] param - materialPurchaseId: {}", materialPurchaseId);

		materialDao.deleteMaterialPurchaseById(materialPurchaseId);
	}

	@Override
	public SupplierDto addSupplier(SupplierDto supplierDto) {
		log.info("[addSupplier] param - supplierDto: {}", supplierDto);

		Supplier supplier = dtoToEntityMapper.convertSupplierDtoToEntity(supplierDto);

		Supplier savedSupplier = materialDao.saveSupplier(supplier);

		SupplierDto savedSupplierDto = EntityToDtoMapper.convertSupplierToDto(savedSupplier);

		log.info("[addSupplier] return - savedSupplierDto: {}", savedSupplierDto);
		return savedSupplierDto;
	}

	@Override
	public SupplierDto getSupplierById(Long supplierId) {
		log.info("[getSupplierById] param - supplierId: {}", supplierId);

		Supplier foundSupplier = materialDao.findSupplierById(supplierId)
			.orElseThrow();

		SupplierDto foundSupplierDto = EntityToDtoMapper.convertSupplierToDto(
			foundSupplier);

		log.info("[getSupplierById] return - foundSupplierDto: {}", foundSupplierDto);
		return foundSupplierDto;
	}

	@Override
	public void updateSupplier(Long supplierId, SupplierDto supplierDto) {
		log.info("[updateSupplier] param - supplierId: {}, supplierDto: {}", supplierId,
			supplierDto);

		Supplier supplier = materialDao.findSupplierById(supplierId)
			.orElseThrow();

		supplier.setCompanyName(supplierDto.getCompanyName());
		supplier.setManagerName(supplierDto.getManagerName());
		supplier.setTel(supplierDto.getTel());
		supplier.setLoc(supplierDto.getLoc());

		Supplier updatedSupplier = materialDao.saveSupplier(supplier);

		SupplierDto updatedSupplierDto = EntityToDtoMapper.convertSupplierToDto(
			updatedSupplier);

		log.info("[updateSupplier] result - updatedSupplierDto: {}", updatedSupplierDto);
	}

	@Override
	public void deleteSupplierById(Long supplierId) {
		log.info("[deleteSupplierById] param - supplierId: {}", supplierId);

		materialDao.deleteSupplierById(supplierId);
	}
}
