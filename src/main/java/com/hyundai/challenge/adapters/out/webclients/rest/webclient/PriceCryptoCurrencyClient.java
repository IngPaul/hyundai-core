package com.hyundai.challenge.adapters.out.webclients.rest.webclient;

import com.hyundai.challenge.adapters.common.dto.price.coinlore.PriceLoreDto;
import com.hyundai.challenge.domain.enums.CryptoCurrencyEnum;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;

@Component
@AllArgsConstructor
@Slf4j
public class PriceCryptoCurrencyClient {
    private final LiveCoinWatchClient liveCoinWatchClient;
    private final CoinLoreClient coinLoreClient;
    @CircuitBreaker(name = "review-service", fallbackMethod = "fallback")
    public Mono<BigDecimal> getPrice(CryptoCurrencyEnum cryptocurrency){
        return liveCoinWatchClient.getPrice(cryptocurrency.name(),"USD")
                .map(price->price.getData().getLastPrice());
    }
    public Mono<BigDecimal> fallback(CryptoCurrencyEnum cryptocurrency, Throwable e) {
        log.info("** Fallback to retrieve price by {}", e.getMessage() );
        return coinLoreClient.getPrice(cryptocurrency.getId())
                .map(PriceLoreDto::getPriceUsd);
    }
}
