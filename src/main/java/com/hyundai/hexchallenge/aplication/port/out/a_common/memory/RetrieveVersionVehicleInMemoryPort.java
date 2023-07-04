package com.hyundai.hexchallenge.aplication.port.out.a_common.memory;

import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.enums.ModelVehicleEnum;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface RetrieveVersionVehicleInMemoryPort {
    Mono<ModelVehicleDomain> retrieveByConversionIdAndVersion(String conversionId, ModelVehicleEnum versionModel, String  version);
}
