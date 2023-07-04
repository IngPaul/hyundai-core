package com.hyundai.challenge.core.services.main.impl;

import Util.MockData;
import com.hyundai.challenge.core.enums.VehicleModelEnum;
import com.hyundai.hexchallenge.adapters.out.dbs.memory.redis.CacheVehicleVersionService;
import com.hyundai.challenge.core.services.common.ConversionService;
import com.hyundai.challenge.dto.vehicle.VehicleModelDto;
import com.hyundai.hexchallenge.adapters.out.webclients.restClient.webclient.ModelVehicleClient;
import com.hyundai.challenge.model.VehicleVersion;
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

import java.math.BigDecimal;
import java.util.Arrays;
@ExtendWith(MockitoExtension.class)
class RetrieveVehicleModelServiceImplTest {
    @Mock private ModelVehicleClient modelVehicleClient;
    @Mock private ConversionService conversionService;
    @Mock private CacheVehicleVersionService cacheService;
    @InjectMocks private RetrieveVehicleModelServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void postVehicleModelRetrieve() {
        VehicleModelDto modelVehicle= new VehicleModelDto();
        Mockito.when(modelVehicleClient.getModelVehicle(Mockito.anyLong()))
                .thenReturn(Flux.just(modelVehicle));
        BigDecimal conversion= BigDecimal.valueOf(1);
        Mockito.when(conversionService.conversionToCryptoCurrency(Mockito.anyString(), Mockito.any()))
                .thenReturn(Mono.just(conversion));
        VehicleVersion vehicleCache= MockData.getVehicleVersion();
        Mockito.when(cacheService.save(Mockito.anyList(),Mockito.anyString(), Mockito.any()))
                .thenReturn(Mono.just(Arrays.asList(vehicleCache)));

        StepVerifier.create(service.postVehicleModelRetrieve("TUCSON","ETH"))
                .expectNextMatches(r->r.getData()!=null)
                .verifyComplete();
    }
}