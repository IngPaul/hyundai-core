package com.hyundai.challenge.adapters.common.mapper.news;


import com.hyundai.challenge.domain.purchase.PurchaseVehicleDomain;
import com.hyundai.challenge.model.PostPurchaseVehicleModelRequestData;
import com.hyundai.challenge.model.PostPurchaseVehicleModelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
    @Mapping(source = "modelVehicleDomain.cryptocurrency", target = "data.cryptocurrency")
    @Mapping(target = "data.priceUsd", ignore = true)
    @Mapping(target = "data.priceCryptocurrency", ignore = true)
    @Mapping(target = "data.date", ignore = true)
    PostPurchaseVehicleModelResponse toPostPurchaseVehicleModelResponse(PurchaseVehicleDomain domain);

    // Mapeo de la lista de PurchaseVehicleDomain a la lista de PostPurchaseVehicleModelResponse
    List<PostPurchaseVehicleModelResponse> toPostPurchaseVehicleModelResponseList(List<PurchaseVehicleDomain> domainList);


}
