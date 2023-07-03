package com.hyundai.hexchallenge.aplication.port.out.a_common.memory;

import com.hyundai.challenge.model.VehicleVersion;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RetrieveListVehicleModelsOfMemoryPort {
    Flux<VehicleVersion> findByConversionId(String conversionId);
}
