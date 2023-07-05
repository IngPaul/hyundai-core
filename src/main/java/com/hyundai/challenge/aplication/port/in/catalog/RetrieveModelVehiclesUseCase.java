package com.hyundai.challenge.aplication.port.in.catalog;

import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.catalog.CatalogVehicleDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface RetrieveModelVehiclesUseCase {
    Mono<CatalogVehicleDomain> retrieveByModelAndCrypto(String model, String cryptocurrency);
}
