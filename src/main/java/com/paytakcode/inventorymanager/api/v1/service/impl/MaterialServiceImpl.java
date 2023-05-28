package com.paytakcode.inventorymanager.api.v1.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytakcode.inventorymanager.api.v1.data.dao.MaterialDao;
import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialPurchaseDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialRequestDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SupplierDto;
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
 * @Version 0.3.0
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

		Material foundMaterial = materialDao.findMaterialById(materialId);

		MaterialDto foundMaterialDto = EntityToDtoMapper.convertMaterialToDto(foundMaterial);

		log.info("[getMaterialById] return - foundMaterialDto: {}", foundMaterialDto);
		return foundMaterialDto;
	}

	@Override
	public void updateMaterial(Long materialId, MaterialDto materialDto) {
		log.info("[updateMaterial] param - materialId: {}, materialDto: {}", materialId, materialDto);

		Material material = materialDao.findMaterialById(materialId);

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
		Material material = materialDao.findMaterialById(materialRequestDto.getMaterialId());
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
	public SupplierDto addSupplier(SupplierDto supplierDto) {
		log.info("[addSupplier] param - supplierDto: {}", supplierDto);

		Supplier supplier = dtoToEntityMapper.convertSupplierDtoToEntity(supplierDto);

		Supplier savedSupplier = materialDao.saveSupplier(supplier);

		SupplierDto savedSupplierDto = EntityToDtoMapper.convertSupplierToDto(savedSupplier);

		log.info("[addSupplier] return - savedSupplierDto: {}", savedSupplierDto);
		return savedSupplierDto;
	}
}
