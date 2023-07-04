package com.hyundai.challenge.core.services.common.impl;

import Util.MockData;
import com.hyundai.challenge.core.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.dto.price.livecoin.PriceLiveDto;
import com.hyundai.hexchallenge.adapters.out.webclients.restClient.webclient.RetrievePriceCryptoCurrencyImpl;
import com.hyundai.hexchallenge.adapters.out.webclients.restClient.webclient.CoinLoreClient;
import com.hyundai.hexchallenge.adapters.out.webclients.restClient.webclient.LiveCoinWatchClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

class RetrievePriceCryptoCurrencyImplTest {
    @Mock private LiveCoinWatchClient liveCoinWatchClient;

    @Mock private CoinLoreClient coinLoreClient;
    @InjectMocks private RetrievePriceCryptoCurrencyImpl retrievePriceCryptoCurrency;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        retrievePriceCryptoCurrency = new RetrievePriceCryptoCurrencyImpl(liveCoinWatchClient, coinLoreClient);
    }

    @Test
    void getPrice() {
        CryptoCurrencyEnum cryptocurrency = CryptoCurrencyEnum.BTC;
        BigDecimal expectedPrice = BigDecimal.valueOf(50000.0);
        PriceLiveDto price= MockData.getPriceLiveDto();
        when(liveCoinWatchClient.getPrice(cryptocurrency.name(), "USD"))
                .thenReturn(Mono.just(price));
        StepVerifier.create(retrievePriceCryptoCurrency.getPrice(cryptocurrency))
                .expectNext(price.getData().getLastPrice() )
                .verifyComplete();

        verify(liveCoinWatchClient, times(1)).getPrice(cryptocurrency.name(), "USD");
        verifyNoInteractions(coinLoreClient);
    }

    @Test
    void fallback() {

    }
}