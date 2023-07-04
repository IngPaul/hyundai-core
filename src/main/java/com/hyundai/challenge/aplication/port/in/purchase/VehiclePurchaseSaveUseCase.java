package com.hyundai.challenge.aplication.port.in.purchase;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface VehiclePurchaseSaveUseCase {
    Mono<ModelVehicleDomain> purchase(ModelVehicleDomain request, String conversionId);
}
