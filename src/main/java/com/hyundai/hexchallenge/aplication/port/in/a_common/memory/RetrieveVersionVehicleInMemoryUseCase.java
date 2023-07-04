package com.hyundai.hexchallenge.aplication.port.in.a_common.memory;

import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RetrieveVersionVehicleInMemoryUseCase {
    Flux<ModelVehicleDomain> retrieveByConversionIdAndVersion(String conversionId, String versionModel, String  version);
}
