package com.hyundai.hexchallenge.aplication.port.out.catalog;

import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.hexchallenge.domain.enums.ModelVehicleEnum;
import reactor.core.publisher.Flux;
@FunctionalInterface
public interface RetrieveModelVehiclesPort {
    Flux<ModelVehicleDomain> retrieveByModelAndCrypto(ModelVehicleEnum model, CryptoCurrencyEnum cryptocurrency);
}
