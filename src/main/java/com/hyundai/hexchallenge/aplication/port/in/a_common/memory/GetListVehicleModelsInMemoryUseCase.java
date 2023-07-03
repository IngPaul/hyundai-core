package com.hyundai.hexchallenge.aplication.port.in.a_common.memory;

import com.hyundai.challenge.model.VehicleVersion;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface GetListVehicleModelsInMemoryUseCase {
    Flux<VehicleVersion> findByConversionId(String conversionId);
}
