package com.hyundai.hexchallenge.aplication.port.out.a_common.memory;

import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RetrieveModelVehicleInMemoryPort {
    Flux<ModelVehicleDomain> retrieveByConversionIdAndVersion(String conversionId, ModelVehicleEnum versionModel);
}
