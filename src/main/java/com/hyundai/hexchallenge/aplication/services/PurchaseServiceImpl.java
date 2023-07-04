package com.hyundai.hexchallenge.aplication.services;


import com.hyundai.hexchallenge.configuration.handler.error.CoreError;
import com.hyundai.hexchallenge.aplication.port.in.purchase.VehiclePurchaseSaveUseCase;
import com.hyundai.hexchallenge.aplication.port.out.a_common.memory.RetrieveVersionVehicleInMemoryPort;
import com.hyundai.hexchallenge.aplication.port.out.catalog.RetrieveVersionVehiclePort;
import com.hyundai.hexchallenge.aplication.port.out.purchase.VehiclePurchaseSavePort;
import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.hexchallenge.domain.enums.ModelVehicleEnum;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PurchaseServiceImpl implements VehiclePurchaseSaveUseCase {
    private final VehiclePurchaseSavePort vehiclePurchaseSavePort;
    private final RetrieveVersionVehicleInMemoryPort retrieveVersionVehicleInMemoryPort;
    private final RetrieveVersionVehiclePort retrieveVersionVehiclePort;

    public Mono<ModelVehicleDomain> purchase(ModelVehicleDomain modelVehicleDomain, String conversionId) {
        ModelVehicleEnum ModelVehicleEnum= com.hyundai.hexchallenge.domain.enums.ModelVehicleEnum.fromName(modelVehicleDomain.getModel());
        CryptoCurrencyEnum cryptoCurrencyEnum= CryptoCurrencyEnum.fromName(modelVehicleDomain.getCryptocurrency());
        return retrieveVersionVehicleInMemoryPort.retrieveByConversionIdAndVersion(conversionId, ModelVehicleEnum, modelVehicleDomain.getVersion())
                .switchIfEmpty(retrieveVersionVehiclePort.retrieveByModelAndCryptoAndVersion(ModelVehicleEnum, cryptoCurrencyEnum,modelVehicleDomain.getVersion()))
                .switchIfEmpty(Mono.error(CoreError.ERROR_IN_RETRIEVE_VEHICLE_NOT_FOUND))
                .flatMap(vehiclePurchaseSavePort::purchase);
    }
}
