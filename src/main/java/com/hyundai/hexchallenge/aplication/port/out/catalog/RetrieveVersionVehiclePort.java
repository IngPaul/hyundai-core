package com.hyundai.hexchallenge.aplication.port.out.catalog;

import com.hyundai.hexchallenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.enums.ModelVehicleEnum;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface RetrieveVersionVehiclePort {
    Mono<ModelVehicleDomain> retrieveByModelAndCryptoAndVersion(ModelVehicleEnum model, CryptoCurrencyEnum cryptocurrency, String version);
}
