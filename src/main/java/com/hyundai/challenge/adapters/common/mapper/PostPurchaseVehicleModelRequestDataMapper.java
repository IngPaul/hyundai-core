package com.hyundai.challenge.adapters.common.mapper;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.model.PostPurchaseVehicleModelRequestData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PostPurchaseVehicleModelRequestDataMapper {
    PostPurchaseVehicleModelRequestDataMapper INSTANCE = Mappers.getMapper(PostPurchaseVehicleModelRequestDataMapper.class);
    @Mapping(target = "msg", ignore = true)
    @Mapping(target = "conversionId", source = "convertionId")
    ModelVehicleDomain toModelVehicleDomain(PostPurchaseVehicleModelRequestData requestData);

}
