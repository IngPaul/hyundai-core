package com.hyundai.challenge.aplication.port.in.purchase;

import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.purchase.PurchaseVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface VehiclePurchaseSaveUseCase {
    Mono<PurchaseVehicleDomain> purchase(PurchaseVehicleDomain request, String conversionId, String version);
}
