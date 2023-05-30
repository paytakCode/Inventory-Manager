package com.paytakcode.inventorymanager.api.v1.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.paytakcode.inventorymanager.api.v1.data.dao.MaterialDao;
import com.paytakcode.inventorymanager.api.v1.data.dao.ProductDao;
import com.paytakcode.inventorymanager.api.v1.data.dao.SalesDao;
import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
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
import com.paytakcode.inventorymanager.api.v1.data.emum.OrderStatus;
import com.paytakcode.inventorymanager.api.v1.data.emum.ProductionStatus;
import com.paytakcode.inventorymanager.api.v1.data.emum.PurchaseStatus;
import com.paytakcode.inventorymanager.api.v1.data.emum.Role;
import com.paytakcode.inventorymanager.api.v1.data.entity.Buyer;
import com.paytakcode.inventorymanager.api.v1.data.entity.Material;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialPurchase;
import com.paytakcode.inventorymanager.api.v1.data.entity.MaterialRequest;
import com.paytakcode.inventorymanager.api.v1.data.entity.Product;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterial;
import com.paytakcode.inventorymanager.api.v1.data.entity.ProductMaterialId;
import com.paytakcode.inventorymanager.api.v1.data.entity.Production;
import com.paytakcode.inventorymanager.api.v1.data.entity.SalesOrder;
import com.paytakcode.inventorymanager.api.v1.data.entity.Supplier;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;

import lombok.RequiredArgsConstructor;

/**
 * DTO to Entity Mapper
 * @Author 김태산
 * @Version 0.1.4
 * @Since 2023-05-26 오후 4:50
 */

@Component
@RequiredArgsConstructor
public class DtoToEntityMapper {

    private final MaterialDao materialDao;
    private final ProductDao productDao;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final SalesDao salesDao;

    public Buyer convertBuyerDtoToEntity(BuyerDto buyerDto) {
        return Buyer.builder()
            .companyName(buyerDto.getCompanyName())
            .managerName(buyerDto.getManagerName())
            .tel(buyerDto.getTel())
            .loc(buyerDto.getLoc())
            .build();
    }

    public Material convertMaterialDtoToEntity(MaterialDto materialDto) {
        Supplier supplier = materialDto.getSupplierId() == null ?
            null : materialDao.getSupplierReferenceById(materialDto.getSupplierId());

        return Material.builder()
            .name(materialDto.getName())
            .spec(materialDto.getSpec())
            .details(materialDto.getDetails())
            .supplier(supplier)
            .build();
    }

    public MaterialPurchase convertMaterialPurchaseDtoToEntity(MaterialPurchaseDto materialPurchaseDto) {
        UserEntity manager = userDao.getUserReferenceById(materialPurchaseDto.getManagerId());
        Material material = materialDao.getMaterialReferenceById(materialPurchaseDto.getMaterialId());
        PurchaseStatus status =
            materialPurchaseDto.getStatus() == null ? PurchaseStatus.ACCEPTED : materialPurchaseDto.getStatus();
        MaterialRequest materialRequest = materialPurchaseDto.getMaterialRequestId() == null ?
            null : materialDao.getMaterialRequestReferenceById(materialPurchaseDto.getMaterialRequestId());

        return MaterialPurchase.builder()
            .manager(manager)
            .material(material)
            .details(materialPurchaseDto.getDetails())
            .lotNo(materialPurchaseDto.getLotNo())
            .quantity(materialPurchaseDto.getQuantity())
            .price(materialPurchaseDto.getPrice())
            .status(status)
            .materialRequest(materialRequest)
            .build();
    }

    public MaterialRequest convertMaterialRequestDtoToEntity(MaterialRequestDto materialRequestDto) {
        Material requestMaterial = materialDao.getMaterialReferenceById(materialRequestDto.getMaterialId());
        UserEntity requester = userDao.getUserReferenceById(materialRequestDto.getRequesterId());

        return MaterialRequest.builder()
            .requester(requester)
            .material(requestMaterial)
            .quantity(materialRequestDto.getQuantity())
            .details(materialRequestDto.getDetails())
            .build();
    }

    public Product convertProductDtoToEntity(ProductDto productDto) {
        return Product.builder()
            .name(productDto.getName())
            .spec(productDto.getSpec())
            .details(productDto.getDetails())
            .build();
    }

    public Production convertProductionDtoToEntity(ProductionDto productionDto) {
        Product product = productDao.getProductReferenceById(productionDto.getProductId());
        UserEntity manager = userDao.getUserReferenceById(productionDto.getManagerId());
        ProductionStatus status =
            productionDto.getStatus() == null ? ProductionStatus.PLANNED : productionDto.getStatus();

        return Production.builder()
            .product(product)
            .manager(manager)
            .quantity(productionDto.getQuantity())
            .details(productionDto.getDetails())
            .targetDate(productionDto.getTargetDate())
            .status(status)
            .build();
    }

    public Supplier convertSupplierDtoToEntity(SupplierDto supplierDto) {
        return Supplier.builder()
            .companyName(supplierDto.getCompanyName())
            .loc(supplierDto.getLoc())
            .managerName(supplierDto.getManagerName())
            .tel(supplierDto.getTel())
            .build();
    }

    public UserEntity convertUserDtoToEntity(UserDto userDto) {
        return UserEntity.builder()
            .email(userDto.getEmail())
            .name(userDto.getName())
            .tel(userDto.getTel())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .role(defaultRole(userDto.getName()))
            .build();
    }
    public SalesOrder convertSalesOrderDtoToEntity(SalesOrderDto salesOrderDto) {
        Product product = productDao.getProductReferenceById(salesOrderDto.getProductId());
        Buyer buyer = salesDao.getBuyerReferenceById(salesOrderDto.getBuyerId());
        UserEntity manager = userDao.getUserReferenceById(salesOrderDto.getManagerId());

        return SalesOrder.builder()
            .product(product)
            .quantity(salesOrderDto.getQuantity())
            .manager(manager)
            .buyer(buyer)
            .dueDate(salesOrderDto.getDueDate())
            .status(OrderStatus.ORDER_CONFIRMED)
            .build();
    }

    public ProductMaterialId convertProductMaterialIdDtoToEntity(ProductMaterialIdDto productMaterialIdDto) {
        Product product = productDao.getProductReferenceById(productMaterialIdDto.getProductId());
        Material material = materialDao.getMaterialReferenceById(productMaterialIdDto.getMaterialId());

        return ProductMaterialId.builder()
            .product(product)
            .material(material)
            .build();
    }

    public ProductMaterial convertProductMaterialDtoToEntity(ProductMaterialDto productMaterialDto) {
        ProductMaterialId productMaterialId = convertProductMaterialIdDtoToEntity(
            productMaterialDto.getProductMaterialIdDto());

        return ProductMaterial.builder()
            .id(productMaterialId)
            .requiredQuantity(productMaterialDto.getRequiredQuantity())
            .build();
    }

    /**
     * Admin Role 자동으로 부여하기 위한 임시 메서드
     */
    private Role defaultRole(String name) {
        if (name.equals("admin")) {
            return Role.ROLE_ADMIN;
        } else {
            return Role.ROLE_WAIT;
        }
    }
}
