package com.hyundai.challenge.adapters.out.webclients.rest.webclient;

import Util.MockData;
import com.hyundai.challenge.adapters.common.dto.price.livecoin.PriceLiveDto;
import com.hyundai.challenge.domain.base.enums.CryptoCurrencyEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class PriceCryptoCurrencyClientTest {
    @Mock private LiveCoinWatchClient liveCoinWatchClient;

    @Mock private CoinLoreClient coinLoreClient;
    @InjectMocks
    private PriceCryptoCurrencyClient priceCryptoCurrencyClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getPrice() {
        CryptoCurrencyEnum cryptocurrency = CryptoCurrencyEnum.BTC;
        BigDecimal expectedPrice = BigDecimal.valueOf(50000.0);
        PriceLiveDto price= MockData.getPriceLiveDto();
        Mockito.when(liveCoinWatchClient.getPrice(cryptocurrency.name(), "USD"))
                .thenReturn(Mono.just(price));
        StepVerifier.create(priceCryptoCurrencyClient.getPrice(cryptocurrency))
                .expectNext(price.getData().getLastPrice() )
                .verifyComplete();

        Mockito.verify(liveCoinWatchClient, Mockito.times(1)).getPrice(cryptocurrency.name(), "USD");
    }

}