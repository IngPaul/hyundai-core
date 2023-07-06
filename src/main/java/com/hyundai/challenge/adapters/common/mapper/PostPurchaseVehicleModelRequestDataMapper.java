package com.hyundai.challenge.adapters.common.mapper;

import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.model.PostPurchaseVehicleModelRequestData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PostPurchaseVehicleModelRequestDataMapper {
    PostPurchaseVehicleModelRequestDataMapper INSTANCE = Mappers.getMapper(PostPurchaseVehicleModelRequestDataMapper.class);

    ModelVehicleDomain toModelVehicleDomain(PostPurchaseVehicleModelRequestData requestData);

}
