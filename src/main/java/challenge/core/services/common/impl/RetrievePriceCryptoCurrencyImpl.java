package challenge.core.services.common.impl;

import challenge.core.enums.CryptoCurrencyEnum;
import challenge.core.services.common.RetrievePriceCryptoCurrency;
import challenge.dto.price.coinlore.PriceLoreDto;
import challenge.external.webclient.CoinLoreClient;
import challenge.external.webclient.LiveCoinWatchClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Slf4j
public class RetrievePriceCryptoCurrencyImpl implements RetrievePriceCryptoCurrency {
    private final LiveCoinWatchClient liveCoinWatchClient;
    private final CoinLoreClient coinLoreClient;
    @CircuitBreaker(name = "review-service", fallbackMethod = "fallback")
    @Override
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
