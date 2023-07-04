package com.hyundai.challenge.aplication.port.out.a_common.memory;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.domain.enums.ModelVehicleEnum;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RetrieveModelVehicleInMemoryPort {
    Flux<ModelVehicleDomain> retrieveByConversionIdAndVersion(String conversionId, ModelVehicleEnum versionModel);
}
