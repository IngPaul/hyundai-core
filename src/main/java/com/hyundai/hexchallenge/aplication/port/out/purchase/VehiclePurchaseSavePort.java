package com.hyundai.hexchallenge.aplication.port.out.purchase;

import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface VehiclePurchaseSavePort {
    Mono<ModelVehicleDomain> purchase(ModelVehicleDomain request);
}
