package com.hyundai.challenge.adapters.out.dbs.memory.redis.adapter;

import Util.MockData;
import com.hyundai.challenge.adapters.out.dbs.memory.redis.repository.CacheVehicleVersionRepository;
import com.hyundai.challenge.domain.ModelVehicleDomain;
import com.hyundai.challenge.domain.enums.ModelVehicleEnum;
import com.hyundai.challenge.model.VehicleVersion;
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

@ExtendWith(MockitoExtension.class)
class ModelVehicleAdapterRedisTest {
    @Mock private CacheVehicleVersionRepository vehicleVersionRepository;
   @InjectMocks private ModelVehicleAdapterRedis modelVehicleAdapterRedis;

    @Test
    void retrieveByConversionIdAndVersionExist() {
        VehicleVersion vehiclesVersion = MockData.getVehicleVersion();
        Mockito.when(vehicleVersionRepository.findByConversionId(Mockito.anyString()))
                        .thenReturn(Flux.just(vehiclesVersion));


        StepVerifier.create(modelVehicleAdapterRedis.retrieveByConversionIdAndVersion("1-2211-12", ModelVehicleEnum.ALL_NEW_TUCSON, "TL"))
                .expectNextMatches(vehicle->vehicle.getVersion().equals("TL"))
                .verifyComplete();

        Mockito.verify(vehicleVersionRepository, Mockito.times(1)).findByConversionId(Mockito.anyString());
    }

    @Test
    void retrieveByConversionIdAndVersionNotExist() {
        VehicleVersion vehiclesVersion = MockData.getVehicleVersion();
        Mockito.when(vehicleVersionRepository.findByConversionId(Mockito.anyString()))
                .thenReturn(Flux.just(vehiclesVersion));

        StepVerifier.create(modelVehicleAdapterRedis.retrieveByConversionIdAndVersion("1-2211-12", ModelVehicleEnum.ALL_NEW_TUCSON, "TL22"))
                .expectNextCount(0)
                .verifyComplete();

        Mockito.verify(vehicleVersionRepository, Mockito.times(1)).findByConversionId(Mockito.anyString());
    }

    @Test
    void save() {
        ModelVehicleDomain model= MockData.getModelVehicleDomain();
        VehicleVersion vehicle= MockData.getVehicleVersion();
        Mockito.when(vehicleVersionRepository.save(Mockito.anyList(), Mockito.anyString(), Mockito.any()))
                        .thenReturn(Mono.just(Arrays.asList(vehicle)));
        StepVerifier.create(modelVehicleAdapterRedis.save(Arrays.asList(model),"1-2211-12", Duration.ofSeconds(20)))
                .expectNextCount(1)
                .verifyComplete();
    }
}