package com.hyundai.challenge.aplication.port.in.a_common.memory;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RetrieveVersionVehicleInMemoryUseCase {
    Flux<ModelVehicleDomain> retrieveByConversionIdAndVersion(String conversionId, String versionModel, String  version);
}