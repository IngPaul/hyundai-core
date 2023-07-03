package com.hyundai.challenge.core.services.common.impl;

import com.hyundai.challenge.core.enums.CryptoCurrencyEnum;
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

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ConversionServiceImplTest {
    @Mock RetrievePriceCryptoCurrencyImpl retrievePriceCryptoCurrency;
    @InjectMocks private ConversionServiceImpl conversionService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void conversionToCryptoCurrency() {
        BigDecimal amountBTC=BigDecimal.valueOf(20000);
        Mockito.when(retrievePriceCryptoCurrency.getPrice(Mockito.any())).thenReturn(Mono.just(amountBTC));
        StepVerifier.create(conversionService.conversionToCryptoCurrency("BTC",BigDecimal.valueOf(80000)))
                .expectNextMatches(r->r.doubleValue()==4)
                .verifyComplete();
    }
}