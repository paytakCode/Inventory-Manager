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
 * @Version 0.4.1
 * @Since 2023-05-26 오후 3:39
 */
public class EntityToDtoMapper {

    private EntityToDtoMapper() {

    }

    public static BuyerDto convertBuyerToDto(Buyer buyer) {
        return BuyerDto.builder()
            .id(buyer.getId())
            .companyName(buyer.getCompanyName())
            .managerName(buyer.getManagerName())
            .tel(buyer.getTel())
            .loc(buyer.getLoc())
            .build();
    }

    public static MaterialDto convertMaterialToDto(Material material) {
        SupplierDto supplierDto = material.getSupplier() == null ?
            null : EntityToDtoMapper.convertSupplierToDto(material.getSupplier());

        return MaterialDto.builder()
            .id(material.getId())
            .name(material.getName())
            .spec(material.getSpec())
            .details(material.getDetails())
            .supplierDto(supplierDto)
            .build();
    }

    public static MaterialPurchaseDto convertMaterialPurchaseToDto(MaterialPurchase materialPurchase) {
        MaterialRequestDto materialRequestDto = null;
        if (materialPurchase.getMaterialRequest() != null) {
            MaterialDto materialDto = EntityToDtoMapper.convertMaterialToDto(
                materialPurchase.getMaterialRequest().getMaterial());
            UserInfoDto requesterDto = EntityToDtoMapper.convertUserToUserInfoDto(
                materialPurchase.getMaterialRequest().getRequester());
            materialRequestDto = MaterialRequestDto.builder()
                .id(materialPurchase.getMaterialRequest().getId())
                .materialDto(materialDto)
                .requesterDto(requesterDto)
                .requestDate(materialPurchase.getMaterialRequest().getCreatedDate())
                .details(materialPurchase.getMaterialRequest().getDetails())
                .quantity(materialPurchase.getMaterialRequest().getQuantity())
                .build();
        }

        MaterialDto materialDto = EntityToDtoMapper.convertMaterialToDto(materialPurchase.getMaterial());
        UserInfoDto managerDto = EntityToDtoMapper.convertUserToUserInfoDto(materialPurchase.getManager());

        return MaterialPurchaseDto.builder()
            .id(materialPurchase.getId())
            .materialDto(materialDto)
            .managerDto(managerDto)
            .details(materialPurchase.getDetails())
            .lotNo(materialPurchase.getLotNo())
            .quantity(materialPurchase.getQuantity())
            .price(materialPurchase.getPrice())
            .status(materialPurchase.getStatus())
            .materialRequestDto(materialRequestDto)
            .build();
    }

    public static MaterialRequestDto convertMaterialRequestToDto(MaterialRequest materialRequest) {
        MaterialPurchaseDto materialPurchaseDto = null;
        if (materialRequest.getMaterialPurchase() != null) {
            MaterialDto materialDto = EntityToDtoMapper.convertMaterialToDto(
                materialRequest.getMaterialPurchase().getMaterial());
            UserInfoDto managerDto = EntityToDtoMapper.convertUserToUserInfoDto(
                materialRequest.getMaterialPurchase().getManager());
            materialPurchaseDto = MaterialPurchaseDto.builder()
                .id(materialRequest.getMaterialPurchase().getId())
                .materialDto(materialDto)
                .managerDto(managerDto)
                .status(materialRequest.getMaterialPurchase().getStatus())
                .price(materialRequest.getMaterialPurchase().getPrice())
                .lotNo(materialRequest.getMaterialPurchase().getLotNo())
                .details(materialRequest.getMaterialPurchase().getDetails())
                .quantity(materialRequest.getMaterialPurchase().getQuantity())
                .build();
        }
        MaterialDto materialDto = EntityToDtoMapper.convertMaterialToDto(materialRequest.getMaterial());
        UserInfoDto requesterDto = EntityToDtoMapper.convertUserToUserInfoDto(materialRequest.getRequester());

        return MaterialRequestDto.builder()
            .id(materialRequest.getId())
            .materialDto(materialDto)
            .requesterDto(requesterDto)
            .quantity(materialRequest.getQuantity())
            .details(materialRequest.getDetails())
            .requestDate(materialRequest.getCreatedDate())
            .materialPurchaseDto(materialPurchaseDto)
            .build();
    }

    public static ProductDto convertProductToDto(Product product) {
        return ProductDto.builder()
            .id(product.getId())
            .name(product.getName())
            .spec(product.getSpec())
            .details(product.getDetails())
            .build();
    }

    public static ProductionDto convertProductionToDto(Production production) {
        ProductDto productDto = EntityToDtoMapper.convertProductToDto(production.getProduct());
        UserInfoDto managerDto = EntityToDtoMapper.convertUserToUserInfoDto(production.getManager());

        return ProductionDto.builder()
            .id(production.getId())
            .productDto(productDto)
            .managerDto(managerDto)
            .quantity(production.getQuantity())
            .details(production.getDetails())
            .targetDate(production.getTargetDate())
            .completionDate(production.getCompletionDate())
            .status(production.getStatus())
            .build();
    }

    public static ProductMaterialDto convertProductMaterialToDto(ProductMaterial productMaterial) {
        MaterialDto materialDto = EntityToDtoMapper.convertMaterialToDto(productMaterial.getId().getMaterial());
        ProductDto productDto = EntityToDtoMapper.convertProductToDto(productMaterial.getId().getProduct());

        ProductMaterialIdDto productMaterialIdDto = ProductMaterialIdDto.builder()
            .materialDto(materialDto)
            .productDto(productDto)
            .build();

        return ProductMaterialDto.builder()
            .productMaterialIdDto(productMaterialIdDto)
            .requiredQuantity(productMaterial.getRequiredQuantity())
            .build();
    }

    public static SupplierDto convertSupplierToDto(Supplier supplier) {
        return SupplierDto.builder()
            .id(supplier.getId())
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
        ProductDto productDto = EntityToDtoMapper.convertProductToDto(salesOrder.getProduct());
        BuyerDto buyerDto = EntityToDtoMapper.convertBuyerToDto(salesOrder.getBuyer());
        UserInfoDto managerDto = EntityToDtoMapper.convertUserToUserInfoDto(salesOrder.getManager());

        return SalesOrderDto.builder()
            .id(salesOrder.getId())
            .productDto(productDto)
            .buyerDto(buyerDto)
            .managerDto(managerDto)
            .dueDate(salesOrder.getDueDate())
            .quantity(salesOrder.getQuantity())
            .completionDate(salesOrder.getCompletionDate())
            .status(salesOrder.getStatus())
            .build();
    }

    public static UserDto convertUserToUserDto(UserEntity userEntity) {
        return UserDto.builder()
            .id(userEntity.getId())
            .email(userEntity.getEmail())
            .password(userEntity.getPassword())
            .name(userEntity.getName())
            .tel(userEntity.getTel())
            .build();
    }
}
