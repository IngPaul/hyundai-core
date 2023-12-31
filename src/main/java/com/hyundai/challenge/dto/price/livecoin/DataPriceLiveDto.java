package com.hyundai.challenge.dto.price.livecoin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataPriceLiveDto {
    private BigDecimal lastPrice;
}
