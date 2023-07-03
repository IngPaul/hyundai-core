package com.hyundai.hexchallenge.aplication.port.out.a_common.memory;

import com.hyundai.challenge.model.VehicleVersion;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@FunctionalInterface
public interface SaveListVehicleModelsInMemoryPort {
    Mono<List<VehicleVersion>> save(List<VehicleVersion> list, String conversionId, Duration expiration);
}
