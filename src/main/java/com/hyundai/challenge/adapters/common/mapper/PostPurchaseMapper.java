package com.hyundai.challenge.adapters.common.mapper;


import com.hyundai.challenge.model.PostPurchaseVehicleModel;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostPurchaseMapper {

    PostPurchaseMapper INSTANCE = Mappers.getMapper(PostPurchaseMapper.class);

    @Mapping( target = "date", expression = "java(java.time.format.DateTimeFormatter.ISO_DATE.format(domain.getDate()))")
    @Mapping( target = "purchaseId", expression = "java(domain.getId().toString())")
    PostPurchaseVehicleModel mapToPostPurchaseModel(ModelVehicleDomain domain);

}
