package com.hyundai.challenge.aplication.port.in.catalog;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Flux;
@FunctionalInterface
public interface RetrieveModelVehiclesUseCase {
    Flux<ModelVehicleDomain> retrieveByModelAndCrypto(String model, String cryptocurrency);
}
