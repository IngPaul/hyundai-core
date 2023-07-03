package com.hyundai.hexchallenge.aplication.port.out.catalog;

import com.hyundai.challenge.model.PostVehicleModelRetrieveResponse;
import reactor.core.publisher.Mono;
@FunctionalInterface
public interface RetrieveVehicleModelsPort {
    Mono<PostVehicleModelRetrieveResponse> retrieve(String model, String cryptocurrency);
}
