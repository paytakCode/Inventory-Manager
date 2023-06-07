package com.paytakcode.inventorymanager.api.v1.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.paytakcode.inventorymanager.api.v1.data.dao.ProductDao;
import com.paytakcode.inventorymanager.api.v1.data.emum.ProductionStatus;
import com.paytakcode.inventorymanager.api.v1.data.entity.Product;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterial;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterialId;
import com.paytakcode.inventorymanager.api.v1.data.entity.Production;
import com.paytakcode.inventorymanager.api.v1.data.entity.Supplier;
import com.paytakcode.inventorymanager.api.v1.data.repository.ProductMaterialRepository;
import com.paytakcode.inventorymanager.api.v1.data.repository.ProductRepository;
import com.paytakcode.inventorymanager.api.v1.data.repository.ProductionRepository;
import com.paytakcode.inventorymanager.api.v1.data.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Product DAO Implementation
 * @Author 김태산
 * @Version 0.5.0
 * @Since 2023-05-25 오전 9:10
 */

@Repository
@Slf4j
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

	private final ProductionRepository productionRepository;
	private final ProductRepository productRepository;
	private final ProductMaterialRepository productMaterialRepository;
	private final SupplierRepository supplierRepository;

	@Override
	public Product saveProduct(Product product) {
		log.info("[saveProduct] param - product: {}", product);

		Product savedProduct = productRepository.save(product);

		log.info("[saveProduct] param - savedProduct: {}", savedProduct);
		return savedProduct;
	}

	@Override
	public List<Product> findProductList() {
		log.info("[findProductList] param - none");

		List<Product> foundProductList = productRepository.findAll();

		log.info("[findProductList] return - foundProductList: {}", foundProductList);
		return foundProductList;
	}

	@Override
	public Optional<Product> findProductById(Long productId) {
		log.info("[findProductById] param - productId: {}", productId);

		Optional<Product> foundProduct = productRepository.findById(productId);

		log.info("[findProductById] return - foundProduct: {}", foundProduct);
		return foundProduct;
	}

	@Override
	public Product getProductReferenceById(Long productId) {
		log.info("[getProductReferenceById] param - productId: {}", productId);

		Product gotProductReference = productRepository.getReferenceById(productId);

		log.info("[getProductReferenceById] return - gotProductReference: {}", gotProductReference);
		return gotProductReference;
	}

	@Override
	public void deleteProductById(Long productId) {
		log.info("[deleteProductById] param - productId: {}", productId);

		productRepository.deleteById(productId);

		log.info("[deleteProductById] result - product Deleted: {}", productId);
	}

	@Override
	public ProductMaterial saveProductMaterial(ProductMaterial productMaterial) {
		log.info("[saveProductMaterial] param - productMaterial: {}", productMaterial);

		ProductMaterial savedProductMaterial = productMaterialRepository.save(productMaterial);

		log.info("[saveProductMaterial] param - savedProductMaterial: {}", savedProductMaterial);
		return savedProductMaterial;
	}

	@Override
	public List<ProductMaterial> findProductMaterialList() {
		log.info("[findProductMaterialList] param - none");

		List<ProductMaterial> foundProductMaterialList = productMaterialRepository.findAll();

		log.info("[findProductMaterialList] return - foundProductMaterialList: {}", foundProductMaterialList);
		return foundProductMaterialList;
	}

	@Override
	public Optional<ProductMaterial> findProductMaterialById(ProductMaterialId productMaterialId) {
		log.info("[findProductMaterialById] param - productMaterialId: {}", productMaterialId);

		Optional<ProductMaterial> foundProductMaterial = productMaterialRepository.findByIdProductAndIdMaterial(
			productMaterialId.getProduct(), productMaterialId.getMaterial());

		log.info("[findProductMaterialById] return - foundProductMaterial: {}", foundProductMaterial);
		return foundProductMaterial;
	}

	@Override
	public void deleteProductMaterialById(ProductMaterialId productMaterialId) {
		log.info("[deleteProductMaterialById] param - productMaterialId: {}", productMaterialId);

		productMaterialRepository.deleteByIdProductAndIdMaterial(productMaterialId.getProduct(),
			productMaterialId.getMaterial());

		log.info("[deleteProductMaterialById] result - productMaterial Deleted: {}", productMaterialId);
	}

	@Override
	public List<ProductMaterial> findProductMaterialListByProductId(Long productId) {
		log.info("[findProductMaterialListByProduct] param - productId: {}", productId);

		List<ProductMaterial> foundProductMaterialList = productMaterialRepository.findAllById_Product_id(productId);

		log.info("[findProductMaterialListByProduct] return - foundProductMaterialList: {}", foundProductMaterialList);
		return foundProductMaterialList;
	}

	@Override
	public Production saveProduction(Production production) {
		log.info("[saveProduction] param - production: {}", production);

		Production savedProduction = productionRepository.save(production);

		log.info("[saveProduction] result - savedProduction: {}", savedProduction);
		return savedProduction;
	}

	@Override
	public List<Production> findProductionList() {
		log.info("[findProductionList] param - none");

		List<Production> foundProductionList = productionRepository.findAll();

		log.info("[findProductionList] return - foundProductionList: {}", foundProductionList);
		return foundProductionList;
	}

	@Override
	public Optional<Production> findProductionById(Long productionId) {
		log.info("[findProductionById] param - productionId: {}", productionId);

		Optional<Production> foundProduction = productionRepository.findById(productionId);

		log.info("[findProductionById] result - foundProduction: {}", foundProduction);
		return foundProduction;
	}

	@Override
	public void deleteProductionById(Long productionId) {
		log.info("[deleteProductionById] param - productionId: {}", productionId);

		productionRepository.deleteById(productionId);

		log.info("[deleteProductionById] result - production Deleted: {}", productionId);
	}

	@Override
	public Supplier getSupplierReferenceById(Long supplierId) {
		log.info("[getSupplierReferenceById] param - supplierId: {}", supplierId);

		Supplier gotSupplier = supplierRepository.getReferenceById(supplierId);

		log.info("[getSupplierReferenceById] return - gotSupplier: {}", gotSupplier);
		return gotSupplier;
	}

	@Override
	public Integer findTotalProductQuantityByProductIdAndStatus(Long productId, ProductionStatus status) {
		log.info("[findTotalProductQuantityByProductIdAndStatus] param - productId: {}, status: {}", productId, status);

		Integer foundTotalProductQuantity = productionRepository.findTotalProductQuantityByProductIdAndStatus(productId,
			status);

		log.info("[findTotalProductQuantityByProductIdAndStatus] return - foundTotalProductQuantity: {}",
			foundTotalProductQuantity);
		return foundTotalProductQuantity;
	}

}
