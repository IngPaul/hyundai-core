package com.hyundai.challenge.aplication.port.out.catalog;

import com.hyundai.challenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.domain.enums.ModelVehicleEnum;
import reactor.core.publisher.Flux;
@FunctionalInterface
public interface RetrieveModelVehiclesPort {
    Flux<ModelVehicleDomain> retrieveByModelAndCrypto(ModelVehicleEnum model, CryptoCurrencyEnum cryptocurrency);
}
