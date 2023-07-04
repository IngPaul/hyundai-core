package com.hyundai.challenge.aplication.port.out.a_common.memory;

import com.hyundai.challenge.model.VehicleVersion;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RetrieveListModelVehiclesOfMemoryPort {
    Flux<VehicleVersion> findByConversionId(String conversionId);
}
