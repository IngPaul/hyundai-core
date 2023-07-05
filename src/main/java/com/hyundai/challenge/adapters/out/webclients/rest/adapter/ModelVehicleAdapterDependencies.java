package com.hyundai.challenge.adapters.out.webclients.rest.adapter;

import com.hyundai.challenge.adapters.common.dto.vehicle.VehicleModelDto;
import com.hyundai.challenge.adapters.out.webclients.rest.webclient.ModelVehicleClient;
import com.hyundai.challenge.adapters.out.webclients.rest.webclient.PriceCryptoCurrencyClient;
import com.hyundai.challenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.challenge.aplication.port.out.catalog.RetrieveModelVehiclesPort;
import com.hyundai.challenge.aplication.port.out.catalog.RetrieveVersionVehiclePort;

import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.base.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.domain.base.enums.ModelVehicleEnum;
import com.hyundai.challenge.domain.catalog.CatalogVehicleDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import static java.math.RoundingMode.DOWN;

@AllArgsConstructor
@Component
public class ModelVehicleAdapterDependencies implements RetrieveModelVehiclesPort, RetrieveVersionVehiclePort, AddPriceCriptocurrencyPort {
    private final ModelVehicleClient modelVehicleClient;
    private final PriceCryptoCurrencyClient priceCryptoCurrencyClient;

    @Override
    public Flux<CatalogVehicleDomain> retrieveByModelAndCrypto(ModelVehicleEnum model, CryptoCurrencyEnum cryptocurrency) {
        xxxxxxxx?? requiero hacer algo
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
        return priceCryptoCurrencyClient.getPrice(cryptocurrency)
                .map(priceCryptocurrency->{
                    BigDecimal priceVehicleInCryptocurrency = modelVehicleDomain.getPriceUsd().divide(priceCryptocurrency, 2, DOWN);
                    modelVehicleDomain.setPriceCryptocurrency(priceVehicleInCryptocurrency);
                    modelVehicleDomain.setCryptocurrency(cryptocurrency.getName());
                    return modelVehicleDomain;
                });
    }

    @Override
    public Mono<ModelVehicleDomain> add(CryptoCurrencyEnum cryptocurrency, ModelVehicleDomain modelVehicleDomain) {
        return addPriceInModel(modelVehicleDomain, cryptocurrency);
    }
}
