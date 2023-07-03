package com.hyundai.challenge.dto.price.livecoin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceLiveDto {
    private DataPriceLiveDto data;
}
