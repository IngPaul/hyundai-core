package com.hyundai.challenge.adapters.common.dto.price.coinlore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceLoreDto {
    @JsonProperty("price_usd")
    private BigDecimal priceUsd;
}
