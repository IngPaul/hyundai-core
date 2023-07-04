package com.hyundai.hexchallenge.aplication.port.in.a_common.convertion;

import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface AddPriceCriptocurrencyUseCase {
     Mono<ModelVehicleDomain> add(String cryptocurrency, ModelVehicleDomain modelVehicleDomain);
}
