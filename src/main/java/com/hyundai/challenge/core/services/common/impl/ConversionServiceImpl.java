package com.hyundai.challenge.core.services.common.impl;

import com.hyundai.challenge.core.enums.CryptoCurrencyEnum;
import com.hyundai.challenge.core.services.common.ConversionService;
import com.hyundai.hexchallenge.adapters.out.webclients.restClient.ClientServicesWithFallback.RetrievePriceCryptoCurrency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversionServiceImpl implements ConversionService {
    private final RetrievePriceCryptoCurrency retrievePriceCryptoCurrency;
    @Override
    public Mono<BigDecimal> conversionToCryptoCurrency(String cryptocurrency, BigDecimal value) {
        return retrievePriceCryptoCurrency.getPrice(CryptoCurrencyEnum.fromName(cryptocurrency))
                .map(price -> value.divide(price,2 , DOWN));
    }
}
