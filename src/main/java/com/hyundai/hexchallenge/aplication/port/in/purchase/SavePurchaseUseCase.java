package com.hyundai.hexchallenge.aplication.port.in.purchase;

import com.hyundai.challenge.model.PostPurchaseVehicleModelRequest;
import com.hyundai.challenge.model.PostPurchaseVehicleModelResponse;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SavePurchaseUseCase {
    Mono<PostPurchaseVehicleModelResponse> purchase(PostPurchaseVehicleModelRequest request);
}
