package com.hyundai.hexchallenge.aplication.port.in.a_common.memory;

import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@FunctionalInterface
public interface SaveListModelVehiclesInMemoryUseCase {
    Mono<List<ModelVehicleDomain>> save(List<VehicleVersion> list, String conversionId, Duration expiration);
}
