package com.hyundai.challenge.adapters.out.dbs.memory.redis.repository;

import com.hyundai.challenge.model.VehicleVersion;
import com.hyundai.challenge.adapters.common.util.VehicleVersionParse;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
@Component
@RequiredArgsConstructor
public class CacheVehicleVersionRepository {
    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public Flux<VehicleVersion> findByConversionId(String conversionId){
        return redisTemplate.opsForValue().get(conversionId)
                .flatMapIterable(VehicleVersionParse::toListVehicle);
    }

    public Mono<List<VehicleVersion>> save(List<VehicleVersion> list, String conversionId, Duration expiration){
        String json=VehicleVersionParse.toJsonString(list);
       return redisTemplate.opsForValue().set(conversionId, json)
               .flatMap(result -> redisTemplate.expire(conversionId, expiration))
                .thenReturn(list);
    }

}
