package com.hyundai.hexchallenge.aplication.port.out.purchase;

import com.hyundai.challenge.model.PostPurchaseVehicleModelRequest;
import com.hyundai.challenge.model.PostPurchaseVehicleModelResponse;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveVehiclePurchaseUseCasePort {
    Mono<PostPurchaseVehicleModelResponse> purchase(PostPurchaseVehicleModelRequest request);
}
