package com.paytakcode.inventorymanager.api.v1.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytakcode.inventorymanager.api.v1.data.dao.ProductDao;
import com.paytakcode.inventorymanager.api.v1.data.dao.SalesDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductionDto;
import com.paytakcode.inventorymanager.api.v1.data.emum.ProductionStatus;
import com.paytakcode.inventorymanager.api.v1.data.entity.Product;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterial;
import com.paytakcode.inventorymanager.api.v1.data.entity.Production;
import com.paytakcode.inventorymanager.api.v1.service.ProductService;
import com.paytakcode.inventorymanager.api.v1.util.DtoToEntityMapper;
import com.paytakcode.inventorymanager.api.v1.util.EntityToDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Product Service Implementation
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-25 오전 9:02
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final DtoToEntityMapper dtoToEntityMapper;
	private final ProductDao productDao;
	private final SalesDao salesDao;

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		log.info("[addProduct] param - productDto: {}", productDto);

		Product product = dtoToEntityMapper.convertProductDtoToEntity(productDto);

		Product savedProduct = productDao.saveProduct(product);

		ProductDto savedProductDto = EntityToDtoMapper.convertProductToDto(savedProduct);

		log.info("[addProduct] return - savedProductDto: {}", savedProductDto);
		return savedProductDto;
	}

	@Override
	public ProductDto getProductById(Long productId) {
		log.info("[getProductById] param - productId: {}", productId);

		Product foundProduct = productDao.findProductById(productId)
			.orElseThrow();

		ProductDto foundProductDto = EntityToDtoMapper.convertProductToDto(
			foundProduct);

		log.info("[getProductById] return - foundProductDto: {}", foundProductDto);
		return foundProductDto;
	}

	@Override
	public void updateProduct(Long productId, ProductDto productDto) {
		log.info("[updateProduct] param - productId: {}, productDto: {}", productId,
			productDto);

		Product product = productDao.findProductById(productId)
			.orElseThrow();

		product.setName(productDto.getName());
		product.setSpec(productDto.getSpec());
		product.setDetails(productDto.getDetails());

		Product updatedProduct = productDao.saveProduct(product);

		ProductDto updatedProductDto = EntityToDtoMapper.convertProductToDto(
			updatedProduct);

		log.info("[updateProduct] result - updatedProductDto: {}", updatedProductDto);
	}

	@Override
	public void deleteProductById(Long productId) {
		log.info("[deleteProductById] param - productId: {}", productId);

		productDao.deleteProductById(productId);
	}

	@Override
	public ProductMaterialDto addProductMaterial(@Valid ProductMaterialDto productMaterialDto) {
		log.info("[addProductMaterial] param - productMaterialDto: {}", productMaterialDto);

		ProductMaterial productMaterial = dtoToEntityMapper.convertProductMaterialDtoToEntity(productMaterialDto);

		ProductMaterial savedProductMaterial = productDao.saveProductMaterial(productMaterial);

		ProductMaterialDto savedProductMaterialDto = EntityToDtoMapper.convertProductMaterialToDto(
			savedProductMaterial);

		log.info("[addProductMaterial] return - savedProductMaterialDto: {}", savedProductMaterialDto);
		return savedProductMaterialDto;
	}

	@Override
	public List<ProductMaterialDto> getProductMaterialListByProductId(Long productId) {
		log.info("[getProductMaterialListByProductId] param - productId: {}", productId);

		List<ProductMaterial> productMaterialList = productDao.findProductMaterialListByProductId(productId);

		List<ProductMaterialDto> productMaterialDtoList = new ArrayList<>();

		for (ProductMaterial productMaterial : productMaterialList) {
			productMaterialDtoList.add(EntityToDtoMapper.convertProductMaterialToDto(productMaterial));
		}

		log.info("[getProductMaterialListByProductId] param - productMaterialDtoList: {}", productMaterialDtoList);
		return productMaterialDtoList;
	}

	@Override
	public ProductionDto addProduction(ProductionDto productionDto) {
		log.info("[addProduction] param - productionDto: {}", productionDto);

		Production production = dtoToEntityMapper.convertProductionDtoToEntity(productionDto);

		Production savedProduction = productDao.saveProduction(production);

		ProductionDto savedProductionDto = EntityToDtoMapper.convertProductionToDto(savedProduction);

		log.info("[addProduction] return - savedProductionDto: {}", savedProductionDto);
		return savedProductionDto;
	}

	@Override
	public void updateProduction(Long productionId, ProductionDto productionDto) {
		log.info("[updateProduction] param - productionId: {}, productionDto: {}", productionId, productionDto);

		Production production = productDao.findProductionById(productionId)
			.orElseThrow();
		Product product = productDao.getProductReferenceById(productionDto.getProductId());

		if(productionDto.getStatus() == ProductionStatus.COMPLETED
			&& production.getStatus() != ProductionStatus.COMPLETED){
			production.setCompletionDate(LocalDateTime.now());
		} else {
			production.setCompletionDate(null);
		}

		production.setDetails(productionDto.getDetails());
		production.setProduct(product);
		production.setQuantity(productionDto.getQuantity());
		production.setLotNo(productionDto.getLotNo());
		production.setTargetDate(productionDto.getTargetDate());
		production.setStatus(productionDto.getStatus());

		Production updatedProduction = productDao.saveProduction(production);

		ProductionDto updatedProductionDto = EntityToDtoMapper.convertProductionToDto(updatedProduction);

		log.info("[updateProduction] result - updatedProductionDto: {}", updatedProductionDto);
	}

	@Override
	public Integer getProductStockByProductId(Long productId) {
		log.info("[getProductStockByProductId] param - productId: {}", productId);

		Integer totalProductionQuantity = Optional.ofNullable(productDao.findTotalProductionQuantityByProductId(productId))
			.orElse(0);
		Integer totalSalesQuantity = Optional.ofNullable(salesDao.findTotalSalesOrderQuantityByProductId(productId))
			.orElse(0);

		Integer productStock = totalProductionQuantity - totalSalesQuantity;

		log.info("[getProductStockByProductId] return - productStock: {}", productStock);
		return productStock;
	}
}
