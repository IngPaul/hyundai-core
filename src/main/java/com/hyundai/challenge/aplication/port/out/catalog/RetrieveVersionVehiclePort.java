package com.hyundai.challenge.aplication.port.out.catalog;

import com.hyundai.challenge.domain.base.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.base.enums.ModelVehicleEnum;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface RetrieveVersionVehiclePort {
    Mono<ModelVehicleDomain> retrieveByModelAndCryptoAndVersion(ModelVehicleEnum model, CryptoCurrencyEnum cryptocurrency, String version);
}
