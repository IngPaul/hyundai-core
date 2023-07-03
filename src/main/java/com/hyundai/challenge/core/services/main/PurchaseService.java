package com.hyundai.challenge.core.services.main;

import com.hyundai.challenge.model.PostPurchaseVehicleModelRequest;
import com.hyundai.challenge.model.PostPurchaseVehicleModelResponse;
import reactor.core.publisher.Mono;

public interface PurchaseService {
    Mono<PostPurchaseVehicleModelResponse> purchase(PostPurchaseVehicleModelRequest request);
}
