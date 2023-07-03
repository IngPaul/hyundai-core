package com.hyundai.hexchallenge.aplication.port.out.a_common.convertion;

import com.hyundai.challenge.core.enums.CryptoCurrencyEnum;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@FunctionalInterface
public interface ConversionVehicleModelPort {
    @CircuitBreaker(name = "review-service", fallbackMethod = "fallback")
    Mono<BigDecimal> getPrice(CryptoCurrencyEnum cryptocurrency);
}
