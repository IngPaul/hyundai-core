package com.hyundai.hexchallenge.adapters.mapper;


import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.hexchallenge.adapters.out.dbs.sql.postgres.springdata.entities.VehiclePurchase;
import com.hyundai.challenge.model.PostPurchaseReportResponseData;
import com.hyundai.challenge.model.PostPurchaseVehicleModelRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    PurchaseMapper INSTANCE =Mappers.getMapper(PurchaseMapper.class);

    @Mapping(source = "request.data.fullName", target = "fullName")
    @Mapping(source = "vehicle.version", target = "version")
    @Mapping(source = "request.data.model", target = "model")
    @Mapping(source = "request.data.cryptocurrency", target = "cryptocurrency")
    @Mapping(source = "vehicle.priceUsd", target = "priceUsd")
    @Mapping(source = "vehicle.priceCryptocurrency", target = "priceCryptocurrency")
    @Mapping(target = "date", expression = "java(java.time.LocalDate.now())")
    VehiclePurchase toPurchaseEntity(VehicleVersion vehicle, PostPurchaseVehicleModelRequest request);

    @Mapping( target = "date", expression = "java(java.time.format.DateTimeFormatter.ISO_DATE.format(purchase.getDate()))")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "cryptocurrency", target = "cryptocurrency")
    @Mapping(source = "priceUsd", target = "usdAmount")
    @Mapping(source = "priceCryptocurrency", target = "cryptocurrencyAmount")
    PostPurchaseReportResponseData toPostPurchaseReportResponseData(VehiclePurchase purchase);
}
