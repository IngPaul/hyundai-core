package com.hyundai.challenge.adapters.common.mapper;

import com.hyundai.challenge.model.PostPurchaseReportResponseData;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostPurchaseReportMapper {
    PostPurchaseReportMapper INSTANCE = Mappers.getMapper(PostPurchaseReportMapper.class);

    @Mapping(source = "date", target = "date")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "cryptocurrency", target = "cryptocurrency")
    @Mapping(source = "priceUsd", target = "usdAmount")
    @Mapping(source = "priceCryptocurrency", target = "cryptocurrencyAmount")
    PostPurchaseReportResponseData toPostPurchaseReportResponseData(ModelVehicleDomain domain);
}
