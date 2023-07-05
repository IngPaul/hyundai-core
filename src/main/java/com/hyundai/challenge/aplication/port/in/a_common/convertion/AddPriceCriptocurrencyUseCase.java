package com.hyundai.challenge.aplication.port.in.a_common.convertion;

import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface AddPriceCriptocurrencyUseCase {
     Mono<ModelVehicleDomain> add(String cryptocurrency, ModelVehicleDomain modelVehicleDomain);
}
