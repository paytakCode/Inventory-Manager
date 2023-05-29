package com.paytakcode.inventorymanager.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paytakcode.inventorymanager.api.v1.data.dto.ProductDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductionDto;
import com.paytakcode.inventorymanager.api.v1.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Product Controller
 * @Author 김태산
 * @Version 0.1.1
 * @Since 2023-05-25 오전 8:59
 */

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

	private final ProductService productService;

	@PostMapping("/production/products")
	public ResponseEntity<String> productAdd(@RequestBody @Valid ProductDto productDto) {
		log.info("[productAdd] param - productMaterialDto: {}", productDto);

		ProductDto addedProductDto = productService.addProduct(productDto);

		log.info("[productAdd] return - HttpStatus.CREATED(201), addedProductMaterialDto: {}", addedProductDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(addedProductDto.toString());
	}

	@GetMapping("/production/products/{productId}")
	public ResponseEntity<ProductDto> productById(@PathVariable Long productId) {
		log.info("[productById] param - productId: {}", productId);

		ProductDto productDto = productService.getProductById(productId);

		log.info("[productById] return - HttpStatus.OK(200), productDto: {}", productDto);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(productDto);
	}

	@PutMapping("/production/products/{productId}")
	public ResponseEntity<Void> productUpdate(@PathVariable Long productId,
		@Valid @RequestBody ProductDto productDto) {
		log.info("[productUpdate] param - productId: {}, productDto: {}", productId,
			productDto);

		productService.updateProduct(productId, productDto);

		log.info("[productUpdate] return - HttpStatus.NO_CONTENT(204)");
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

	@DeleteMapping("/production/products/{productId}")
	public ResponseEntity<Void> productDeleteById(@PathVariable Long productId) {
		log.info("[productDeleteById] param - productId: {}", productId);

		productService.deleteProductById(productId);

		log.info("[productDeleteById] return - HttpStatus.NO_CONTENT(204)");
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

	@PostMapping("/production/product-materials")
	public ResponseEntity<String> productMaterialAdd(@RequestBody @Valid ProductMaterialDto productMaterialDto) {
		log.info("[productMaterialAdd] param - productMaterialDto: {}", productMaterialDto);

		ProductMaterialDto addedProductMaterialDto = productService.addProductMaterial(productMaterialDto);

		log.info("[productMaterialAdd] return - HttpStatus.CREATED(201), addedProductMaterialDto: {}",
			addedProductMaterialDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(addedProductMaterialDto.toString());
	}

	@GetMapping("/products/{productId}/product-materials")
	public ResponseEntity<List<ProductMaterialDto>> productMaterialListByProductId(@PathVariable Long productId) {
		log.info("[productMaterialListByProductId] param - productId: {}", productId);

		List<ProductMaterialDto> productMaterialList = productService.getProductMaterialListByProductId(productId);

		log.info("[productMaterialListByProductId] return - HttpStatus.OK(200) , productMaterialList: {}",
			productMaterialList);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(productMaterialList);
	}

	@PostMapping("/production/productions")
	public ResponseEntity<String> productionAdd(@RequestBody @Valid ProductionDto productionDto) {
		log.info("[productionAdd] param - productionDto: {}", productionDto);

		ProductionDto addedProductionDto = productService.addProduction(productionDto);

		log.info("[productionAdd] return - HttpStatus.CREATED(201), addedProductionDto: {}", addedProductionDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(addedProductionDto.toString());
	}
	@PutMapping("/production/productions/{productionId}")
	public ResponseEntity<String> productionUpdate(@PathVariable Long productionId, @RequestBody @Valid ProductionDto productionDto) {
		log.info("[productionUpdate] param - productionId:{}, productionDto: {}", productionId, productionDto);

		productService.updateProduction(productionId, productionDto);

		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

	@GetMapping("/products/{productId}/stock")
	public ResponseEntity<Integer> productStockByProductId(@PathVariable Long productId){
		log.info("[productStockByProductId] param - productId: {}", productId);

		Integer productStock = productService.getProductStockByProductId(productId);

		log.info("[productStockByProductId] return - productStock: {}", productStock);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(productStock);
	}
}
