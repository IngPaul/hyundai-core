package com.hyundai.challenge.aplication.port.out.a_common.convertion;

import com.hyundai.challenge.domain.base.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface AddPriceCriptocurrencyPort {
     Mono<ModelVehicleDomain> add(CryptoCurrencyEnum cryptocurrency, ModelVehicleDomain modelVehicleDomain);
}
