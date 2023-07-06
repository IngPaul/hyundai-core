package com.hyundai.challenge.adapters.common.mapper;

import com.hyundai.challenge.model.PostPurchaseReportResponseData;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostPurchaseReportMapper {
    PostPurchaseReportMapper INSTANCE = Mappers.getMapper(PostPurchaseReportMapper.class);

    PostPurchaseReportResponseData toPostPurchaseReportResponseData(ModelVehicleDomain domain);
}
