package com.hyundai.hexchallenge.aplication.port.in.a_common.memory;

import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RetrieveListModelVehicleInMemoryUseCase {
    Flux<ModelVehicleDomain> findByConversionId(String conversionId);
}
