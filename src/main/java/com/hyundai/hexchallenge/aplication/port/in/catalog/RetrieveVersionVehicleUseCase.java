package com.hyundai.hexchallenge.aplication.port.in.catalog;

import com.hyundai.hexchallenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface RetrieveVersionVehicleUseCase {
    Mono<ModelVehicleDomain> retrieveByModelAndCryptoAndVersion(String model, String cryptocurrency, String version);
}
