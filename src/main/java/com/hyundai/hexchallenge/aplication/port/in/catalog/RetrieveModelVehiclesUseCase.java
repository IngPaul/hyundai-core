package com.hyundai.hexchallenge.aplication.port.in.catalog;

import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Flux;
@FunctionalInterface
public interface RetrieveModelVehiclesUseCase {
    Flux<ModelVehicleDomain> retrieveByModelAndCrypto(String model, String cryptocurrency);
}
