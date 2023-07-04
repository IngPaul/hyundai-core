package com.hyundai.hexchallenge.aplication.port.in.purchase;

import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface VehiclePurchaseSaveUseCase {
    Mono<ModelVehicleDomain> purchase(ModelVehicleDomain request, String conversionId);
}
