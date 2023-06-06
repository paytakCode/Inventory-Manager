package com.paytakcode.inventorymanager.api.v1.service;

import java.util.List;

import com.paytakcode.inventorymanager.api.v1.data.dto.ProductContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialIdDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductionContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductionDto;

/**
 * Product Service
 * @Author 김태산
 * @Version 0.4.0
 * @Since 2023-05-25 오전 9:02
 */
public interface ProductService {

	ProductDto addProduct(ProductDto productDto);

	List<ProductDto> getProductList();

	ProductDto getProductById(Long productId);

	void updateProduct(Long productId, ProductDto productDto);

	void deleteProductById(Long productId);

	void updateProductIsDeletedToTrueById(Long productId);

	ProductMaterialDto addProductMaterial(ProductMaterialDto productMaterialDto);

	List<ProductMaterialDto> getProductMaterialList();

	ProductMaterialDto getProductMaterialById(ProductMaterialIdDto productMaterialIdDto);

	void updateProductMaterial(ProductMaterialIdDto productMaterialIdDto, ProductMaterialDto productMaterialDto);

	void deleteProductMaterialById(ProductMaterialIdDto productMaterialIdDto);

	void updateProductMaterialIsDeletedToTrueById(ProductMaterialIdDto productMaterialIdDto);

	List<ProductMaterialDto> getProductMaterialListByProductId(Long productId);

	ProductionDto addProduction(ProductionDto productionDto);

	List<ProductionDto> getProductionList();

	ProductionDto getProductionById(Long productionId);

	void updateProduction(Long productionId, ProductionDto productionDto);

	void deleteProductionById(Long productionId);

	void updateProductionIsDeletedToTrueById(Long productionId);

	Integer getProductStockByProductId(Long productId);

	List<ProductContentDto> getProductContentList();

	List<ProductionContentDto> getProductionContentList();
}
