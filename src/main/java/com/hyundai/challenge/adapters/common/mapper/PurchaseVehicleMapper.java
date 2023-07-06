package com.hyundai.challenge.adapters.common.mapper;

import com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.entities.VehiclePurchase;
import com.hyundai.challenge.domain.purchase.PurchaseVehicleDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PurchaseVehicleMapper {

    PurchaseVehicleMapper INSTANCE = Mappers.getMapper(PurchaseVehicleMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "modelVehicleDomain.version", target = "version")
    @Mapping(source = "modelVehicleDomain.model", target = "model")
    @Mapping(source = "modelVehicleDomain.cryptocurrency", target = "cryptocurrency")
    @Mapping(source = "modelVehicleDomain.priceUsd", target = "priceUsd")
    @Mapping(source = "modelVehicleDomain.priceCryptocurrency", target = "priceCryptocurrency")
    VehiclePurchase toVehiclePurchase(PurchaseVehicleDomain domain);

    List<VehiclePurchase> toVehiclePurchaseList(List<PurchaseVehicleDomain> domainList);

    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "version", target = "modelVehicleDomain.version")
    @Mapping(source = "model", target = "modelVehicleDomain.model")
    @Mapping(source = "cryptocurrency", target = "modelVehicleDomain.cryptocurrency")
    @Mapping(source = "priceUsd", target = "modelVehicleDomain.priceUsd")
    @Mapping(source = "priceCryptocurrency", target = "modelVehicleDomain.priceCryptocurrency")
    PurchaseVehicleDomain toPurchaseVehicleDomain(VehiclePurchase vehiclePurchase);

    List<PurchaseVehicleDomain> toPurchaseVehicleDomainList(List<VehiclePurchase> vehiclePurchaseList);
}
