package com.hyundai.hexchallenge.aplication.services;

import com.hyundai.hexchallenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.hexchallenge.aplication.port.in.catalog.RetrieveModelVehiclesUseCase;
import com.hyundai.hexchallenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.hexchallenge.aplication.port.out.catalog.RetrieveModelVehiclesPort;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.enums.ModelVehicleEnum;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class RetrieveVehicleServiceImpl implements RetrieveModelVehiclesUseCase {
    private final RetrieveModelVehiclesPort retrieveModelVehiclesPort;
    private final AddPriceCriptocurrencyPort addPriceCriptocurrencyPort;
    

    @Override
    public Flux<ModelVehicleDomain> retrieveByModelAndCrypto(String model, String cryptocurrency) {
        ModelVehicleEnum modelVehicleEnum= com.hyundai.hexchallenge.domain.enums.ModelVehicleEnum.fromName(cryptocurrency);
        CryptoCurrencyEnum cryptoCurrencyEnum= CryptoCurrencyEnum.fromName(model);
        return retrieveModelVehiclesPort.retrieveByModelAndCrypto(modelVehicleEnum, cryptoCurrencyEnum)
                .flatMap(vehicle->addPriceCriptocurrencyPort.add(cryptoCurrencyEnum,vehicle));
    }
}
