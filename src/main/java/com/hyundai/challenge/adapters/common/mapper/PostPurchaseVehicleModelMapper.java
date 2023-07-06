package com.hyundai.challenge.adapters.common.mapper;


import com.hyundai.challenge.domain.purchase.PurchaseVehicleDomain;
import com.hyundai.challenge.model.PostPurchaseVehicleModelRequestData;
import com.hyundai.challenge.model.PostPurchaseVehicleModelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PostPurchaseVehicleModelMapper {
    PostPurchaseVehicleModelMapper INSTANCE = Mappers.getMapper(PostPurchaseVehicleModelMapper.class);
    @Mapping(target = "convertionId", source = "convertionId")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "modelVehicleDomain.version", source = "version")
    @Mapping(target = "modelVehicleDomain.model", source = "model")
    @Mapping(target = "modelVehicleDomain.cryptocurrency", source = "cryptocurrency")
    @Mapping(target = "modelVehicleDomain.priceUsd", ignore = true)
    @Mapping(target = "modelVehicleDomain.priceCryptocurrency", ignore = true)
    PurchaseVehicleDomain toPurchaseVehicleDomain(PostPurchaseVehicleModelRequestData requestData);




    @Mapping(source = "fullName", target = "data.fullName")
    @Mapping(source = "modelVehicleDomain.version", target = "data.version")
    @Mapping(source = "modelVehicleDomain.model", target = "data.model")
    @Mapping(source = "modelVehicleDomain.priceUsd", target = "data.priceUsd")
    @Mapping(source = "modelVehicleDomain.cryptocurrency", target = "data.cryptocurrency")
    @Mapping(source = "modelVehicleDomain.priceCryptocurrency", target = "data.priceCryptocurrency")
    @Mapping(target = "data.purchaseId", expression = "java(getToStringOfUuid(modelVehicleDomain.getId()))")
    PostPurchaseVehicleModelResponse toPostPurchaseVehicleModelResponse(PurchaseVehicleDomain domain);
    default String getToStringOfUuid(UUID uuid){
        if (uuid!=null)
            return uuid.toString();
        return null;
    }
    List<PostPurchaseVehicleModelResponse> toPostPurchaseVehicleModelResponseList(List<PurchaseVehicleDomain> domainList);


}
