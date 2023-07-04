package com.hyundai.challenge.aplication.services;

import Util.MockData;
import com.hyundai.challenge.aplication.port.out.a_common.convertion.AddPriceCriptocurrencyPort;
import com.hyundai.challenge.aplication.port.out.a_common.memory.SaveListModelVehiclesInMemoryPort;
import com.hyundai.challenge.aplication.port.out.catalog.RetrieveModelVehiclesPort;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RetrieveVehicleServiceImplTest {
    @Mock private RetrieveModelVehiclesPort retrieveModelVehiclesPort;
    @Mock
    private AddPriceCriptocurrencyPort addPriceCriptocurrencyPort;
    @Mock private SaveListModelVehiclesInMemoryPort saveListModelVehiclesInMemoryPort;
    @InjectMocks RetrieveVehicleServiceImpl retrieveVehicleService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void retrieveByModelAndCrypto() {

        ModelVehicleDomain modelVehicleDomain= MockData.getModelVehicleDomain();
        Mockito.when(retrieveModelVehiclesPort.retrieveByModelAndCrypto(Mockito.any(), Mockito.any()))
                .thenReturn(Flux.just(modelVehicleDomain));
        Mockito.when(addPriceCriptocurrencyPort.add(Mockito.any(), Mockito.any()))
                .thenReturn(Mono.just(modelVehicleDomain));
        Mockito.when(saveListModelVehiclesInMemoryPort.save(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(Mono.just(Arrays.asList(modelVehicleDomain)));

        StepVerifier.create(retrieveVehicleService.retrieveByModelAndCrypto("TUCSON", "BTC"))
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(retrieveModelVehiclesPort, Mockito.times(1)).retrieveByModelAndCrypto(Mockito.any(), Mockito.any());
        Mockito.verify(addPriceCriptocurrencyPort, Mockito.times(1)).add(Mockito.any(), Mockito.any());
        Mockito.verify(saveListModelVehiclesInMemoryPort, Mockito.times(1)).save(Mockito.any(), Mockito.any(), Mockito.any());

    }

}