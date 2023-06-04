package com.paytakcode.inventorymanager.api.v1.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytakcode.inventorymanager.api.v1.data.dao.ProductDao;
import com.paytakcode.inventorymanager.api.v1.data.dao.SalesDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialIdDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductionDto;
import com.paytakcode.inventorymanager.api.v1.data.emum.ProductionStatus;
import com.paytakcode.inventorymanager.api.v1.data.entity.Product;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterial;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterialId;
import com.paytakcode.inventorymanager.api.v1.data.entity.Production;
import com.paytakcode.inventorymanager.api.v1.service.ProductService;
import com.paytakcode.inventorymanager.api.v1.util.DtoToEntityMapper;
import com.paytakcode.inventorymanager.api.v1.util.EntityToDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Product Service Implementation
 * @Author 김태산
 * @Version 0.5.0
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
	public List<ProductDto> getProductList() {
		log.info("[getProductList] param - none");

		List<Product> foundProductList = productDao.findProductList();

		List<ProductDto> foundProductDtoList = new ArrayList<>();

		for (Product product : foundProductList) {
			foundProductDtoList.add(EntityToDtoMapper.convertProductToDto(product));
		}

		log.info("[getProductList] return - foundProductDtoList: {}", foundProductDtoList);
		return foundProductDtoList;
	}

	@Override
	public ProductDto getProductById(Long productId) {
		log.info("[getProductById] param - productId: {}", productId);

		Product foundProduct = productDao.findProductById(productId)
			.orElseThrow(() -> new EntityNotFoundException("Product not found for ID: " + productId));

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
			.orElseThrow(() -> new EntityNotFoundException("Product not found for ID: " + productId));

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
	public ProductMaterialDto addProductMaterial(ProductMaterialDto productMaterialDto) {
		log.info("[addProductMaterial] param - productMaterialDto: {}", productMaterialDto);

		ProductMaterial productMaterial = dtoToEntityMapper.convertProductMaterialDtoToEntity(productMaterialDto);

		ProductMaterial savedProductMaterial = productDao.saveProductMaterial(productMaterial);

		ProductMaterialDto savedProductMaterialDto = EntityToDtoMapper.convertProductMaterialToDto(
			savedProductMaterial);

		log.info("[addProductMaterial] return - savedProductMaterialDto: {}", savedProductMaterialDto);
		return savedProductMaterialDto;
	}

	@Override
	public List<ProductMaterialDto> getProductMaterialList() {
		log.info("[getProductMaterialList] param - none");

		List<ProductMaterial> foundProductMaterialList = productDao.findProductMaterialList();

		List<ProductMaterialDto> foundProductMaterialDtoList = new ArrayList<>();

		for (ProductMaterial productMaterial : foundProductMaterialList) {
			foundProductMaterialDtoList.add(EntityToDtoMapper.convertProductMaterialToDto(productMaterial));
		}

		log.info("[getProductMaterialList] return - foundProductMaterialDtoList: {}", foundProductMaterialDtoList);
		return foundProductMaterialDtoList;
	}

	@Override
	public ProductMaterialDto getProductMaterialById(ProductMaterialIdDto productMaterialIdDto) {
		log.info("[getProductMaterialByProductIdAndMaterialId] param - ProductMaterialIdDto: {}", productMaterialIdDto);

		ProductMaterialId productMaterialId = dtoToEntityMapper.convertProductMaterialIdDtoToEntity(
			productMaterialIdDto);

		ProductMaterial foundProductMaterial = productDao.findProductMaterialById(productMaterialId)
			.orElseThrow(() -> new EntityNotFoundException("ProductMaterial not found for ID: " + productMaterialId));

		ProductMaterialDto foundProductMaterialDto = EntityToDtoMapper.convertProductMaterialToDto(
			foundProductMaterial);

		log.info("[getProductMaterialByProductIdAndMaterialId] return - foundProductMaterialDto: {}",
			foundProductMaterialDto);
		return foundProductMaterialDto;
	}

	@Override
	public void updateProductMaterial(ProductMaterialIdDto productMaterialIdDto,
		ProductMaterialDto productMaterialDto) {
		log.info("[updateProductMaterial] param - productMaterialIdDto: {}, productMaterialDto: {}",
			productMaterialIdDto,
			productMaterialDto);

		ProductMaterialId productMaterialId = dtoToEntityMapper.convertProductMaterialIdDtoToEntity(
			productMaterialIdDto);

		ProductMaterial productMaterial = productDao.findProductMaterialById(productMaterialId)
			.orElseThrow(() -> new EntityNotFoundException("ProductMaterial not found for ID: " + productMaterialId));

		productMaterial.setRequiredQuantity(productMaterialDto.getRequiredQuantity());

		ProductMaterial updatedProductMaterial = productDao.saveProductMaterial(productMaterial);

		ProductMaterialDto updatedProductMaterialDto = EntityToDtoMapper.convertProductMaterialToDto(
			updatedProductMaterial);

		log.info("[updateProductMaterial] result - updatedProductMaterialDto: {}", updatedProductMaterialDto);
	}

	@Override
	public void deleteProductMaterialById(ProductMaterialIdDto productMaterialIdDto) {
		log.info("[deleteProductMaterialById] param - productMaterialIdDto: {}", productMaterialIdDto);

		ProductMaterialId productMaterialId = dtoToEntityMapper.convertProductMaterialIdDtoToEntity(
			productMaterialIdDto);

		productDao.deleteProductMaterialById(productMaterialId);
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
	public List<ProductionDto> getProductionList() {
		log.info("[getProductionList] param - none");

		List<Production> foundProductionList = productDao.findProductionList();

		List<ProductionDto> foundProductionDtoList = new ArrayList<>();

		for (Production production : foundProductionList) {
			foundProductionDtoList.add(EntityToDtoMapper.convertProductionToDto(production));
		}

		log.info("[getProductionList] return - foundProductionDtoList: {}", foundProductionDtoList);
		return foundProductionDtoList;
	}

	@Override
	public ProductionDto getProductionById(Long productionId) {
		log.info("[getProductionById] param - productionId: {}", productionId);

		Production foundProduction = productDao.findProductionById(productionId)
			.orElseThrow(() -> new EntityNotFoundException("Production not found for ID: " + productionId));

		ProductionDto foundProductionDto = EntityToDtoMapper.convertProductionToDto(
			foundProduction);

		log.info("[getProductionById] return - foundProductionDto: {}", foundProductionDto);
		return foundProductionDto;
	}

	@Override
	public void updateProduction(Long productionId, ProductionDto productionDto) {
		log.info("[updateProduction] param - productionId: {}, productionDto: {}", productionId, productionDto);

		Production production = productDao.findProductionById(productionId)
			.orElseThrow(() -> new EntityNotFoundException("Production not found for ID: " + productionId));
		Product product = productDao.getProductReferenceById(productionDto.getProductDto().getId());

		if (productionDto.getStatus() == ProductionStatus.COMPLETED
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
	public void deleteProductionById(Long productionId) {
		log.info("[deleteProductionById] param - productionId: {}", productionId);

		productDao.deleteProductionById(productionId);
	}

	@Override
	public Integer getProductStockByProductId(Long productId) {
		log.info("[getProductStockByProductId] param - productId: {}", productId);

		Integer totalProductionQuantity = Optional.ofNullable(
				productDao.findTotalProductionQuantityByProductId(productId))
			.orElse(0);
		Integer totalSalesQuantity = Optional.ofNullable(salesDao.findTotalSalesOrderQuantityByProductId(productId))
			.orElse(0);

		Integer productStock = totalProductionQuantity - totalSalesQuantity;

		log.info("[getProductStockByProductId] return - productStock: {}", productStock);
		return productStock;
	}
}
