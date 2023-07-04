package com.hyundai.challenge.aplication.port.out.a_common.convertion;

import com.hyundai.challenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface AddPriceCriptocurrencyPort {
     Mono<ModelVehicleDomain> add(CryptoCurrencyEnum cryptocurrency, ModelVehicleDomain modelVehicleDomain);
}
