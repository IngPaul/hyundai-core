package com.hyundai.challenge.aplication.services;


import com.hyundai.challenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.challenge.configuration.handler.error.CoreError;
import com.hyundai.challenge.aplication.port.in.purchase.VehiclePurchaseSaveUseCase;
import com.hyundai.challenge.aplication.port.out.a_common.memory.RetrieveVersionVehicleInMemoryPort;
import com.hyundai.challenge.aplication.port.out.catalog.RetrieveVersionVehiclePort;
import com.hyundai.challenge.aplication.port.out.purchase.VehiclePurchaseSavePort;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.domain.enums.ModelVehicleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements VehiclePurchaseSaveUseCase {
    private final VehiclePurchaseSavePort vehiclePurchaseSavePort;
    private final RetrieveVersionVehicleInMemoryPort retrieveVersionVehicleInMemoryPort;
    private final RetrieveVersionVehiclePort retrieveVersionVehiclePort;
    private final AddPriceCriptocurrencyPort addPriceCriptocurrencyPort;

    public Mono<ModelVehicleDomain> purchase(ModelVehicleDomain modelVehicleDomain, String conversionId) {
        ModelVehicleEnum ModelVehicleEnum= com.hyundai.challenge.domain.enums.ModelVehicleEnum.fromName(modelVehicleDomain.getModel());
        CryptoCurrencyEnum cryptoCurrencyEnum= CryptoCurrencyEnum.fromName(modelVehicleDomain.getCryptocurrency());
        return retrieveVersionVehicleInMemoryPort.retrieveByConversionIdAndVersion(conversionId, ModelVehicleEnum, modelVehicleDomain.getVersion())
                .switchIfEmpty(retrieveVersionVehiclePort.retrieveByModelAndCryptoAndVersion(ModelVehicleEnum, cryptoCurrencyEnum,modelVehicleDomain.getVersion()))
                .switchIfEmpty(Mono.error(CoreError.ERROR_IN_RETRIEVE_VEHICLE_NOT_FOUND))
                .map(modelVehicleDomainRetrieve->{
                    modelVehicleDomain.setCryptocurrency(modelVehicleDomainRetrieve.getCryptocurrency());
                    modelVehicleDomain.setPriceCryptocurrency(modelVehicleDomainRetrieve.getPriceCryptocurrency());
                    modelVehicleDomain.setPriceUsd(modelVehicleDomainRetrieve.getPriceUsd());
                    modelVehicleDomain.setDate(LocalDate.now());
                    return modelVehicleDomain;
                })
                .flatMap(modelVehicle -> addPriceCriptocurrencyPort.add(cryptoCurrencyEnum,modelVehicle))
                .flatMap(vehiclePurchaseSavePort::purchase);
    }
}
