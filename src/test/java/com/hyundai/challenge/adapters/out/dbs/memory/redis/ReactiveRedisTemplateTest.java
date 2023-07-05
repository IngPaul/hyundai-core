package com.hyundai.challenge.adapters.out.dbs.memory.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@SpringBootTest
@SpringJUnitConfig
 class ReactiveRedisTemplateTest {

    @Autowired
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    private static final String KEY = "test:key";
    private static final String VALUE = "test:value";

    @BeforeEach
     void setUp() {
        reactiveRedisTemplate.opsForValue().set(KEY, VALUE).block();
    }

    @AfterEach
     void tearDown() {
        reactiveRedisTemplate.delete(KEY).block();
    }

    @Test
     void testGetValueFromRedis() {
        Mono<String> result = reactiveRedisTemplate.opsForValue().get(KEY);

        StepVerifier.create(result)
                .expectNext(VALUE)
                .verifyComplete();
    }

    @Test
     void testSetValueInRedis() {
        String newValue = "new:value";
        Mono<Boolean> result = reactiveRedisTemplate.opsForValue().set(KEY, newValue);

        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();

        Mono<String> valueResult = reactiveRedisTemplate.opsForValue().get(KEY);

        StepVerifier.create(valueResult)
                .expectNext(newValue)
                .verifyComplete();
    }

    @Test
     void testExpireKeyInRedis() {
        Duration expiration = Duration.ofSeconds(5);

        Mono<Boolean> result = reactiveRedisTemplate.expire(KEY, expiration);
        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();
        try {
            Thread.sleep(expiration.toMillis() + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Mono<String> valueResult = reactiveRedisTemplate.opsForValue().get(KEY);

        StepVerifier.create(valueResult)
                .expectNextCount(0)
                .verifyComplete();
    }
}