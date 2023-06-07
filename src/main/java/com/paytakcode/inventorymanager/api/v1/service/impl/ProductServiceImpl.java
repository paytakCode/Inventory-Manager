package com.paytakcode.inventorymanager.api.v1.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytakcode.inventorymanager.api.v1.data.dao.ProductDao;
import com.paytakcode.inventorymanager.api.v1.data.dao.SalesDao;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialIdDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductionContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductionDto;
import com.paytakcode.inventorymanager.api.v1.data.emum.OrderStatus;
import com.paytakcode.inventorymanager.api.v1.data.emum.ProductionStatus;
import com.paytakcode.inventorymanager.api.v1.data.entity.Product;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterial;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterialId;
import com.paytakcode.inventorymanager.api.v1.data.entity.Production;
import com.paytakcode.inventorymanager.api.v1.service.MaterialService;
import com.paytakcode.inventorymanager.api.v1.service.ProductService;
import com.paytakcode.inventorymanager.api.v1.util.DtoToEntityMapper;
import com.paytakcode.inventorymanager.api.v1.util.EntityToDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Product Service Implementation
 * @Author 김태산
 * @Version 0.5.1
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
	private final MaterialService materialService;

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

		List<ProductDto> foundProductDtoList = foundProductList.stream()
			.filter(product -> !product.getIsDeleted())
			.map(EntityToDtoMapper::convertProductToDto)
			.collect(Collectors.toList());

		log.info("[getProductList] return - foundProductDtoList: {}", foundProductDtoList);
		return foundProductDtoList;
	}

	@Override
	public ProductDto getProductById(Long productId) {
		log.info("[getProductById] param - productId: {}", productId);

		Product foundProduct = productDao.findProductById(productId)
			.orElseThrow(() -> new EntityNotFoundException("Product not found for ID: " + productId));

		if (foundProduct.getIsDeleted()) {
			throw new NoSuchElementException();
		}

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
	public void updateProductIsDeletedToTrueById(Long productId) {
		log.info("[updateProductIsDeletedToTrueById] param - productId: {}", productId);

		Product product = productDao.findProductById(productId)
			.orElseThrow(() -> new EntityNotFoundException("Product not found for ID: " + productId));

		product.setIsDeleted(true);

		productDao.saveProduct(product);

		log.info("[updateProductIsDeletedToTrueById] result - Product updated(isDeleted=true) productId: {}",
			productId);
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

		List<ProductMaterialDto> foundProductMaterialDtoList = foundProductMaterialList.stream()
			.filter(productMaterial -> !productMaterial.getIsDeleted())
			.map(EntityToDtoMapper::convertProductMaterialToDto)
			.collect(Collectors.toList());

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

		if (foundProductMaterial.getIsDeleted()) {
			throw new NoSuchElementException();
		}

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
	public void updateProductMaterialIsDeletedToTrueById(ProductMaterialIdDto productMaterialIdDto) {
		log.info("[updateProductMaterialIsDeletedToTrueById] param - productMaterialIdDto: {}", productMaterialIdDto);

		ProductMaterialId productMaterialId = dtoToEntityMapper.convertProductMaterialIdDtoToEntity(
			productMaterialIdDto);

		ProductMaterial productMaterial = productDao.findProductMaterialById(productMaterialId)
			.orElseThrow(() -> new EntityNotFoundException("ProductMaterial not found for ID: " + productMaterialId));

		productMaterial.setIsDeleted(true);

		productDao.saveProductMaterial(productMaterial);

		log.info(
			"[updateProductMaterialIsDeletedToTrueById] result - ProductMaterial updated(isDeleted=true) productMaterialId: {}",
			productMaterialId);
	}

	@Override
	public List<ProductMaterialDto> getProductMaterialListByProductId(Long productId) {
		log.info("[getProductMaterialListByProductId] param - productId: {}", productId);

		List<ProductMaterial> productMaterialList = productDao.findProductMaterialListByProductId(productId);

		List<ProductMaterialDto> productMaterialDtoList = productMaterialList.stream()
			.filter(productMaterial -> !productMaterial.getIsDeleted())
			.map(EntityToDtoMapper::convertProductMaterialToDto)
			.collect(Collectors.toList());

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

		List<ProductionDto> foundProductionDtoList = foundProductionList.stream()
			.filter(production -> !production.getIsDeleted())
			.map(EntityToDtoMapper::convertProductionToDto)
			.collect(Collectors.toList());

		log.info("[getProductionList] return - foundProductionDtoList: {}", foundProductionDtoList);
		return foundProductionDtoList;
	}

	@Override
	public ProductionDto getProductionById(Long productionId) {
		log.info("[getProductionById] param - productionId: {}", productionId);

		Production foundProduction = productDao.findProductionById(productionId)
			.orElseThrow(() -> new EntityNotFoundException("Production not found for ID: " + productionId));

		if (foundProduction.getIsDeleted()) {
			throw new NoSuchElementException();
		}

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
	public void updateProductionIsDeletedToTrueById(Long productionId) {
		log.info("[updateProductionIsDeletedToTrueById] param - productionId: {}", productionId);

		Production production = productDao.findProductionById(productionId)
			.orElseThrow(() -> new EntityNotFoundException("Production not found for ID: " + productionId));

		production.setIsDeleted(true);

		productDao.saveProduction(production);

		log.info("[updateProductionIsDeletedToTrueById] result - Production updated(isDeleted=true) productionId: {}",
			productionId);
	}

	@Override
	public List<ProductContentDto> getProductContentList() {
		log.info("[getProductContentList] param - none");

		List<Product> productList = productDao.findProductList();

		List<Product> notDeletedProductList = productList.stream()
			.filter(product -> !product.getIsDeleted())
			.collect(Collectors.toList());

		List<ProductContentDto> productContentDtoList = new ArrayList<>();

		for (Product product : notDeletedProductList) {
			ProductDto productDto = EntityToDtoMapper.convertProductToDto(product);

			Integer totalProductionQuantity = Optional.ofNullable(
					productDao.findTotalProductQuantityByProductIdAndStatus(productDto.getId(), ProductionStatus.COMPLETED))
				.orElse(0);
			Integer totalSalesQuantity = Optional.ofNullable(
					salesDao.findTotalProductQuantityByProductIdAndStatus(productDto.getId(), OrderStatus.COMPLETED))
				.orElse(0)
				+ Optional.ofNullable(
					salesDao.findTotalProductQuantityByProductIdAndStatus(productDto.getId(), OrderStatus.SHIPPED))
				.orElse(0)
				+ Optional.ofNullable(
					salesDao.findTotalProductQuantityByProductIdAndStatus(productDto.getId(), OrderStatus.DELIVERED))
				.orElse(0);

			Integer currentQuantity = totalProductionQuantity - totalSalesQuantity;
			Integer inProductionQuantity = Optional.ofNullable(
				productDao.findTotalProductQuantityByProductIdAndStatus(productDto.getId(),
					ProductionStatus.INPRODUCTION)).orElse(0);
			Integer plannedOutboundQuantity = Optional.ofNullable(
					salesDao.findTotalProductQuantityByProductIdAndStatus(productDto.getId(), OrderStatus.ORDER_CONFIRMED))
				.orElse(0)
				+ Optional.ofNullable(
					salesDao.findTotalProductQuantityByProductIdAndStatus(productDto.getId(), OrderStatus.READY_SHIPMENT))
				.orElse(0);
			Integer actualQuantity = currentQuantity + inProductionQuantity - plannedOutboundQuantity;

			ProductContentDto productContentDto = new ProductContentDto();
			productContentDto.setId(productDto.getId());
			productContentDto.setName(productDto.getName());
			productContentDto.setSpec(productDto.getSpec());
			productContentDto.setDetails(productDto.getDetails());
			productContentDto.setCurrentQuantity(currentQuantity);
			productContentDto.setInProductionQuantity(inProductionQuantity);
			productContentDto.setPlannedOutboundQuantity(plannedOutboundQuantity);
			productContentDto.setActualQuantity(actualQuantity);

			productContentDtoList.add(productContentDto);
		}

		log.info("[getProductContentList] return - productContentDtoList: {}", productContentDtoList);
		return productContentDtoList;
	}

	@Override
	public List<ProductionContentDto> getProductionContentList() {
		log.info("[getProductionContentList] param - none");

		List<Production> productionList = productDao.findProductionList();

		List<Production> notDeletedProductionList = productionList.stream()
			.filter(production -> !production.getIsDeleted())
			.collect(Collectors.toList());

		List<ProductionContentDto> productionContentDtoList = new ArrayList<>();

		for (Production production : notDeletedProductionList) {
			ProductionDto productionDto = EntityToDtoMapper.convertProductionToDto(production);

			ProductionContentDto productionContentDto = new ProductionContentDto();
			productionContentDto.setId(productionDto.getId());
			productionContentDto.setProductDto(productionDto.getProductDto());
			productionContentDto.setQuantity(productionDto.getQuantity());
			productionContentDto.setManagerDto(productionDto.getManagerDto());
			productionContentDto.setDetails(productionDto.getDetails());
			productionContentDto.setLotNo(productionDto.getLotNo());
			productionContentDto.setTargetDate(productionDto.getTargetDate());
			productionContentDto.setCompletionDate(productionDto.getCompletionDate());
			productionContentDto.setStatus(productionDto.getStatus());

			productionContentDtoList.add(productionContentDto);
		}

		log.info("[getProductionContentList] return - productionContentDtoList: {}", productionContentDtoList);
		return productionContentDtoList;
	}

	@Override
	public List<ProductMaterialContentDto> getProductMaterialContentList() {
		log.info("[getProductMaterialContentList] param - none");

		List<ProductMaterialContentDto> productMaterialContentList = new ArrayList<>();

		List<ProductDto> productDtoList = getProductList();

		for (ProductDto productDto : productDtoList) {
			List<ProductMaterialDto> productMaterialDtoList = getProductMaterialList();
			productMaterialContentList.add(ProductMaterialContentDto.builder()
				.productDto(productDto)
				.productMaterialDtoList(productMaterialDtoList)
				.build());
		}

		log.info("[getProductMaterialContentList] return - productMaterialContentList: {}", productMaterialContentList);
		return productMaterialContentList;
	}
}
