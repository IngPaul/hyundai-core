package com.hyundai.challenge.core.services.common.impl;

import Util.MockData;
import com.hyundai.challenge.core.util.VehicleVersionParse;
import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.hexchallenge.adapters.out.dbs.memory.redis.CacheVehicleVersionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CacheVehicleVersionServiceImplTest {
    @Mock private ReactiveRedisTemplate<String, String> redisTemplate;
    @InjectMocks private CacheVehicleVersionServiceImpl cacheVehicleVersionService;
    @Mock
    private ReactiveValueOperations<String, String> valueOperations;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        cacheVehicleVersionService = new CacheVehicleVersionServiceImpl(redisTemplate);
    }

    @Test
    void findByConversionId() {
        List<VehicleVersion> vehicleVersions = Arrays.asList(MockData.getVehicleVersion());
        Mockito.when(redisTemplate.opsForValue().get(Mockito.anyString()))
                .thenReturn(Mono.just(VehicleVersionParse.toJsonString(vehicleVersions)));
        StepVerifier.create(cacheVehicleVersionService.findByConversionId("id"))
                .expectNextCount(1)
                .verifyComplete();
        Mockito.verify(valueOperations, Mockito.times(1)).get(Mockito.anyString());
    }

    @Test
    void save() {
        String conversionId = "conversionId";
        Duration expiration = Duration.ofMinutes(10);
        List<VehicleVersion> vehicleVersions = new ArrayList<>();
        Mockito.when(valueOperations.set(Mockito.anyString(), Mockito.anyString())).thenReturn(Mono.empty());

        StepVerifier.create(cacheVehicleVersionService.save(vehicleVersions, conversionId, expiration))
                .expectNextCount(1)
                .verifyComplete();

    }
}