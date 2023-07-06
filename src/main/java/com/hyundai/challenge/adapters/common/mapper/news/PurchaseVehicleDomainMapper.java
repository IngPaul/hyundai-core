package com.hyundai.challenge.adapters.common.mapper.news;


import com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.entities.VehiclePurchase;
import com.hyundai.challenge.domain.purchase.PurchaseVehicleDomain;
import com.hyundai.challenge.model.PostPurchaseVehicleModelRequestData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PurchaseVehicleDomainMapper {
    PurchaseVehicleDomainMapper INSTANCE = Mappers.getMapper(PurchaseVehicleDomainMapper.class);
    /*
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = "vehiclePurchase.fullName")
    @Mapping(target = "version", source = "vehiclePurchase.version")
    @Mapping(target = "model", source = "vehiclePurchase.model")
    @Mapping(target = "cryptocurrency", source = "vehiclePurchase.cryptocurrency")
    @Mapping(target = "priceUsd", source = "vehiclePurchase.priceUsd")
    @Mapping(target = "priceCryptocurrency", source = "vehiclePurchase.priceCryptocurrency")
    @Mapping(target = "date", source = "vehiclePurchase.date")
    PurchaseVehicleDomain toPurchaseVehicleDomain(VehiclePurchase vehiclePurchase);

    @Mapping(target = "fullName", source = "purchaseVehicleDomain.fullName")
    @Mapping(target = "version", source = "purchaseVehicleDomain.version")
    @Mapping(target = "model", source = "purchaseVehicleDomain.model")
    @Mapping(target = "cryptocurrency", source = "purchaseVehicleDomain.cryptocurrency")
    @Mapping(target = "priceUsd", source = "purchaseVehicleDomain.priceUsd")
    @Mapping(target = "priceCryptocurrency", source = "purchaseVehicleDomain.priceCryptocurrency")
    @Mapping(target = "date", source = "purchaseVehicleDomain.date")
    VehiclePurchase toVehiclePurchase(PurchaseVehicleDomain purchaseVehicleDomain);*/




}
