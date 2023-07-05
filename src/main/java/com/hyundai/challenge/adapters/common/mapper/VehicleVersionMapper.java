package com.hyundai.challenge.adapters.common.mapper;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.model.VehicleVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleVersionMapper {
    VehicleVersionMapper INSTANCE = Mappers.getMapper(VehicleVersionMapper.class);

    @Mapping(source = "model", target = "model")
    @Mapping(source = "domain.priceUsd", target = "priceUsd")
    @Mapping(source = "domain.priceCryptocurrency", target = "priceCryptocurrency")
    @Mapping(source = "domain.cryptocurrency", target = "cryptocurrency")
    VehicleVersion toVehicleVersion(ModelVehicleDomain domain, String model);
}
