package com.hyundai.challenge.aplication.port.in.a_common.memory;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RetrieveListModelVehicleInMemoryUseCase {
    Flux<ModelVehicleDomain> findByConversionId(String conversionId);
}