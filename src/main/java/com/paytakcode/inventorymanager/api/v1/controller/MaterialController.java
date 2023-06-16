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

import com.paytakcode.inventorymanager.api.v1.config.ApiBaseUrlConfig;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialPurchaseContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialPurchaseDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialRequestContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialRequestDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SupplierContentDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SupplierDto;
import com.paytakcode.inventorymanager.api.v1.service.MaterialService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Material Controller
 * @Author 김태산
 * @Version 0.9.0
 * @Since 2023-05-24 오전 11:30
 */

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(ApiBaseUrlConfig.API_BASE_URL)
public class MaterialController {

	private final MaterialService materialService;

	@PostMapping("/material/materials")
	public ResponseEntity<String> materialAdd(@RequestBody @Valid MaterialDto materialDto) {
		log.info("[materialAdd] param - materialDto: {}", materialDto);

		MaterialDto addedMaterialDto = materialService.addMaterial(materialDto);

		log.info("[materialAdd] return - HttpStatus.CREATED(201), addedMaterialDto: {}", addedMaterialDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(addedMaterialDto.toString());
	}

	@GetMapping("/materials")
	public ResponseEntity<List<MaterialDto>> materialList() {
		log.info("[materialList] param - none");

		List<MaterialDto> materialList = materialService.getMaterialList();

		log.info("[materialList] return - HttpStatus.OK(200), materialList: {}", materialList);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(materialList);
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

	@DeleteMapping("/material/materials/{materialId}")
	public ResponseEntity<Void> materialDeleteById(@PathVariable Long materialId) {
		log.info("[materialDeleteById] param - materialId: {}", materialId);

		materialService.updateMaterialIsDeletedToTrueById(materialId);

		log.info("[materialDeleteById] return - HttpStatus.NO_CONTENT(204)");
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
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

	@GetMapping("/material-requests")
	public ResponseEntity<List<MaterialRequestDto>> materialRequestList() {
		log.info("[materialRequestList] param - none");

		List<MaterialRequestDto> materialRequestList = materialService.getMaterialRequestList();

		log.info("[materialRequestList] return - HttpStatus.OK(200), materialRequestList: {}", materialRequestList);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(materialRequestList);
	}

	@GetMapping("/material-requests/{materialRequestId}")
	public ResponseEntity<MaterialRequestDto> materialRequestById(@PathVariable Long materialRequestId) {
		log.info("[materialRequestById] param - materialRequestId: {}", materialRequestId);

		MaterialRequestDto materialRequestDto = materialService.getMaterialRequestById(materialRequestId);

		log.info("[materialRequestById] return - HttpStatus.OK(200), materialRequestDto: {}", materialRequestDto);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(materialRequestDto);
	}

	@PutMapping("/production/material-requests/{materialRequestId}")
	public ResponseEntity<Void> materialRequestUpdate(@PathVariable Long materialRequestId,
		@Valid @RequestBody MaterialRequestDto materialRequestDto) {
		log.info("[materialRequestUpdate] param - materialRequestId: {}, materialRequestDto: {}", materialRequestId,
			materialRequestDto);

		materialService.updateMaterialRequest(materialRequestId, materialRequestDto);

		log.info("[materialRequestUpdate] return - HttpStatus.NO_CONTENT(204)");
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

	@DeleteMapping("/production/material-requests/{materialRequestId}")
	public ResponseEntity<Void> materialRequestDeleteById(@PathVariable Long materialRequestId) {
		log.info("[materialRequestDeleteById] param - materialRequestId: {}", materialRequestId);

		materialService.updateMaterialRequestIsDeletedToTrueById(materialRequestId);

		log.info("[materialRequestDeleteById] return - HttpStatus.NO_CONTENT(204)");
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

	@GetMapping("/material-purchases")
	public ResponseEntity<List<MaterialPurchaseDto>> materialPurchaseList() {
		log.info("[materialPurchaseList] param - none");

		List<MaterialPurchaseDto> materialPurchaseList = materialService.getMaterialPurchaseList();

		log.info("[materialPurchaseList] return - HttpStatus.OK(200), materialPurchaseList: {}", materialPurchaseList);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(materialPurchaseList);
	}

	@GetMapping("/material-purchases/{materialPurchaseId}")
	public ResponseEntity<MaterialPurchaseDto> materialPurchaseById(@PathVariable Long materialPurchaseId) {
		log.info("[materialPurchaseById] param - materialPurchaseId: {}", materialPurchaseId);

		MaterialPurchaseDto materialPurchaseDto = materialService.getMaterialPurchaseById(materialPurchaseId);

		log.info("[materialPurchaseById] return - HttpStatus.OK(200), materialPurchaseDto: {}", materialPurchaseDto);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(materialPurchaseDto);
	}

	@PutMapping("/material/material-purchases/{materialPurchaseId}")
	public ResponseEntity<Void> materialPurchaseUpdate(@PathVariable Long materialPurchaseId,
		@Valid @RequestBody MaterialPurchaseDto materialPurchaseDto) {
		log.info("[materialPurchaseUpdate] param - materialPurchaseId: {}, materialPurchaseDto: {}", materialPurchaseId,
			materialPurchaseDto);

		materialService.updateMaterialPurchase(materialPurchaseId, materialPurchaseDto);

		log.info("[materialPurchaseUpdate] return - HttpStatus.NO_CONTENT(204)");
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

	@DeleteMapping("/material/material-purchases/{materialPurchaseId}")
	public ResponseEntity<Void> materialPurchaseDeleteById(@PathVariable Long materialPurchaseId) {
		log.info("[materialPurchaseDeleteById] param - materialPurchaseId: {}", materialPurchaseId);

		materialService.updateMaterialPurchaseIsDeletedToTrueById(materialPurchaseId);
		log.info("[materialPurchaseDeleteById] return - HttpStatus.NO_CONTENT(204)");
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
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

	@GetMapping("/suppliers")
	public ResponseEntity<List<SupplierDto>> supplierList() {
		log.info("[supplierList] param - none");

		List<SupplierDto> supplierList = materialService.getSupplierList();

		log.info("[supplierList] return - HttpStatus.OK(200), supplierList: {}", supplierList);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(supplierList);
	}

	@GetMapping("/suppliers/{supplierId}")
	public ResponseEntity<SupplierDto> supplierById(@PathVariable Long supplierId) {
		log.info("[supplierById] param - supplierId: {}", supplierId);

		SupplierDto supplierDto = materialService.getSupplierById(supplierId);

		log.info("[supplierById] return - HttpStatus.OK(200), supplierDto: {}", supplierDto);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(supplierDto);
	}

	@PutMapping("/material/suppliers/{supplierId}")
	public ResponseEntity<Void> supplierUpdate(@PathVariable Long supplierId,
		@Valid @RequestBody SupplierDto supplierDto) {
		log.info("[supplierUpdate] param - supplierId: {}, supplierDto: {}", supplierId,
			supplierDto);

		materialService.updateSupplier(supplierId, supplierDto);

		log.info("[supplierUpdate] return - HttpStatus.NO_CONTENT(204)");
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

	@DeleteMapping("/material/suppliers/{supplierId}")
	public ResponseEntity<Void> supplierDeleteById(@PathVariable Long supplierId) {
		log.info("[supplierDeleteById] param - supplierId: {}", supplierId);

		materialService.updateSupplierIsDeletedToTrueById(supplierId);

		log.info("[supplierDeleteById] return - HttpStatus.NO_CONTENT(204)");
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build();
	}

	@GetMapping("/material-contents")
	public ResponseEntity<List<MaterialContentDto>> materialContentList() {
		log.info("[materialContentList] param - none");

		List<MaterialContentDto> materialContentList = materialService.getMaterialContentList();

		log.info("[materialContentList] return - HttpStatus.OK(200), materialContentList: {}", materialContentList);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(materialContentList);
	}

	@GetMapping("/material-request-contents")
	public ResponseEntity<List<MaterialRequestContentDto>> materialRequestContentList() {
		log.info("[materialRequestContentList] param - none");

		List<MaterialRequestContentDto> materialRequestContentList = materialService.getMaterialRequestContentList();

		log.info("[materialRequestContentList] return - HttpStatus.OK(200), materialRequestContentList: {}",
			materialRequestContentList);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(materialRequestContentList);
	}

	@GetMapping("/material-purchase-contents")
	public ResponseEntity<List<MaterialPurchaseContentDto>> materialPurchaseContentList() {
		log.info("[materialPurchaseContentList] param - none");

		List<MaterialPurchaseContentDto> materialPurchaseContentList = materialService.getMaterialPurchaseContentList();

		log.info("[materialPurchaseContentList] return - HttpStatus.OK(200), materialPurchaseContentList: {}",
			materialPurchaseContentList);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(materialPurchaseContentList);
	}

	@GetMapping("/supplier-contents")
	public ResponseEntity<List<SupplierContentDto>> supplierContentList() {
		log.info("[supplierContentList] param - none");

		List<SupplierContentDto> supplierContentList = materialService.getSupplierContentList();

		log.info("[supplierContentList] return - HttpStatus.OK(200), supplierContentList: {}", supplierContentList);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(supplierContentList);
	}
}
