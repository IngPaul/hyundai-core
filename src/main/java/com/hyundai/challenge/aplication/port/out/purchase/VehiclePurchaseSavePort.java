package com.hyundai.challenge.aplication.port.out.purchase;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface VehiclePurchaseSavePort {
    Mono<ModelVehicleDomain> purchase(ModelVehicleDomain request);
}
