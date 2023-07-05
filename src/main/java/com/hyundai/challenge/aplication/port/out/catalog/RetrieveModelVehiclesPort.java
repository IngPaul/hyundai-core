package com.hyundai.challenge.aplication.port.out.catalog;

import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.base.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.domain.base.enums.ModelVehicleEnum;
import com.hyundai.challenge.domain.catalog.CatalogVehicleDomain;
import reactor.core.publisher.Flux;
@FunctionalInterface
public interface RetrieveModelVehiclesPort {
    Flux<CatalogVehicleDomain> retrieveByModelAndCrypto(ModelVehicleEnum model, CryptoCurrencyEnum cryptocurrency);
}
