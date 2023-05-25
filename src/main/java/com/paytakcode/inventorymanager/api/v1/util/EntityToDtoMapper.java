package com.paytakcode.inventorymanager.api.v1.util;

import com.paytakcode.inventorymanager.api.v1.data.dto.BuyerDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialPurchaseDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.MaterialRequestDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SalesOrderDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductMaterialDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.ProductionDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.SupplierDto;
import com.paytakcode.inventorymanager.api.v1.data.dto.UserInfoDto;
import com.paytakcode.inventorymanager.api.v1.data.entity.Buyer;
import com.paytakcode.inventorymanager.api.v1.data.entity.Material;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialPurchase;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialRequest;
import com.paytakcode.inventorymanager.api.v1.data.entity.SalesOrder;
import com.paytakcode.inventorymanager.api.v1.data.entity.Product;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterial;
import com.paytakcode.inventorymanager.api.v1.data.entity.Production;
import com.paytakcode.inventorymanager.api.v1.data.entity.Supplier;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;

/**
 * Entity to DTO Mapper
 * @Author 김태산
 * @Version 0.1.0
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
        MaterialDto materialDto = MaterialDto.builder()
            .name(material.getName())
            .spec(material.getSpec())
            .details(material.getDetails())
            .build();

        if (material.getSupplier() != null) {
            materialDto.setSupplierId(material.getSupplier().getId());
        }

        return materialDto;
    }

    public static MaterialPurchaseDto convertMaterialPurchaseToDto(MaterialPurchase materialPurchase) {
        MaterialPurchaseDto materialPurchaseDto = MaterialPurchaseDto.builder()
            .materialId(materialPurchase.getMaterial().getId())
            .managerId(materialPurchase.getManager().getId())
            .details(materialPurchase.getDetails())
            .lotNo(materialPurchase.getLotNo())
            .quantity(materialPurchase.getQuantity())
            .price(materialPurchase.getPrice())
            .build();

        if (materialPurchase.getMaterialRequest() != null) {
            materialPurchaseDto.setMaterialRequestId(materialPurchase.getMaterialRequest().getId());
        }
        return materialPurchaseDto;
    }

    public static MaterialRequestDto convertMaterialRequestToDto(MaterialRequest materialRequest) {
        return MaterialRequestDto.builder()
            .materialId(materialRequest.getId())
            .requesterId(materialRequest.getRequester().getId())
            .quantity(materialRequest.getQuantity())
            .details(materialRequest.getDetails())
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
        return ProductMaterialDto.builder()
            .materialId(productMaterial.getId().getMaterial().getId())
            .productId(productMaterial.getId().getProduct().getId())
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
}
