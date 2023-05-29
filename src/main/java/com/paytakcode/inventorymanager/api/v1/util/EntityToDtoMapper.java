package com.paytakcode.inventorymanager.api.v1.util;

import com.paytakcode.inventorymanager.api.v1.data.dto.BuyerDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialPurchaseDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialRequestDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialIdDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductionDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SalesOrderDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SupplierDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserInfoDto;
import com.paytakcode.inventorymanager.api.v1.data.entity.Buyer;
import com.paytakcode.inventorymanager.api.v1.data.entity.Material;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialPurchase;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialRequest;
import com.paytakcode.inventorymanager.api.v1.data.entity.Product;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterial;
import com.paytakcode.inventorymanager.api.v1.data.entity.Production;
import com.paytakcode.inventorymanager.api.v1.data.entity.SalesOrder;
import com.paytakcode.inventorymanager.api.v1.data.entity.Supplier;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;

/**
 * Entity to DTO Mapper
 * @Author 김태산
 * @Version 0.2.0
 * @Since 2023-05-26 오후 3:39
 */
public class EntityToDtoMapper {

    private EntityToDtoMapper() {

    }

    public static BuyerDto convertBuyerToDto(Buyer buyer) {
        return BuyerDto.builder()
            .companyName(buyer.getCompanyName())
            .managerName(buyer.getManagerName())
            .tel(buyer.getTel())
            .loc(buyer.getLoc())
            .build();
    }

    public static MaterialDto convertMaterialToDto(Material material) {
        Long supplierId = material.getSupplier() == null ? null : material.getSupplier().getId();

        return MaterialDto.builder()
            .name(material.getName())
            .spec(material.getSpec())
            .details(material.getDetails())
            .supplierId(supplierId)
            .build();
    }

    public static MaterialPurchaseDto convertMaterialPurchaseToDto(MaterialPurchase materialPurchase) {
        Long materialRequestId =
            materialPurchase.getMaterialRequest() == null ? null : materialPurchase.getMaterialRequest().getId();

        return MaterialPurchaseDto.builder()
            .materialId(materialPurchase.getMaterial().getId())
            .managerId(materialPurchase.getManager().getId())
            .details(materialPurchase.getDetails())
            .lotNo(materialPurchase.getLotNo())
            .quantity(materialPurchase.getQuantity())
            .price(materialPurchase.getPrice())
            .status(materialPurchase.getStatus())
            .materialRequestId(materialRequestId)
            .build();
    }

    public static MaterialRequestDto convertMaterialRequestToDto(MaterialRequest materialRequest) {
        return MaterialRequestDto.builder()
            .materialId(materialRequest.getId())
            .requesterId(materialRequest.getRequester().getId())
            .quantity(materialRequest.getQuantity())
            .details(materialRequest.getDetails())
            .materialPurchaseId(materialRequest.getId())
            .build();
    }

    public static ProductDto convertProductToDto(Product product) {
        return ProductDto.builder()
            .name(product.getName())
            .spec(product.getSpec())
            .details(product.getDetails())
            .build();
    }

    public static ProductionDto convertProductionToDto(Production production) {
        return ProductionDto.builder()
            .productId(production.getProduct().getId())
            .managerId(production.getManager().getId())
            .quantity(production.getQuantity())
            .details(production.getDetails())
            .targetDate(production.getTargetDate())
            .status(production.getStatus())
            .build();
    }

    public static ProductMaterialDto convertProductMaterialToDto(ProductMaterial productMaterial) {
        ProductMaterialIdDto productMaterialIdDto = ProductMaterialIdDto.builder()
            .materialId(productMaterial.getId().getMaterial().getId())
            .productId(productMaterial.getId().getProduct().getId())
            .build();

        return ProductMaterialDto.builder()
            .productMaterialIdDto(productMaterialIdDto)
            .requiredQuantity(productMaterial.getRequiredQuantity())
            .build();
    }

    public static SupplierDto convertSupplierToDto(Supplier supplier) {
        return SupplierDto.builder()
            .companyName(supplier.getCompanyName())
            .loc(supplier.getLoc())
            .managerName(supplier.getManagerName())
            .tel(supplier.getTel())
            .build();
    }

    public static UserInfoDto convertUserToUserInfoDto(UserEntity userEntity) {
        return UserInfoDto.builder()
            .id(userEntity.getId())
            .email(userEntity.getEmail())
            .name(userEntity.getName())
            .tel(userEntity.getTel())
            .role(userEntity.getRole())
            .build();
    }

    public static SalesOrderDto convertSalesOrderToDto(SalesOrder salesOrder) {
        return SalesOrderDto.builder()
            .productId(salesOrder.getProduct().getId())
            .buyerId(salesOrder.getBuyer().getId())
            .managerId(salesOrder.getManager().getId())
            .dueDate(salesOrder.getDueDate())
            .quantity(salesOrder.getQuantity())
            .completionDate(salesOrder.getCompletionDate())
            .status(salesOrder.getStatus())
            .build();
    }

    public static UserDto convertUserToUserDto(UserEntity userEntity) {
        return UserDto.builder()
            .email(userEntity.getEmail())
            .password(userEntity.getPassword())
            .name(userEntity.getName())
            .tel(userEntity.getTel())
            .build();
    }
}
