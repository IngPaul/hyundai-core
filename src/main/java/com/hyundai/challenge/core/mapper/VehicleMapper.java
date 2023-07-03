package com.hyundai.challenge.core.mapper;

import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.challenge.dto.vehicle.VehicleModelDto;
import com.hyundai.challenge.core.entities.VehiclePurchase;
import com.hyundai.challenge.model.PostPurchaseVehicleModel;
import com.hyundai.challenge.model.PostPurchaseVehicleModelRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);
    @Mapping(source = "vehicleModel.name", target = "version")//VER_NOMBRE
    @Mapping(source = "vehicleModel.priceUsd", target = "priceUsd")//VEA_PRECIO_PVP
    @Mapping(source = "model", target = "model")//VEA_PRECIO_PVP
    @Mapping(target = "priceCryptocurrency", ignore = true)
    @Mapping(source="cryptoCurrency" , target = "cryptocurrency")
    VehicleVersion toVehicleVersion(VehicleModelDto vehicleModel, String model, String cryptoCurrency);

    @Mapping(source = "vehicle.priceUsd", target = "priceUsd")
    @Mapping(source = "vehicle.priceCryptocurrency", target = "priceCryptocurrency")
    @Mapping(source = "request.data.fullName", target = "fullName")
    @Mapping(source = "request.data.version", target = "version")
    @Mapping(source = "request.data.model", target = "model")
    @Mapping(source = "request.data.cryptocurrency", target = "cryptocurrency")
    PostPurchaseVehicleModel toPostPurchaseVehicleModel(VehicleVersion vehicle, PostPurchaseVehicleModelRequest request);

    @Mapping(target = "purchaseId",  expression = "java(vehicle.getId().toString())")
   // @Mapping(source = "id", target = "purchaseId")
    @Mapping(target = "date", expression = "java(java.time.format.DateTimeFormatter.ISO_DATE.format(vehicle.getDate()))")
    PostPurchaseVehicleModel toPostPurchaseVehicleModel(VehiclePurchase vehicle);


}
