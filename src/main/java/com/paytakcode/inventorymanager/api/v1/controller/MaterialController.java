package com.paytakcode.inventorymanager.api.v1.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialPurchaseDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialRequestDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SupplierDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SupplierIdDto;
import com.paytakcode.inventorymanager.api.v1.service.MaterialService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Material Controller
 * @Author 김태산
 * @Version 0.1.0
 * @Since 2023-05-24 오전 11:30
 */

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MaterialController {

	private final MaterialService materialService;

	@PostMapping("/materials")
	public ResponseEntity<String> materialAdd(@RequestBody @Valid MaterialDto materialDto) {
		log.info("[materialAdd] param - materialDto: {}", materialDto);

		MaterialDto addedMaterialDto = materialService.addMaterial(materialDto);

		log.info("[materialAdd] return - HttpStatus.CREATED(201), addedMaterialDto: {}", addedMaterialDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(addedMaterialDto.toString());
	}

	@PostMapping("/production/material-requests")
	public ResponseEntity<String> materialRequestAdd(@RequestBody @Valid MaterialRequestDto materialRequestDto) {
		log.info("[materialRequestAdd] param - materialRequestDto: {}", materialRequestDto);

		MaterialRequestDto addedMaterialRequestDto = materialService.addMaterialRequest(materialRequestDto);

		log.info("[materialRequestAdd] return - HttpStatus.CREATED(201), addedMaterialRequestDto: {}",
			addedMaterialRequestDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(addedMaterialRequestDto.toString());
	}

	@PutMapping("/material/materials/{materialId}")
	public ResponseEntity<Void> materialUpdate(@PathVariable Long materialId,
		@Valid @RequestBody MaterialDto materialDto) {
		log.info("[materialUpdate] param - materialId: {}, materialDto: {}", materialId, materialDto);

		materialService.updateMaterial(materialId, materialDto);

		log.info("[materialUpdate] return - HttpStatus.NO_CONTENT(204)");
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

	@PostMapping("/material/material-purchases")
	public ResponseEntity<String> materialPurchaseAdd(@RequestBody @Valid MaterialPurchaseDto materialPurchaseDto) {
		log.info("[materialPurchaseAdd] param - materialPurchaseDto: {}", materialPurchaseDto);

		MaterialPurchaseDto addedMaterialPurchaseDto = materialService.addMaterialPurchase(materialPurchaseDto);

		log.info("[materialPurchaseAdd] return - HttpStatus.CREATED(201), addedMaterialPurchaseDto: {}",
			addedMaterialPurchaseDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(addedMaterialPurchaseDto.toString());
	}

	@PostMapping("/material/suppliers")
	public ResponseEntity<String> supplierAdd(@RequestBody @Valid SupplierDto supplierDto) {
		log.info("[supplierAdd] param - supplierDto: {}", supplierDto);

		SupplierDto addedSupplierDto = materialService.addSupplier(supplierDto);

		log.info("[supplierAdd] return - HttpStatus.CREATED(201), addedSupplierDto: {}", addedSupplierDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(addedSupplierDto.toString());
	}
}
