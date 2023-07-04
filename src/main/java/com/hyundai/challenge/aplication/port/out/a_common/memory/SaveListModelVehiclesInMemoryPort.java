package com.hyundai.challenge.aplication.port.out.a_common.memory;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@FunctionalInterface
public interface SaveListModelVehiclesInMemoryPort {
    Mono<List<ModelVehicleDomain>> save(List<ModelVehicleDomain> list, String conversionId, Duration expiration);
}
