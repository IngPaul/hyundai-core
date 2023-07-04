package com.hyundai.challenge.aplication.port.in.catalog;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface RetrieveVersionVehicleUseCase {
    Mono<ModelVehicleDomain> retrieveByModelAndCryptoAndVersion(String model, String cryptocurrency, String version);
}
