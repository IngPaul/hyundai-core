package com.hyundai.challenge.adapters.common.mapper;


import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.entities.VehiclePurchase;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;


@Mapper(componentModel = "spring")
public interface ModelVehicleDomainMapper {
    ModelVehicleDomainMapper INSTANCE =Mappers.getMapper(ModelVehicleDomainMapper.class);

    ModelVehicleDomain entityToDomain(VehiclePurchase vehiclePurchase);


    VehiclePurchase domainToEntity(ModelVehicleDomain modelVehicleDomain);

    @Mapping(source = "model", target = "fullName")
    @Mapping(source = "vehicle.version", target = "version")
    @Mapping(source = "vehicle.priceUsd", target = "priceUsd")
    @Mapping(source = "vehicle.priceCryptocurrency", target = "priceCryptocurrency")
    @Mapping(source = "vehicle.cryptocurrency", target = "cryptocurrency")
    ModelVehicleDomain vehicleVersionToDomain(VehicleVersion vehicle, String model);


    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "model.priceUsd", target = "priceUsd")
    @Mapping(source = "model.priceCryptocurrency", target = "priceCryptocurrency")
    @Mapping(source = "model.cryptocurrency", target = "cryptocurrency")
    ModelVehicleDomain toModelVehicleDomain (VehicleVersion model, String fullName);
}
