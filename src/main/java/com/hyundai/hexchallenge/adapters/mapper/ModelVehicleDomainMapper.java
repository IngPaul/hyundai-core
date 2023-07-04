package com.hyundai.hexchallenge.adapters.mapper;


import com.hyundai.challenge.model.PostPurchaseReportResponseData;
import com.hyundai.challenge.model.PostPurchaseVehicleModelRequest;
import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.hexchallenge.adapters.out.dbs.sql.postgres.springdata.entities.VehiclePurchase;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ModelVehicleDomainMapper {
    ModelVehicleDomainMapper INSTANCE =Mappers.getMapper(ModelVehicleDomainMapper.class);
    ModelVehicleDomain entityToDomain(VehiclePurchase vehiclePurchase);

    VehiclePurchase domainToEntity(ModelVehicleDomain modelVehicleDomain);
}
