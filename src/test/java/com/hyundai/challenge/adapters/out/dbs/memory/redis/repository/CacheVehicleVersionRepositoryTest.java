package com.hyundai.challenge.adapters.out.dbs.memory.redis.repository;

import Util.MockData;
import com.hyundai.challenge.model.VehicleVersion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CacheVehicleVersionRepositoryTest {
    @Autowired
    private CacheVehicleVersionRepository repository;

    @Test
    void testReporitory() {
        List<VehicleVersion> list=Arrays.asList(MockData.getVehicleVersion());
        String conversionId="1111111111111";
        List<VehicleVersion> savedData = repository.save(list, conversionId, Duration.ofSeconds(20)).block();
        List<VehicleVersion> findData = repository.findByConversionId(conversionId).collectList().block();
        Assertions.assertEquals(savedData,findData);

    }
}