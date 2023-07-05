package com.hyundai.challenge.adapters.out.webclients.rest.adapter;

import Util.MockData;
import com.hyundai.challenge.adapters.common.dto.vehicle.VehicleModelDto;
import com.hyundai.challenge.adapters.out.webclients.rest.webclient.ModelVehicleClient;
import com.hyundai.challenge.adapters.out.webclients.rest.webclient.PriceCryptoCurrencyClient;
import com.hyundai.challenge.domain.base.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.domain.base.enums.ModelVehicleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class ModelVehicleAdapterDependenciesTest {
    @Mock private ModelVehicleClient modelVehicleClient;
    @Mock private PriceCryptoCurrencyClient priceCryptoCurrencyClient;
    @InjectMocks ModelVehicleAdapterDependencies modelVehicleAdapterDependencies;
    private final static ModelVehicleEnum MODEL_ENUM=ModelVehicleEnum.ALL_NEW_TUCSON;
    private final static CryptoCurrencyEnum CRYPTO_CURRENCY_ENUM=CryptoCurrencyEnum.BTC;
    @Test
    void retrieveByModelAndCrypto() {
        VehicleModelDto vehicle=MockData.getVehicleModelDto();
        vehicle.setPriceUsd(80000);
        Mockito.when(modelVehicleClient.getModelVehicle(Mockito.anyLong()))
                        .thenReturn(Flux.just(vehicle));
        Mockito.when(priceCryptoCurrencyClient.getPrice(Mockito.any()))
                        .thenReturn(Mono.just(BigDecimal.valueOf(20000)));

        StepVerifier.create(modelVehicleAdapterDependencies.retrieveByModelAndCrypto(MODEL_ENUM, CRYPTO_CURRENCY_ENUM))
                .expectNextMatches(v-> v.getPriceCryptocurrency().compareTo(BigDecimal.valueOf(4.00)) == 0)
                .verifyComplete();
        Mockito.verify(modelVehicleClient, Mockito.times(1)).getModelVehicle(Mockito.anyLong());
        Mockito.verify(priceCryptoCurrencyClient, Mockito.times(1)).getPrice(Mockito.any());
    }

    @Test
    void retrieveByModelAndCryptoAndVersion() {
        VehicleModelDto vehicle=MockData.getVehicleModelDto();
        vehicle.setName("TL");
        Mockito.when(modelVehicleClient.getModelVehicle(Mockito.anyLong()))
                .thenReturn(Flux.just(vehicle));
        Mockito.when(priceCryptoCurrencyClient.getPrice(Mockito.any()))
                .thenReturn(Mono.just(BigDecimal.valueOf(20000)));

        StepVerifier.create(modelVehicleAdapterDependencies.retrieveByModelAndCryptoAndVersion(MODEL_ENUM, CRYPTO_CURRENCY_ENUM, "TL"))
                .expectNextCount(1)
                .verifyComplete();
        Mockito.verify(modelVehicleClient, Mockito.times(1)).getModelVehicle(Mockito.anyLong());
        Mockito.verify(priceCryptoCurrencyClient, Mockito.times(1)).getPrice(Mockito.any());
    }

    @Test
    void retrieveVehiclesWithPrice() {
        VehicleModelDto vehicle=MockData.getVehicleModelDto();
        Mockito.when(modelVehicleClient.getModelVehicle(Mockito.anyLong()))
                .thenReturn(Flux.just(vehicle));
        Mockito.when(priceCryptoCurrencyClient.getPrice(Mockito.any()))
                .thenReturn(Mono.just(BigDecimal.valueOf(20000)));

        StepVerifier.create(modelVehicleAdapterDependencies.retrieveVehiclesWithPrice(MODEL_ENUM, CRYPTO_CURRENCY_ENUM))
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(modelVehicleClient, Mockito.times(1)).getModelVehicle(Mockito.anyLong());
        Mockito.verify(priceCryptoCurrencyClient, Mockito.times(1)).getPrice(Mockito.any());
    }

    @Test
    void add() {
        Mockito.when(priceCryptoCurrencyClient.getPrice(Mockito.any()))
                .thenReturn(Mono.just(BigDecimal.valueOf(20000)));
        StepVerifier.create(modelVehicleAdapterDependencies.add(CRYPTO_CURRENCY_ENUM, MockData.getModelVehicleDomain()))
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(priceCryptoCurrencyClient, Mockito.times(1)).getPrice(Mockito.any());

    }
}