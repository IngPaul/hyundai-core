package com.hyundai.challenge.adapters.common.mapper.news;


import com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.entities.VehiclePurchase;
import com.hyundai.challenge.domain.report.ReportPurchaseVehicleDomain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReportPurchaseVehicleDomainMapper {
    ReportPurchaseVehicleDomainMapper INSTANCE = Mappers.getMapper(ReportPurchaseVehicleDomainMapper.class);
    ReportPurchaseVehicleDomain toDomainReport(VehiclePurchase vehiclePurchase);
}
