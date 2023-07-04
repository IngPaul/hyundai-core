package com.hyundai.hexchallenge.aplication.port.out.a_common.convertion;

import com.hyundai.hexchallenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface AddPriceCriptocurrencyPort {
     Mono<ModelVehicleDomain> add(CryptoCurrencyEnum cryptocurrency, ModelVehicleDomain modelVehicleDomain);
}
