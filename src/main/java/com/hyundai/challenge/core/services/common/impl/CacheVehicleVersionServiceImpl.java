package com.hyundai.challenge.core.services.common.impl;

import com.hyundai.challenge.core.services.common.CacheVehicleVersionService;
import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.challenge.core.util.VehicleVersionParse;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CacheVehicleVersionServiceImpl implements CacheVehicleVersionService {
    private final ReactiveRedisTemplate<String, String> redisTemplate;
    @Override
    public Flux<VehicleVersion> findByConversionId(String conversionId){
        return redisTemplate.opsForValue().get(conversionId)
                .flatMapIterable(VehicleVersionParse::toListVehicle);
    }
    @Override
    public Mono<List<VehicleVersion>> save(List<VehicleVersion> list, String conversionId, Duration expiration){
        String json=VehicleVersionParse.toJsonString(list);
       return redisTemplate.opsForValue().set(conversionId, json)
               .flatMap(result -> redisTemplate.expire(conversionId, expiration))
                .thenReturn(list);
    }

}
