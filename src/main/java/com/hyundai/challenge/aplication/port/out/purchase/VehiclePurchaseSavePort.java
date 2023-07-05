package com.hyundai.challenge.aplication.port.out.purchase;

import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.purchase.PurchaseVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface VehiclePurchaseSavePort {
    Mono<PurchaseVehicleDomain> purchase(PurchaseVehicleDomain request);
}
