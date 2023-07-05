package com.hyundai.challenge.aplication.services;

import Util.MockData;
import com.hyundai.challenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.challenge.aplication.port.out.a_common.memory.RetrieveVersionVehicleInMemoryPort;
import com.hyundai.challenge.aplication.port.out.catalog.RetrieveVersionVehiclePort;
import com.hyundai.challenge.aplication.port.out.purchase.VehiclePurchaseSavePort;
import com.hyundai.challenge.configuration.handler.error.CoreError;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.base.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.domain.base.enums.ModelVehicleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceImplTest {
    @Mock private VehiclePurchaseSavePort vehiclePurchaseSavePort;
    @Mock private RetrieveVersionVehicleInMemoryPort retrieveVersionVehicleInMemoryPort;
    @Mock private RetrieveVersionVehiclePort retrieveVersionVehiclePort;
    @Mock private AddPriceCriptocurrencyPort addPriceCriptocurrencyPort;
    @InjectMocks PurchaseServiceImpl purchaseService;

    @BeforeEach
    void setUp() {
    }

    private  void purchase(Mono<ModelVehicleDomain> justRetrieveInMemory, Mono<ModelVehicleDomain> justRetrieveInBase) {
        Mockito.when(retrieveVersionVehicleInMemoryPort.retrieveByConversionIdAndVersion(Mockito.anyString(), Mockito.any(), Mockito.anyString()))
                .thenReturn(justRetrieveInMemory);

        Mockito.when(retrieveVersionVehiclePort.retrieveByModelAndCryptoAndVersion(Mockito.any(), Mockito.any(), Mockito.anyString()))
                .thenReturn(justRetrieveInBase);

        Mockito.when(addPriceCriptocurrencyPort.add(Mockito.any(),Mockito.any()))
                .thenReturn(Mono.just(MockData.getModelVehicleDomain()));

        Mockito.when(vehiclePurchaseSavePort.purchase(Mockito.any()))
                .thenReturn(Mono.just(MockData.getModelVehicleDomain()));
    }
    @Test
    void purchaseWithDataInMemory() {
        purchase(Mono.just(MockData.getModelVehicleDomain()),Mono.just(MockData.getModelVehicleDomain()));
        StepVerifier.create(purchaseService.purchase(MockData.getModelVehicleDomain(), "XXXXX"))
                .expectNextCount(1)
                .verifyComplete();
        Mockito.verify(retrieveVersionVehicleInMemoryPort, Mockito.times(1)).retrieveByConversionIdAndVersion(Mockito.anyString(), Mockito.any(), Mockito.anyString());
        Mockito.verify(addPriceCriptocurrencyPort, Mockito.times(1)).add(Mockito.any(), Mockito.any());
        Mockito.verify(vehiclePurchaseSavePort, Mockito.times(1)).purchase(Mockito.any());
    }
    @Test
    void purchaseWithDataNotInMemory() {
        purchase(Mono.empty(), Mono.just(MockData.getModelVehicleDomain()));
        StepVerifier.create(purchaseService.purchase(MockData.getModelVehicleDomain(), "XXXXX"))
                .expectNextCount(1)
                .verifyComplete();
        Mockito.verify(retrieveVersionVehicleInMemoryPort, Mockito.times(1)).retrieveByConversionIdAndVersion(Mockito.anyString(), Mockito.any(), Mockito.anyString());
        Mockito.verify(retrieveVersionVehiclePort, Mockito.times(1)).retrieveByModelAndCryptoAndVersion(Mockito.any(), Mockito.any(), Mockito.anyString());
        Mockito.verify(addPriceCriptocurrencyPort, Mockito.times(1)).add(Mockito.any(), Mockito.any());
        Mockito.verify(vehiclePurchaseSavePort, Mockito.times(1)).purchase(Mockito.any());
    }

    @Test
    void purchaseVersionVehicleNotExist() {
        Mockito.when(retrieveVersionVehicleInMemoryPort.retrieveByConversionIdAndVersion(Mockito.anyString(), Mockito.any(), Mockito.anyString()))
                .thenReturn(Mono.empty());
        Mockito.when(retrieveVersionVehiclePort.retrieveByModelAndCryptoAndVersion(Mockito.any(), Mockito.any(), Mockito.anyString()))
                .thenReturn(Mono.empty());
        StepVerifier.create(purchaseService.purchase(MockData.getModelVehicleDomain(), "XXXXX"))
                .expectError();
        Mockito.verify(retrieveVersionVehicleInMemoryPort, Mockito.times(1)).retrieveByConversionIdAndVersion(Mockito.anyString(), Mockito.any(), Mockito.anyString());
        Mockito.verify(retrieveVersionVehiclePort, Mockito.times(1)).retrieveByModelAndCryptoAndVersion(Mockito.any(), Mockito.any(), Mockito.anyString());
    }


}