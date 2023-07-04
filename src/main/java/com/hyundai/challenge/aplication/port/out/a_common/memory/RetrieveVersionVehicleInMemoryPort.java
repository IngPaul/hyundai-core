package com.hyundai.challenge.aplication.port.out.a_common.memory;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.domain.enums.ModelVehicleEnum;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface RetrieveVersionVehicleInMemoryPort {
    Mono<ModelVehicleDomain> retrieveByConversionIdAndVersion(String conversionId, ModelVehicleEnum versionModel, String  version);
}
