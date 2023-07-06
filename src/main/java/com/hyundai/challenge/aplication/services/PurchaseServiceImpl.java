package com.hyundai.challenge.aplication.services;


import com.hyundai.challenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.challenge.configuration.handler.error.CoreError;
import com.hyundai.challenge.aplication.port.in.purchase.VehiclePurchaseSaveUseCase;
import com.hyundai.challenge.aplication.port.out.a_common.memory.RetrieveVersionVehicleInMemoryPort;
import com.hyundai.challenge.aplication.port.out.catalog.RetrieveVersionVehiclePort;
import com.hyundai.challenge.aplication.port.out.purchase.VehiclePurchaseSavePort;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.base.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.domain.base.enums.ModelVehicleEnum;
import com.hyundai.challenge.domain.purchase.PurchaseVehicleDomain;
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

    public Mono<PurchaseVehicleDomain> purchase(PurchaseVehicleDomain purchaseVehicleDomain, String conversionId, String version) {
        ModelVehicleEnum modelVehicleEnum= ModelVehicleEnum.fromName(purchaseVehicleDomain.getModelVehicleDomain().getModel());
        CryptoCurrencyEnum cryptoCurrencyEnum= CryptoCurrencyEnum.fromName(purchaseVehicleDomain.getModelVehicleDomain().getCryptocurrency());
        return  retrieveVersionVehicleInMemoryPort.retrieveByConversionIdAndVersion(conversionId, modelVehicleEnum, version)
                .switchIfEmpty(retrieveVersionVehiclePort.retrieveByModelAndCryptoAndVersion(modelVehicleEnum, cryptoCurrencyEnum, version)
                        .flatMap(modelVehicleDomain -> addPriceCriptocurrencyPort.add(cryptoCurrencyEnum, modelVehicleDomain)))
                .switchIfEmpty(Mono.error(CoreError.ERROR_IN_RETRIEVE_VEHICLE_NOT_FOUND))
                .map(modelVehicleDomain -> copyData(purchaseVehicleDomain, modelVehicleDomain))
                .flatMap(vehiclePurchaseSavePort::purchase);
    }
    private PurchaseVehicleDomain copyData(PurchaseVehicleDomain target, ModelVehicleDomain source){
        target.getModelVehicleDomain().setCryptocurrency(source.getCryptocurrency());
        target.getModelVehicleDomain().setPriceCryptocurrency(source.getPriceCryptocurrency());
        target.getModelVehicleDomain().setPriceUsd(source.getPriceUsd());
        target.setModelVehicleDomain(source);
        target.setDate(LocalDate.now());
        return target;
    }
}
