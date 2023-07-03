package com.hyundai.challenge.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hyundai.challenge.configuration.handler.error.CoreError.CRYPTOCURRENCY_NOT_FOUND;

@AllArgsConstructor
@Getter
public enum CryptoCurrencyEnum {
    ETH("ETH", 80L),
    BTC("BTC", 90L);

    private final String name;
    private final Long id;

    public static CryptoCurrencyEnum fromName(String name) {
        for (CryptoCurrencyEnum currency : CryptoCurrencyEnum.values()) {
            if (currency.name.equalsIgnoreCase(name)) {
                return currency;
            }
        }
        throw CRYPTOCURRENCY_NOT_FOUND;
    }
}
