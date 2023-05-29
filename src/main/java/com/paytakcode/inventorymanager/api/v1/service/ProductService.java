package com.paytakcode.inventorymanager.api.v1.service;

import java.util.List;

import com.paytakcode.inventorymanager.api.v1.data.dto.ProductDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialIdDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductionDto;

/**
 * Product Service
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-25 오전 9:02
 */
public interface ProductService {

	ProductDto addProduct(ProductDto productDto);

	ProductDto getProductById(Long productId);

	void updateProduct(Long productId, ProductDto productDto);

	void deleteProductById(Long productId);

	ProductMaterialDto addProductMaterial(ProductMaterialDto productMaterialDto);

	ProductMaterialDto getProductMaterialById(ProductMaterialIdDto productMaterialIdDto);

	void updateProductMaterial(ProductMaterialIdDto productMaterialIdDto, ProductMaterialDto productMaterialDto);

	void deleteProductMaterialById(ProductMaterialIdDto productMaterialIdDto);

	List<ProductMaterialDto> getProductMaterialListByProductId(Long productId);

	ProductionDto addProduction(ProductionDto productionDto);

	Integer getProductStockByProductId(Long productId);

	void updateProduction(Long productionId, ProductionDto productionDto);
}
