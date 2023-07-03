package com.hyundai.hexchallenge.aplication.port.in.catalog;

import com.hyundai.challenge.model.PostVehicleModelRetrieveResponse;
import reactor.core.publisher.Mono;
@FunctionalInterface
public interface RetrieveVehicleModelsUseCase {
    Mono<PostVehicleModelRetrieveResponse> retrieve(String model, String cryptocurrency);
}
