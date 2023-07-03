package com.hyundai.challenge.core.services.common;

import com.hyundai.challenge.model.VehicleVersion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public interface CacheVehicleVersionService {
    Flux<VehicleVersion> findByConversionId(String conversionId);

    Mono<List<VehicleVersion>>  save(List<VehicleVersion> list, String conversionId, Duration expiration);
}
