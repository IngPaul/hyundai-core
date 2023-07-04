package com.hyundai.hexchallenge.adapters.out.webclients.restClient.adapter;

import com.hyundai.challenge.dto.vehicle.VehicleModelDto;
import com.hyundai.hexchallenge.adapters.out.webclients.restClient.webclient.ModelVehicleClient;
import com.hyundai.hexchallenge.adapters.out.webclients.restClient.webclient.PriceCryptoCurrencyClient;
import com.hyundai.hexchallenge.aplication.port.out.catalog.RetrieveModelVehiclesPort;
import com.hyundai.hexchallenge.aplication.port.out.catalog.RetrieveVersionVehiclePort;

import com.hyundai.hexchallenge.domain.ModelVehicleDomain;
import com.hyundai.hexchallenge.domain.enums.CryptoCurrencyEnum;
import com.hyundai.hexchallenge.domain.enums.ModelVehicleEnum;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@AllArgsConstructor
public class ModelVehicleAdapterPrice implements RetrieveModelVehiclesPort, RetrieveVersionVehiclePort  {
    private final ModelVehicleClient modelVehicleClient;
    private final PriceCryptoCurrencyClient priceCryptoCurrencyClient;

    @Override
    public Flux<ModelVehicleDomain> retrieveByModelAndCrypto(ModelVehicleEnum model, CryptoCurrencyEnum cryptocurrency) {
        return retrieveVehiclesWithPrice(model,cryptocurrency);
    }
    @Override
    public Mono<ModelVehicleDomain> retrieveByModelAndCryptoAndVersion(ModelVehicleEnum model, CryptoCurrencyEnum cryptocurrency, String version) {
        return retrieveVehiclesWithPrice(model,cryptocurrency)
                .filter(vehicle->vehicle.getVersion().equals(version))
                .next();
    }

    public Flux<ModelVehicleDomain> retrieveVehiclesWithPrice(ModelVehicleEnum model, CryptoCurrencyEnum cryptocurrency) {
        return modelVehicleClient.getModelVehicle(model.getId())
                .map(version->buildModel(version, model.getName()))
                .flatMap(modelBuilding->addPriceInModel(modelBuilding, cryptocurrency));
    }

    private ModelVehicleDomain buildModel(VehicleModelDto version, String model) {
        ModelVehicleDomain modelVehicleDomain = new ModelVehicleDomain();
        modelVehicleDomain.setModel(model);
        modelVehicleDomain.setVersion(version.getName());
        modelVehicleDomain.setPriceUsd(BigDecimal.valueOf(version.getPriceUsd()));
        return modelVehicleDomain;
    }

    private Mono<ModelVehicleDomain> addPriceInModel(ModelVehicleDomain modelVehicleDomain,CryptoCurrencyEnum cryptocurrency ){
        return priceCryptoCurrencyClient.getPrice(cryptocurrency).map(price->{
            modelVehicleDomain.setPriceCryptocurrency(price);
            return modelVehicleDomain;
        });
    }
}
