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

    public Mono<PurchaseVehicleDomain> purchase(PurchaseVehicleDomain modelVehicleDomain, String conversionId) {
        ModelVehicleEnum modelVehicleEnum= ModelVehicleEnum.fromName(modelVehicleDomain.getModelVehicleDomain().getModel());
        CryptoCurrencyEnum cryptoCurrencyEnum= CryptoCurrencyEnum.fromName(modelVehicleDomain.getModelVehicleDomain().getCryptocurrency());
        return  retrieveVersionVehicleInMemoryPort.retrieveByConversionIdAndVersion(conversionId, modelVehicleEnum, modelVehicleDomain.getVersion())
                .switchIfEmpty(retrieveVersionVehiclePort.retrieveByModelAndCryptoAndVersion(modelVehicleEnum, cryptoCurrencyEnum, modelVehicleDomain.getVersion()))
                .switchIfEmpty(Mono.error(CoreError.ERROR_IN_RETRIEVE_VEHICLE_NOT_FOUND))
                .map(modelVehicleDomainRetrieve -> copyData(modelVehicleDomain, modelVehicleDomainRetrieve))
                .flatMap(modelVehicle -> addPriceCriptocurrencyPort.add(cryptoCurrencyEnum, modelVehicle.getModelVehicleDomain())
                        .flatMap(vehiclePrice->{
                            modelVehicle.setModelVehicleDomain(vehiclePrice);
                            return vehiclePurchaseSavePort.purchase(modelVehicle);
                        }));
    }
    private PurchaseVehicleDomain copyData(PurchaseVehicleDomain target, ModelVehicleDomain source){
        target.getModelVehicleDomain().setCryptocurrency(source.getCryptocurrency());
        target.getModelVehicleDomain().setPriceCryptocurrency(source.getPriceCryptocurrency());
        target.getModelVehicleDomain().setPriceUsd(source.getPriceUsd());
        target.setDate(LocalDate.now());
        return target;
    }
}
