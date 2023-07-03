package challenge.core.services.common;

import challenge.core.enums.CryptoCurrencyEnum;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface RetrievePriceCryptoCurrency {
    @CircuitBreaker(name = "review-service", fallbackMethod = "fallback")
    Mono<BigDecimal> getPrice(CryptoCurrencyEnum cryptocurrency);
}
